/*
 * IPerspectiveAccesibilities.java
 * 
 * Created on 2007-11-11, 18:59:46
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import pl.mpak.orbada.plugins.providers.ViewProvider;
import pl.mpak.sky.gui.swing.Action;
import pl.mpak.usedb.core.Database;

/**
 *
 * @author akaluza
 */
public interface IPerspectiveAccesibilities {

  public Database getDatabase();
  
  public IApplication getApplication();
  
  /**
   * <p>Pozwala doda� menu do systemu dla perspektywy. Menu b�dzie zarz�dzane automatycznie przez
   * obiekt perspektywy. Nie trzeba go na koniec nawet zwalnia�.
   * @param menu 
   */
  public void addMenu(JMenu menu);
  
  /**
   * <p>Pozwala doda� akcje do toolbar-a systemu dla perspektywy.<br>
   * Akcja b�dzie zarz�dzane automatycznie przez obiekt perspektywy. 
   * Nie trzeba go na koniec nawet zwalnia�.
   * @param action 
   */
  public void addAction(Action action);
  
  /**
   * <p>Pozwala doda� toolbar do listy toolbar-�w systemu dla perspektywy.<br>
   * Toolbar b�dzie zarz�dzany automatycznie przez perspektyw�.<br>
   * Nie trzeba go zwalnia� na koniec.
   * @param toolBar
   */
  public void addToolBar(JToolBar toolBar);
  
  /**
   * <p> Pozwala doda� status bar perspektywy<br>
   * Perspektywa b�dzie zarz�dana automatycznie. Nie trzeba go na koniec zwalnia�.
   * @param statusBar
   */
  public void addStatusBar(JComponent statusBar);
  
  public Component getViewComponent(ViewProvider view);
  
  public Component createView(ViewProvider view);
  
  public void closeView(Component component);
  
  public void setSelectedView(Component c);
  
  public String getPerspectiveId();
  
}
