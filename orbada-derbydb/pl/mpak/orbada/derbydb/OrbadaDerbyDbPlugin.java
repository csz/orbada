package pl.mpak.orbada.derbydb;

import java.util.ArrayList;
import pl.mpak.orbada.derbydb.services.DerbyDbViewsView;
import pl.mpak.orbada.derbydb.services.DerbyDbTablesView;
import pl.mpak.orbada.derbydb.services.DerbyDbProceduresView;
import pl.mpak.orbada.derbydb.services.DerbyDbInfoProvider;
import pl.mpak.orbada.derbydb.services.DerbyDbFunctionsView;
import java.util.List;
import orbada.Consts;
import pl.mpak.orbada.derbydb.services.DerbyDbJarFilesView;
import pl.mpak.orbada.derbydb.services.DerbyDbPerspectiveProvider;
import pl.mpak.orbada.derbydb.services.DerbyDbStatisticsService;
import pl.mpak.orbada.derbydb.services.UniversalColumnService;
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
public class OrbadaDerbyDbPlugin extends OrbadaPlugin {
  
  private final static StringManager stringManager = StringManagerFactory.getStringManager(OrbadaDerbyDbPlugin.class);

  public final static String apacheDerbyDriverType = "Apache Derby";
  private static pl.mpak.util.timer.TimerQueue refreshQueue;
  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();
  
  public static pl.mpak.util.timer.TimerQueue getRefreshQueue() {
    if (refreshQueue == null) {
      refreshQueue = pl.mpak.util.timer.TimerManager.getTimer("orbada-derbydb-refresh");
    }
    return refreshQueue;
  }
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaDerbyDbPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaDerbyDbPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Database,Apache Derby DB";
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
    return new VersionID(1, 0, 1, 10).toString();
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
    return Consts.orbadaDerbyDbPluginId;
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
    application.registerDriverType(apacheDerbyDriverType);
    classList.add(DerbyDbInfoProvider.class);
    classList.add(DerbyDbTablesView.class);
    classList.add(DerbyDbViewsView.class);
    classList.add(DerbyDbProceduresView.class);
    classList.add(DerbyDbFunctionsView.class);
    classList.add(DerbyDbPerspectiveProvider.class);
    classList.add(DerbyDbJarFilesView.class);
    classList.add(DerbyDbStatisticsService.class);
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
    addDepend(Consts.orbadaCommanderPluginId);
    for (IPlugin plugin : loadedPlugins) {
      if (Consts.orbadaUniversalPluginId.equals(plugin.getUniqueID())) {
        classList.add(UniversalColumnService.class);
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
