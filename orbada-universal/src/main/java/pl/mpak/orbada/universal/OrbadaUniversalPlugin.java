/*
 * OrbadaUniversalPlugin.java
 * 
 * Created on 2007-10-18, 20:13:27
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.universal;

import java.io.File;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.orbada.universal.services.SqlQueryView;
import pl.mpak.orbada.universal.services.DatabaseInfoView;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.db.OrbadaDatabase;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.universal.services.UniversalSessionSettingsProvider;
import pl.mpak.orbada.universal.services.UniversalDatabaseProvider;
import pl.mpak.orbada.universal.services.UniversalPerspectiveProvider;
import pl.mpak.orbada.universal.services.UniversalPerspectiveSettingsProvider;
import pl.mpak.orbada.universal.services.UniversalProceduresView;
import pl.mpak.orbada.universal.services.UniversalSettingsProvider;
import pl.mpak.orbada.universal.services.UniversalSqlTextTransformProvider;
import pl.mpak.orbada.universal.services.UniversalTablesView;
import pl.mpak.util.id.VersionID;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.usedb.core.Query;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author akaluza
 */
public class OrbadaUniversalPlugin extends OrbadaPlugin {

  private final static StringManager stringManager = StringManagerFactory.getStringManager("universal");

  public final static String version = new VersionID(1, 2, 0, 53).toString();
  public final static String universalGroupName = stringManager.getString("universal-group-name");
  public final static pl.mpak.util.timer.TimerQueue refreshQueue = pl.mpak.util.timer.TimerManager.getTimer("orbada-universal-refresh");
  public final static String universalSettingsRefresh = "universal-sql-query-settings-refresh";
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaUniversalPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaUniversalPlugin-descriptive-name"), new Object[] {getVersion()});
  }
  
  /**
   * <p>Funkcja powinna zwraca� rozszerzone informacje opisowe dotycz�ce wtyczki.
   * @return
   */
  public String getDescription() {
    return "";
  }
  
  /**
   * <p>Kategorie wtyczki, np:
   * <li>Database, HSQLDB</li>
   * <li>Developers</li>
   * @return
   */
  public String getCategory() {
    return "IDE,Database,Universal";
  }
  
  /**
   * Funkcja powinna zwraca� informacje o autorach wtyczki
   * @return
   */
  public String getAuthor() {
    return "Andrzej Ka�u�a";
  }
  
  public String getCopyrights() {
    return "";
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony www
   * @return
   */
  public String getWebSite() {
    return null;
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony aktualizacji
   * @return
   */
  public String getUpdateSite() {
    return null;
  }
  
  /**
   * Funckja powinna zwr�ci� wersj� najlepiej w postaci:
   * major.minor.release.build
   * @return
   */
  public String getVersion() {
    return version;
  }
  
  /**
   * Mo�e zwr�ci� tre�� licencji
   * @return
   */
  public String getLicence() {
    return null;
  }
  
  /**
   * <p>Funkcja musi zwraca� unikalny identyfikator wtyczki
   * <p>W tym miejscu mo�na sko�ysta� z kasy pl.mpak.sky.utils.UniqueID
   * Identyfikator identyfikuje jednoznacznie za�adowan� wtyczk�.
   * <p>Mo�e te� by� to unikalna nazwa wtyczki.
   * @return
   */
  public String getUniqueID() {
    return Consts.orbadaUniversalPluginId;
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz po za�adowaniu wtyczki.
   * ManOra jest ju� utworzona, konfiguracja programu za�adowana 
   */
  public void load() {
    
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz przed zamkni�ciem programu
   */
  public void unload() {
  }
  
  /**
   * Funkcja wywo�ywana jest po za�adowaniu wszystkich wtyczek i pokazaniu okna g��wnego.
   * W tym miejscu mo�e by� sprawdzone czy s� wszystkie wtyczki potrzebne
   * do prawid�owego dzia�ania tej wtyczki.
   * R�wnie� w tym miejscu mo�na podpi�� listenery gdzie tylko si� chce.
   * Mo�e podpi�� si� w odpowiednie miejsca menu, toolbar-a, list� po��cze�
   * skonfigurowanych i nawi�zanych. Mo�e uruchomi� jakie� zadania (Task), wpisa�
   * co� do log-a (pl.mpak.sky.utils.logging.Logger), etc
   */
  public void initialize() {
    OrbadaDatabase database = (OrbadaDatabase)application.getOrbadaDatabase();
    if (application.isUserAdmin()) {
      Query query = database.createQuery();
      try {
        if (getLastVersion() == null) {
          try {
            database.executeCommand(
              "create table execmds (\n" +
              "  cmd_file_id varchar(40) not null,\n" +
              "  cmd_crc32 numeric(10) not null,\n" +
              "  cmd_usr_id varchar(40) not null,\n" +
              "  cmd_executed timestamp,\n" +
              "  cmd_selected timestamp,\n" +
              "  cmd_source " +application.getProperty("data.type.clob", "CLOB") +",\n" +
              "  cmd_sch_id varchar(40),\n" +
              "  cmd_executed_count integer\n" +
              ")");
            database.executeCommandNoException("alter table execmds add constraint execmds_pk primary key (cmd_file_id)");
            database.executeCommandNoException("alter table execmds add constraint execmd_user_fk foreign key (cmd_usr_id) references users (usr_id) on delete cascade");
            database.executeCommand("create index execmds_selected_i on execmds(cmd_usr_id, cmd_sch_id, cmd_selected)");
            database.executeCommandNoException("alter table execmds add constraint execmd_schema_fk foreign key (cmd_sch_id) references schemas (sch_id) on delete cascade");
          }
          catch (Exception ex2) {
            ExceptionUtil.processException(ex2);
          }
          try {
            database.executeCommand(
              "create table sqlparams (\n" +
              "  sqlp_sch_id varchar(40),\n" +
              "  sqlp_name varchar(100) not null,\n" +
              "  sqlp_type varchar(50) not null,\n" +
              "  sqlp_mode varchar(10),\n" +
              "  sqlp_value varchar(1000)\n" +
              ")");
            database.executeCommandNoException("alter table sqlparams add constraint sqlparams_schema_fk foreign key (sqlp_sch_id) references schemas(sch_id) on delete cascade");
            database.executeCommand("create unique index sqlparams_schema_name_ui on sqlparams (sqlp_sch_id, sqlp_name)");
          }
          catch (Exception ex2) {
            ExceptionUtil.processException(ex2);
          }
        }
        else if (new VersionID(getLastVersion()).getBuild() < 2) {
          try { database.executeCommandNoException("alter table execmds drop constraint execmds_pk"); } catch (Exception ex) {}
          try { database.executeCommandNoException("alter table execmds add constraint execmds_pk primary key (cmd_file_id)"); } catch (Exception ex) {}
          try { database.executeCommand("drop index execmds_selected_i"); } catch (Exception ex) {}
          try { database.executeCommand("create index execmds_selected_i on execmds(cmd_usr_id, cmd_sch_id, cmd_selected)"); } catch (Exception ex) {}
          try { database.executeCommandNoException("alter table execmds add constraint execmd_schema_fk foreign key (cmd_sch_id) references schemas (sch_id) on delete cascade"); } catch (Exception ex) {}
        }
        else if (new VersionID(getLastVersion()).getBuild() < 3) {
          try { database.executeCommand("alter table execmds add cmd_executed_count integer"); } catch (Exception ex) {}
          try { database.executeCommand("update execmds set cmd_executed_count = 1"); } catch (Exception ex) {}
          try { database.commit(); } catch (Exception ex) {}
        }
        else if (new VersionID(getLastVersion()).getBuild() < 4) {
          try { database.executeCommand("alter table SQLPARAMS add sqlp_mode varchar(10)"); } catch (Exception ex) {}
          try { database.executeCommand("update SQLPARAMS set sqlp_mode = 'IN'"); } catch (Exception ex) {}
        }
      }
      finally {
        query.close();
      }
    }
  }
  
  /**
   * <p>Funkcja powinna sprawdzi� list� potrzebnych innych wtyczek
   * return informacje czy mo�na warunki s� spe�nione i czy mo�na u�ywa� tej wtyczki
   * @param loadedPlugins 
   * @return 
   */
  public boolean requires(List<IPlugin> loadedPlugins) {
    return true;
  }

  public Class<IPluginProvider>[] getProviderArray() {
    return new Class[] {
      SqlQueryView.class, 
      DatabaseInfoView.class,
      UniversalTablesView.class,
      UniversalPerspectiveProvider.class,
      UniversalSqlTextTransformProvider.class,
      UniversalSettingsProvider.class,
      UniversalPerspectiveSettingsProvider.class,
      UniversalDatabaseProvider.class,
      UniversalSessionSettingsProvider.class,
      UniversalProceduresView.class
    };
  }

  @Override
  public void processMessage(PluginMessage message) {
    if (message.isMessageId(Consts.globalMessageSchemaDeleted)) {
      File dir = new File(getApplication().getConfigPath() + "/sql-editor-contents/" +message.getObject().toString());
      if (dir.exists()) {
        File[] files = dir.listFiles();
        for (File file : files) {
          file.delete();
        }
        dir.delete();
      }
    }
  }
  
}
