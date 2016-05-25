package pl.mpak.plugins;

import java.util.List;

import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;

public class Plugin {

  private String source;
  private String className;
  private IPlugin plugin = null;
  private String uniqueID;
  private Class<?> clazz;
  private boolean canUsePlugin;
  private ClassLoader classLoader;

  public Plugin(String source, String className, ClassLoader classLoader) throws PluginException {
    this.className = className;
    this.source = source;
    this.classLoader = classLoader;
    loadPlugin();
  }

  public Plugin(String source, Class<?> clazz, ClassLoader classLoader) throws PluginException {
    this.className = clazz.getName();
    this.source = source;
    this.classLoader = classLoader;
    this.clazz = clazz;
    loadPlugin();
  }

  public String getSource() {
    return source;
  }
  
  public String getClassName() {
    return className;
  }

  public IPlugin getPlugin() {
    return plugin;
  }
  
  public Class<IPluginProvider>[] getProviderArray() {
    return plugin.getProviderArray();
  }
  
  void loadPlugin() throws PluginException {
    if (plugin == null) {
      try {
        if (clazz == null) {
          if (classLoader == null) {
            plugin = (IPlugin)ClassLoader.getSystemClassLoader().loadClass(className).newInstance();
          }
          else {
            plugin = (IPlugin)classLoader.loadClass(className).newInstance();
          }
        }
        else {
          plugin = (IPlugin)clazz.newInstance();
        }
      }
      catch(Exception e) {
        throw new PluginException(e);
      }
    }
    plugin.load();
    uniqueID = plugin.getUniqueID();
  }
  
  void unloadPlugin() {
    plugin.unload();
    plugin = null;
  }

  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return plugin.getInternalName();
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return plugin.getDescriptiveName();
  }
  
  /**
   * <p>Funkcja powinna zwraca� rozszerzone informacje opisowe dotycz�ce wtyczki.
   * @return
   */
  public String getDescription() {
    return plugin.getDescription();
  }
  
  /**
   * <p>Kategorie wtyczki, np:
   * <li>Database, HSQLDB</li>
   * <li>Developers</li>
   * @return
   */
  public String getCategory() {
    return plugin.getCategory();
  }
  
  /**
   * Funkcja powinna zwraca� informacje o autorach wtyczki
   * @return
   */
  public String getAuthor() {
    return plugin.getAuthor();
  }
  
  /**
   * <p>Funkcja powinna zwraca� informacje o w�a�cicielu praw do rozpowszechniania
   * @return
   */
  public String getCopyrights() {
    return plugin.getCopyrights();
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony www
   * @return
   */
  public String getWebSite() {
    return plugin.getWebSite();
  }
  
  /**
   * Funckja powinna zwr�ci� wersj� najlepiej w postaci:
   * major.minor.release.build
   * @return
   */
  public String getVersion() {
    return plugin.getVersion() == null ? "" : plugin.getVersion();
  }
  
  /**
   * Mo�e zwr�ci� �cie�k� do pliku licencji
   * @return
   */
  public String getLicence() {
    return plugin.getLicence();
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony aktualizacji
   * @return
   */
  public String getUpdateSite() {
    return plugin.getUpdateSite();
  }
  
  /**
   * Funkcja musi zwraca� unikalny identyfikator wtyczki
   * W tym miejscu mo�na sko�ysta� z kasy pl.mpak.sky.utils.UniqueID
   * Identyfikator identyfikuje jednoznacznie za�adowan� wtyczk�.  
   * @return
   */
  public String getUniqueID() {
    return uniqueID;
  }
  
  /**
   * <p>Funkcja wywo�ywana jest po za�adowaniu wszystkich wtyczek i pokazaniu okna g��wnego.
   * <p>W tym miejscu mo�e by� sprawdzone czy s� wszystkie wtyczki potrzebne
   * do prawid�owego dzia�ania tej wtyczki.
   * <p>R�wnie� w tym miejscu mo�na podpi�� listenery gdzie tylko si� chce.
   * <p>Mo�e podpi�� si� w odpowiednie miejsca menu, toolbar-a, list� po��cze�
   * skonfigurowanych i nawi�zanych. Mo�e uruchomi� jakie� zadania (Task), wpisa�
   * co� do log-a (pl.mpak.sky.utils.logging.Logger), etc
   * <p>Funkcja sprawdza czy mo�e si� uruchomi�, wcze�niej by�a wywo�ana funkcja requires
   */
  public void initialize() {
    if (canUsePlugin) {
      plugin.initialize();
    }
  }

  public boolean requires(List<IPlugin> loadedPlugins) {
    return canUsePlugin = plugin.requires(loadedPlugins);
  }

  public void reload() throws PluginException {
    unloadPlugin();
    loadPlugin();
    initialize();
  }

  public boolean isCanUsePlugin() {
    return canUsePlugin;
  }

  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  public String[] getDepends() {
    return plugin.getDepends();
  }

}
