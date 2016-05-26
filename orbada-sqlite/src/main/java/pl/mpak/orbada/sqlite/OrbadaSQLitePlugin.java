package pl.mpak.orbada.sqlite;

import java.util.ArrayList;
import java.util.List;
import orbada.Consts;
import orbada.db.InternalDatabase;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.orbada.sqlite.services.ExplainPlanProvider;
import pl.mpak.orbada.sqlite.services.SQLiteDatabaseProvider;
import pl.mpak.orbada.sqlite.services.SQLiteDatabasesView;
import pl.mpak.orbada.sqlite.services.SQLiteDbInfoProvider;
import pl.mpak.orbada.sqlite.services.SQLitePerspectiveProvider;
import pl.mpak.orbada.sqlite.services.SQLiteTablesView;
import pl.mpak.orbada.sqlite.services.SQLiteTriggersView;
import pl.mpak.orbada.sqlite.services.SQLiteViewsView;
import pl.mpak.orbada.sqlite.services.actions.AttachDatabaseAction;
import pl.mpak.orbada.sqlite.services.actions.DetachDatabaseAction;
import pl.mpak.orbada.sqlite.services.actions.DropTableAction;
import pl.mpak.orbada.sqlite.services.actions.DropTriggerAction;
import pl.mpak.orbada.sqlite.services.actions.DropViewAction;
import pl.mpak.orbada.sqlite.services.actions.FreezeAction;
import pl.mpak.orbada.sqlite.services.actions.PragmaDatabaseCacheSizeAction;
import pl.mpak.orbada.sqlite.services.actions.PragmaDatabaseIntegrityCheckAction;
import pl.mpak.orbada.sqlite.services.actions.PragmaDatabaseLockingModeAction;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.core.Query;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.id.VersionID;

/**
 *
 * @author akaluza
 */
public class OrbadaSQLitePlugin extends OrbadaPlugin {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaSQLitePlugin.class);

  public final static String driverType = "SQLite";
  public final static VersionID version = new VersionID(2, 0, 0, 15);

  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();

  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaSQLitePlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaSQLitePlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Database,SQLite";
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
    return version.toString();
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
    return Consts.orbadaSQLitePluginId;
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
    application.registerDriverType(driverType);
    classList.add(SQLiteDbInfoProvider.class);
    classList.add(SQLiteDatabaseProvider.class);
    classList.add(SQLiteTablesView.class);
    classList.add(SQLiteViewsView.class);
    classList.add(SQLiteTriggersView.class);
    classList.add(SQLiteDatabasesView.class);
    classList.add(DropTableAction.class);
    classList.add(DropViewAction.class);
    classList.add(DropTriggerAction.class);
    classList.add(FreezeAction.class);
    classList.add(AttachDatabaseAction.class);
    classList.add(DetachDatabaseAction.class);
    classList.add(SQLitePerspectiveProvider.class);
    classList.add(PragmaDatabaseLockingModeAction.class);
    classList.add(PragmaDatabaseCacheSizeAction.class);
    classList.add(PragmaDatabaseIntegrityCheckAction.class);
    initTemplates();
  }
  
  /**
   * <p>Funkcja powinna sprawdzi� list� potrzebnych innych wtyczek
   * return informacje czy mo�na warunki s� spe�nione i czy mo�na u�ywa� tej wtyczki
   * <p>Wywo�ane przed initialize()
   * @param loadedPlugins
   * @return
   */
  public boolean requires(List<IPlugin> loadedPlugins) {
    addDepend(Consts.orbadaUniversalPluginId);
    for (IPlugin plugin : loadedPlugins) {
      if (Consts.orbadaUniversalPluginId.equals(plugin.getUniqueID())) {
        classList.add(ExplainPlanProvider.class);
      }
    }
    return true;
  }
  
  /**
   * <p>Wywo�ywane jest po initialize()
   */
  public Class<IPluginProvider>[] getProviderArray() {
    return classList.toArray(new Class[classList.size()]);
  }

  @Override
  public void processMessage(PluginMessage message) {
  }

  private void initTemplates() {
    if (InternalDatabase.get() == null) {
      return;
    }
    Database database = InternalDatabase.get();
    Query query = database.createQuery();
    try {
//      query.open("select count( 0 ) cnt from templates where tpl_name like 'oracle-%'");
//      if (query.fieldByName("cnt").getInteger() == 0) {
//        Template template = new Template(application.getOrbadaDatabase());
//        template.setName("oracle-function");
//        template.setDescription("Oracle Function Template");
//        template.setUsrId("");
//        template.setBody(
//          "CREATE FUNCTION $(&name) $(&parameters) RETURN $(&return) IS\n" +
//          "--############################################################################\n" +
//          "--# ORBADA   : To change this template, choose Tools | Templates\n" +
//          "--# Autor    : $(orbada.user.name)\n" +
//          "--# Stworzono: $(orbada.current.date) $(orbada.current.time)\n" +
//          "--# Opis     : $(&description)\n" +
//          "--# Zmiany   :\n" +
//          "--# Data:      Autor:         Wersja:\n" +
//          "--# ---------- -------------- ------------------------------------------------\n" +
//          "--############################################################################\n" +
//          "BEGIN\n" +
//          "  /* TODO function implementation */\n" +
//          "$(&body)\n" +
//          "END;");
//        template.applyInsert();
//      }
      
//      ISettings settings = Application.get().getSettings(OracleTemplatesSettingsProvider.settingsName);
//      if (settings.getValue(OracleTemplatesSettingsProvider.setFunction).isNull()) {
//        settings.setValue(OracleTemplatesSettingsProvider.setFunction, "oracle-function");
//      }
//      settings.store();
    }
    catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
    finally {
      query.close();
    }
  }
  
}
