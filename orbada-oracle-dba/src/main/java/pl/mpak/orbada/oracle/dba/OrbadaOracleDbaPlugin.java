package pl.mpak.orbada.oracle.dba;

import java.util.ArrayList;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.oracle.cm.FindObjectFromEditorAction;
import pl.mpak.orbada.oracle.dba.services.OracleCacheTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleCompressTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleCreateDatabaseTriggerAction;
import pl.mpak.orbada.oracle.dba.services.OracleCreateSchemaTriggerAction;
import pl.mpak.orbada.oracle.dba.services.OracleDbaDatabaseProvider;
import pl.mpak.orbada.oracle.dba.services.OracleGotoLockSIDAction;
import pl.mpak.orbada.oracle.dba.services.OracleGotoSessionSIDAction;
import pl.mpak.orbada.oracle.dba.services.OracleKillSessionAction;
import pl.mpak.orbada.oracle.dba.services.OracleLocksView;
import pl.mpak.orbada.oracle.dba.services.OracleLoggingTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleMoveTablespaceTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleParametersView;
import pl.mpak.orbada.oracle.dba.services.OracleRebuildIndexAction;
import pl.mpak.orbada.oracle.dba.services.OracleRowMovementTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleSessionsView;
import pl.mpak.orbada.oracle.dba.services.OracleSetDbParameterAction;
import pl.mpak.orbada.oracle.dba.services.OracleShrinkTableAction;
import pl.mpak.orbada.oracle.dba.services.OracleVisualDataFileView;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.id.VersionID;

/**
 *
 * @author akaluza
 */
public class OrbadaOracleDbaPlugin extends OrbadaPlugin {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaOracleDbaPlugin.class);

  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();
  
  public static FindObjectFromEditorAction findObjectAction;
    
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaOracleDbaPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaOracleDbaPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Database,Oracle,DBA";
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
    return new VersionID(1, 0, 1, 18).toString();
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
    return Consts.orbadaOracleDbaPluginId;
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
    addDepend(Consts.orbadaOraclePluginId);
    for (IPlugin plugin : loadedPlugins) {
      if (Consts.orbadaUniversalPluginId.equals(plugin.getUniqueID())) {
//        classList.add(UniversalColumnProvider.class);
      }
      else if (Consts.orbadaOraclePluginId.equals(plugin.getUniqueID())) {
        classList.add(OracleDbaDatabaseProvider.class);
        classList.add(OracleSessionsView.class);
        classList.add(OracleLocksView.class);
        classList.add(OracleParametersView.class);
        classList.add(OracleCreateSchemaTriggerAction.class);
        classList.add(OracleCreateDatabaseTriggerAction.class);
        classList.add(OracleRebuildIndexAction.class);
        classList.add(OracleCompressTableAction.class);
        classList.add(OracleCacheTableAction.class);
        classList.add(OracleRowMovementTableAction.class);
        classList.add(OracleShrinkTableAction.class);
        classList.add(OracleMoveTablespaceTableAction.class);
        classList.add(OracleLoggingTableAction.class);
        classList.add(OracleVisualDataFileView.class);
        classList.add(OracleKillSessionAction.class);
        classList.add(OracleSetDbParameterAction.class);
        classList.add(OracleGotoLockSIDAction.class);
        classList.add(OracleGotoSessionSIDAction.class);
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

}
