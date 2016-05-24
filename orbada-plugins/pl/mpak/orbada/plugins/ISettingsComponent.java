/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

/**
 * <p>Interfejs do obs�ugi ustawie� wtyczki i perspektywy.
 * <p>Komponent, panel ustawie� mo�e implementowa� r�wnie� interfejs java.io.Closeable.
 * Przy zamykaniu okna ustawie� b�dzie on wywo�any je�li zosta� utworzony.
 * @see SettingProvider
 * @see PerspectiveSettingProvider
 * @author akaluza
 */
public interface ISettingsComponent {

  public void restoreSettings();
  
  public void applySettings();
  
  public void cancelSettings();

}
