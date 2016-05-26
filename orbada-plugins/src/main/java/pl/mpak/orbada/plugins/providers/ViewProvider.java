/*
 * ViewProvider.java
 * 
 * Created on 2007-10-27, 12:26:05
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import javax.swing.Icon;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;
import pl.mpak.usedb.core.Database;

/**
 *
 * @author akaluza
 */
public abstract class ViewProvider implements IOrbadaPluginProvider {

  protected IApplication application;
  
  public void setApplication(IApplication application) {
    this.application = application;
  }
  
  /**
   * <p>Czy ma by� utworzony tylko jeden egzempla� klasy
   * @return
   */
  public boolean isSharedProvider() {
    return false;
  }
  
  /**
   * <p>Informuje perspektyw� o tym, �e je�eli �aden widok nie zosta�
   * otwarty, to ten widok ma si� pokaza� automatycznie
   * @return
   */
  public boolean isDefaultView() {
    return false;
  }
  
  /**
   * <p>Powinna zwr�ci� informacj� czy widok przeznaczony jest dla tej bazy danych
   * <p>Wywo�ywana za ka�dym razem gdy wybierany jest nowy widok.
   * @param database 
   * @return 
   */
  public abstract boolean isForDatabase(Database database);
  
  /**
   * <p>Powinna utworzy� widok dla otwartej perspektywy.
   * @param accesibilities 
   * @return
   */
  public abstract Component createView(IViewAccesibilities accesibilities);
  
  /**
   * <p>Nazwa publiczna widoku przeznaczona dla u�ytkownika.
   * <p>Pozycja w menu, w ustawieniach, etc
   * @return
   */
  public abstract String getPublicName();
  
  /**
   * <p>Mo�e zwr�ci� list� submenu wy�wietlanych podczas wybierania
   * nowego widoku
   * @return
   */
  public String[] getSubmenu() {
    return null;
  }
  
  /**
   * <p>Wewn�trzna, unikalna nazwa widoku dla identyfikacji bezpo�redniej.
   * <p>Nie powinna si� zmienia�.
   * @return
   */
  public abstract String getViewId();
  
  /**
   * <p>Ikonka wy�wietlana w pozycji menu, wyboru aktywnego widoku i na
   * zak�adkach otwartych widok�w.
   * @return
   */
  public abstract Icon getIcon();
  
  public void viewShow() {}
  
  public void viewHide() {}
  
  public void viewClose() {}
  
}
