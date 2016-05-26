package pl.mpak.orbada.localhistory;

import java.util.Date;
import java.util.List;
import orbada.Consts;
import orbada.db.InternalDatabase;
import pl.mpak.orbada.localhistory.services.LocalHistoryDatabaseService;
import pl.mpak.orbada.localhistory.services.LocalHistoryLastChangesActionService;
import pl.mpak.orbada.localhistory.services.LocalHistorySchemaSettingsService;
import pl.mpak.orbada.localhistory.services.LocalHistorySettingsService;
import pl.mpak.orbada.localhistory.services.LocalHistorySyntaxService;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import orbada.util.ScriptUtil;
import pl.mpak.util.id.VersionID;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.usedb.core.Command;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.variant.Variant;

/**
 *
 * @author akaluza
 */
public class OrbadaLocalHistoryPlugin extends OrbadaPlugin {

  private final static StringManager stringManager = StringManagerFactory.getStringManager(OrbadaLocalHistoryPlugin.class);

  public final static String pluginGroupName = stringManager.getString("OrbadaLocalHistoryPlugin-group-name");
  private static pl.mpak.util.timer.TimerQueue refreshQueue;
  private static ISettings settings;
  
  public static pl.mpak.util.timer.TimerQueue getRefreshQueue() {
    if (refreshQueue == null) {
      refreshQueue = pl.mpak.util.timer.TimerManager.getTimer("orbada-local-history-refresh");
    }
    return refreshQueue;
  }
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaLocalHistoryPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaLocalHistoryPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Tools,Developers,Diff";
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
    return new VersionID(1, 0, 1, 9).toString();
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
    return Consts.orbadaLocalHistoryPluginId;
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
    //Database database = application.getOrbadaDatabase();
    settings = application.getSettings(LocalHistorySettingsService.settingsName);
    if (application.isUserAdmin()) {
      if (getLastVersion() == null) {
        ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/localhistory/sql/creation.sql"));
      }
      else if (new VersionID(getLastVersion()).getBuild() < 8) {
        if ("oralce".equalsIgnoreCase(InternalDatabase.get().getDriverType())) {
          ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/localhistory/sql/oracle-1-0-1-8.sql"));
        }
        else if ("postgresql".equalsIgnoreCase(InternalDatabase.get().getDriverType())) {
          ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/localhistory/sql/postgresql-1-0-1-8.sql"));
        }
        else {
          ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/localhistory/sql/hsqldb-1-0-1-8.sql"));
        }
      }
    }
    if (getLastVersion() != null) {
      deleteOldest();
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
      LocalHistoryDatabaseService.class,
      LocalHistorySyntaxService.class,
      LocalHistorySchemaSettingsService.class,
      LocalHistorySettingsService.class,
      LocalHistoryLastChangesActionService.class
    };
  }

  @Override
  public void processMessage(PluginMessage message) {
  }

  private void deleteOldest() {
    int deleteAfterDays = settings.getValue(LocalHistorySettingsService.setDeleteAfterDays, 30L).intValue();
    try {
      Variant v = new Variant(new Date()).subtract(new Variant(deleteAfterDays));
      Command command = application.getOrbadaDatabase().createCommand(Sql.getDeleteOldest(), false);
      command.paramByName("OLHO_CREATED").setTimestamp(v.getTimestamp());
      command.paramByName("USR_ID").setString(application.getUserId());
      command.execute();
    } 
    catch(Exception ex) {
      ExceptionUtil.processException(ex);
    }
  }

  public static ISettings getSettings() {
    return settings;
  }
  
}
