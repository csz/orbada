/*
 * TableViewValueProvider.java
 *
 * Created on 2007-11-26, 21:10:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;

/**
 * <p>S�u�y do implementacji podgl�du warto�ci tabeli i innych danych
 * <p>getDescription() powinno zwraca� opis dla menu
 * @author akaluza
 */
public abstract class ViewValueProvider implements IOrbadaPluginProvider {
  
  protected IApplication application;
  
  public void setApplication(IApplication application) {
    this.application = application;
  }
  
  public boolean isSharedProvider() {
    return false;
  }
  
  /**
   * <p>Powinna tworzy� np JPanel z podgl�dem warto�ci
   * <p>value mo�e by� typu Variant
   * <p>Je�li jest wymagana jaka� specjalna akcja zwolnienia zasob�w to 
   * Component powinien implementowa� interfejs Closeable
   * @param value 
   * @return 
   */
  public abstract Component createComponent(Object value);
  
}
