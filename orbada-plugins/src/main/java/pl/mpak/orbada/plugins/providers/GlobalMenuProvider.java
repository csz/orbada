/*
 * GlobalMenuProvider.java
 * 
 * Created on 2007-11-03, 14:03:17
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import javax.swing.JMenu;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.plugins.spi.IPluginProvider;

/**
 * <p>S�u�o do dostarczenia globalnego Menu-a, kt�re dodane zostanie w g��wnym oknie aplikacji<br>
 * Po utworzeniu menu b�dzie dost�pny, nast�pnie wywo�ana b�dzie funkcja initialize()
 * @author akaluza
 */
public abstract class GlobalMenuProvider implements IPluginProvider {

  protected IApplication application;
  protected JMenu menu;
  
  public void setApplication(IApplication application) {
    this.application = application;
  }
  
  public boolean isSharedProvider() {
    return true;
  }
  
  public void setMenu(JMenu menu) {
    this.menu = menu;
  }
  
  public JMenu getMenu() {
    return menu;
  }
  
  public abstract void initialize();
  
}
