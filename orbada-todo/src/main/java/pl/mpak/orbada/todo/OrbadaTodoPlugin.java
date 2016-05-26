package pl.mpak.orbada.todo;

import java.io.File;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.db.InternalDatabase;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.orbada.todo.services.TodoPerspectiveProvider;
import pl.mpak.orbada.todo.services.TodoView;
import pl.mpak.util.id.VersionID;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.usedb.core.Database;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.patt.Resolvers;

/**
 *
 * @author akaluza
 */
public class OrbadaTodoPlugin extends OrbadaPlugin {

  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaTodoPlugin.class);

  public final static String todoGroupName = "Orbada Tools";
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaTodoPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaTodoPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Developers";
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
    return new VersionID(1, 0, 2, 24).toString();
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
    return Consts.orbadaTodoPluginId;
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
    new File(Resolvers.expand("$(orbada.home)") +"/todos").mkdirs();
    if (application.isUserAdmin()) {
      Database database = InternalDatabase.get();
      if (getLastVersion() == null) {
        try {
          database.executeCommand(
            "create table todos (\n" +
            "  td_id varchar(40) not null primary key,\n" +
            "  td_usr_id varchar(40) not null,\n" +
            "  td_sch_id varchar(40),\n" +
            "  td_priority integer,\n" +
            "  td_state varchar(100),\n" +
            "  td_title varchar(200),\n" +
            "  td_description varchar(1000),\n" +
            "  td_enable varchar(1) default 'T',\n" +
            "  td_inserted timestamp,\n" +
            "  td_updated timestamp,\n" +
            "  td_workaround varchar(1000),\n" +
            "  td_plan_end timestamp,\n" +
            "  td_ended timestamp,\n" +
            "  td_app_version varchar(100),\n" +
            "  td_orbada varchar(1),\n" +
            "  td_exported varchar(1)\n" +
            ")");
          database.executeCommand("alter table todos add constraint todos_sch_id_fk foreign key (td_sch_id) references schemas (sch_id) on delete cascade");
          database.executeCommand("create index todos_sch_id_priority_i on todos (td_sch_id, td_priority)");
          database.executeCommand("alter table todos add constraint todo_user_fk foreign key (td_usr_id) references users (usr_id) on delete cascade");
        }
        catch (Exception ex2) {
          ExceptionUtil.processException(ex2);
        }
      }
      else if (new VersionID(getLastVersion()).getBuild() < 21) {
        try {
          database.executeCommand("alter table todos add td_app_version varchar(100)");
          database.executeCommand("alter table todos add td_orbada varchar(1)");
          database.executeCommand("alter table todos add td_exported varchar(1)");
        }
        catch (Exception ex2) {
          ExceptionUtil.processException(ex2);
        }
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
      TodoView.class,
      TodoPerspectiveProvider.class
    };
  }

  @Override
  public void processMessage(PluginMessage message) {
  }
  
}
