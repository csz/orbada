/*
 * OrbadaBeanshellPlugin.java
 * 
 * Created on 2007-10-31, 21:00:08
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.beanshell;

import pl.mpak.orbada.beanshell.gui.BeanShellEditorView;
import pl.mpak.orbada.beanshell.services.BeanshellSqlTextTransformProvider;
import pl.mpak.orbada.beanshell.services.StartupShutdownSettingsService;
import bsh.Interpreter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import orbada.Consts;
import pl.mpak.orbada.beanshell.services.BshActionsSettingsProvider;
import pl.mpak.orbada.beanshell.services.BshComponentActionsProvider;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import orbada.util.ScriptUtil;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.id.VersionID;
import pl.mpak.util.patt.Resolvers;

/**
 *
 * @author akaluza
 */
public class OrbadaBeanshellPlugin extends OrbadaPlugin {

  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaBeanshellPlugin.class);

  public final static String beanshellGroupName = "Orbada Tools";
  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();
  public final static String rootSettingsPath = "BeanShell";
  public final static String bshActionsReloadMessage = "bsh-actions-reload";
  
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaBeanshellPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaBeanshellPlugin-descriptive-name"), new Object[] {getVersion()});
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
    return "IDE,Tools,Scripting";
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
    return new VersionID(1, 0, 1, 8).toString();
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
    return Consts.orbadaBeanShellPluginId;
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz po za�adowaniu wtyczki.
   * ManOra jest ju� utworzona, konfiguracja programu za�adowana
   */
  public void load() {
    File startupFile = getStartupFile();
    if (startupFile.exists()) {
      Interpreter i = new Interpreter();
      try {
        i.eval(new FileReader(startupFile));
      } catch (Exception ex) {
        ExceptionUtil.processException(ex);
      }
    }
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz przed zamkni�ciem programu
   */
  public void unload() {
    File startupFile = getShutdownFile();
    if (startupFile.exists()) {
      Interpreter i = new Interpreter();
      try {
        i.eval(new FileReader(startupFile));
      } catch (Exception ex) {
        ExceptionUtil.processException(ex);
      }
    }
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
    classList.add(BeanShellEditorView.class);
    classList.add(StartupShutdownSettingsService.class);
    classList.add(BshActionsSettingsProvider.class);
    classList.add(BshComponentActionsProvider.class);
    if (application.isUserAdmin()) {
      if (getLastVersion() == null || new VersionID(getLastVersion()).getBuild() < 1) {
        ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/beanshell/sql/b0001.sql"));
      }
      if (getLastVersion() == null || new VersionID(getLastVersion()).getBuild() < 5) {
        ScriptUtil.executeInternalScript(getClass().getResourceAsStream("/pl/mpak/orbada/beanshell/sql/b0002.sql"));
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
    addDepend(Consts.orbadaUniversalPluginId);
    for (IPlugin plugin : loadedPlugins) {
      if (Consts.orbadaUniversalPluginId.equals(plugin.getUniqueID())) {
        classList.add(BeanshellSqlTextTransformProvider.class);
      }
    }
    return true;
  }
  
  public Class<IPluginProvider>[] getProviderArray() {
    return classList.toArray(new Class[classList.size()]);
  }

  @Override
  public void processMessage(PluginMessage message) {
  }
  
  public static File getStartupFile() {
    return new File(Resolvers.expand("$(orbada.home)") +"/startup.bsh");
  }

  public static File getShutdownFile() {
    return new File(Resolvers.expand("$(orbada.home)") +"/shutdown.bsh");
  }

}
