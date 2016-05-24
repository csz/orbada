/*
 * OrbadaLafSubstancePlugin.java
 *
 * Created on 2010-10-31, 16:26:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.laf.jtatoo;

import java.util.ArrayList;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.laf.jtatoo.services.JTatooAcrylLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooAeroLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooAluminiumLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooBernsteinLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooFastLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooGlobalOptionsLookAndFeelSettingsService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooGraphiteLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooHiFiLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooLunaLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooMcWinLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooMintLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooNoireLookAndFeelService;
import pl.mpak.orbada.laf.jtatoo.services.JTatooSmartLookAndFeelService;
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
public class OrbadaLafJTatooPlugin extends OrbadaPlugin {
  
  private StringManager i18n = StringManagerFactory.getStringManager(OrbadaLafJTatooPlugin.class);

  public final static String pluginName = "JTatoo";
  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();
    
  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  @Override
  public String getInternalName() {
    return "OrbadaLafJTatooPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  @Override
  public String getDescriptiveName() {
    return String.format(i18n.getString("OrbadaLafJTatooPlugin-descriptive-name"), new Object[] {getVersion()});
  }
  
  /**
   * <p>Funkcja powinna zwraca� rozszerzone informacje opisowe dotycz�ce wtyczki.
   * @return
   */
  @Override
  public String getDescription() {
    return "";
  }
  
  /**
   * <p>Kategorie wtyczki, np:
   * <li>Database, HSQLDB</li>
   * <li>Developers</li>
   * @return
   */
  @Override
  public String getCategory() {
    return "IDE,LAF";
  }
  
  /**
   * Funkcja powinna zwraca� informacje o autorach wtyczki
   * @return
   */
  @Override
  public String getAuthor() {
    return "Andrzej Ka�u�a";
  }
  
  @Override
  public String getCopyrights() {
    return "";
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony www
   * @return
   */
  @Override
  public String getWebSite() {
    return null;
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony aktualizacji
   * @return
   */
  @Override
  public String getUpdateSite() {
    return null;
  }
  
  /**
   * Funckja powinna zwr�ci� wersj� najlepiej w postaci:
   * major.minor.release.build
   * @return
   */
  @Override
  public String getVersion() {
    return new VersionID(1, 0, 0, 1).toString();
  }
  
  /**
   * Mo�e zwr�ci� tre�� licencji
   * @return
   */
  @Override
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
  @Override
  public String getUniqueID() {
    return Consts.orbadaLafJTatooPluginId;
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz po za�adowaniu wtyczki.
   * ManOra jest ju� utworzona, konfiguracja programu za�adowana
   */
  @Override
  public void load() {
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz przed zamkni�ciem programu
   */
  @Override
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
  @Override
  public void initialize() {
    classList.add(JTatooAcrylLookAndFeelService.class);
    classList.add(JTatooAeroLookAndFeelService.class);
    classList.add(JTatooAluminiumLookAndFeelService.class);
    classList.add(JTatooBernsteinLookAndFeelService.class);
    classList.add(JTatooFastLookAndFeelService.class);
    classList.add(JTatooGraphiteLookAndFeelService.class);
    classList.add(JTatooHiFiLookAndFeelService.class);
    classList.add(JTatooLunaLookAndFeelService.class);
    classList.add(JTatooMcWinLookAndFeelService.class);
    classList.add(JTatooMintLookAndFeelService.class);
    classList.add(JTatooNoireLookAndFeelService.class);
    classList.add(JTatooSmartLookAndFeelService.class);
    classList.add(JTatooGlobalOptionsLookAndFeelSettingsService.class);
  }
  
  /**
   * <p>Funkcja powinna sprawdzi� list� potrzebnych innych wtyczek
   * return informacje czy mo�na warunki s� spe�nione i czy mo�na u�ywa� tej wtyczki
   * <p>Wywo�ane przed initialize()
   * @param loadedPlugins
   * @return
   */
  @Override
  public boolean requires(List<IPlugin> loadedPlugins) {
    return true;
  }
  
  /**
   * <p>Wywo�ywane jest po initialize()
   */
  @Override
  public Class<IPluginProvider>[] getProviderArray() {
    return classList.toArray(new Class[classList.size()]);
  }

  @Override
  public void processMessage(PluginMessage message) {
  }
  
}
