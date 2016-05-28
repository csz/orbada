package pl.mpak.orbada.sqlscripts;

import java.util.ArrayList;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.orbada.sqlscripts.services.SqlScriptPerspectiveProvider;
import pl.mpak.orbada.util.ScriptUtil;
import pl.mpak.util.id.VersionID;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author akaluza
 */
public class OrbadaSqlScriptsPlugin extends OrbadaPlugin {

  private final StringManager stringManager = StringManagerFactory.getStringManager("sql-scripts");

  public final static String pluginGroupName = "Orbada Tools";
  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaSqlScriptsPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaSqlScriptsPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "Tools";
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
    return new VersionID(1, 0, 1, 7).toString();
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
    return Consts.orbadaSqlScriptsPluginId;
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
    classList.add(SqlScriptPerspectiveProvider.class);
    if (application.isUserAdmin()) {
      if (getLastVersion() == null) {
        ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/sqlscripts/sql/creation.sql"));
      }
      else if (new VersionID(getLastVersion()).getBuild() <= 1) {
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
    return classList.toArray(new Class[classList.size()]);
  }

  @Override
  public void processMessage(PluginMessage message) {
  }

}
