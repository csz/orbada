/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import javax.swing.Icon;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.IGadgetAccesibilities;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;
import pl.mpak.usedb.core.Database;

/**
 * 
 * @author akaluza
 */
public abstract class PerpectiveGadgetProvider implements IOrbadaPluginProvider {

  protected IApplication application;

  public void setApplication(IApplication application) {
    this.application = application;
  }
  
  public boolean isSharedProvider() {
    return false;
  }

  /**
   * <p>Powinna zwr�ci� informacj� czy serwis przeznaczony jest dla tej bazy danych
   * @param database 
   * @return 
   */
  public abstract boolean isForDatabase(Database database);
  
  /**
   * <p>Powinna utworzy� narz�dzie w postaci komponentu.
   * <p>Je�li dodany zostanie listener ComponentListener to przy minimalizacji zostanie wywo�any medota hide, a przy
   * przywr�ceniu widoczno�ci metoda show.
   * <p>Component mo�e implementowa� interfejsy:
   * <li><b>pl.mpak.util.Titleable</b> - pozwoli ustawi� tytu� narz�dzia w perspektywie</li>
   * <li><b>java.io.Closeable</b> - przy zamykaniu gad�etu wywo�any b�dzie close</li>
   * <li><b>pl.mpak.util.Configurable</b> - obs�uga okna konfiguracyjnego</li>
   * @param accesibilities
   * @return
   */
  public abstract Component createGadget(IGadgetAccesibilities accesibilities);
  
  /**
   * <p>Nazwa publiczna widoku przeznaczona dla u�ytkownika.
   * <p>Pozycja w menu, w ustawieniach, etc
   * @return
   */
  public abstract String getPublicName();
  
  /**
   * <p>Ikonka wy�wietlana w pozycji menu, wyboru aktywnego widoku i na
   * zak�adkach otwartych widok�w.
   * @return
   */
  public abstract Icon getIcon();
  
  /**
   * <p>Wewn�trzna, unikalna nazwa gad�etu dla identyfikacji bezpo�redniej.
   * <p>Nie powinna si� zmienia�.
   * @return
   */
  public abstract String getGadgetId();
  
}
