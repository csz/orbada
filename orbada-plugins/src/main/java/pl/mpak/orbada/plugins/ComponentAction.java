/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import java.awt.Component;
import javax.swing.Icon;
import pl.mpak.sky.gui.swing.Action;
import pl.mpak.usedb.core.Database;

/**
 *
 * @author akaluza
 */
public class ComponentAction extends Action {

  protected Component component;
  protected Database database;

  public final static ComponentAction Separator = new ComponentAction("-");

  public ComponentAction(String title, Icon icon) {
    super(title, icon);
  }

  public ComponentAction(String title) {
    super(title);
  }

  public ComponentAction() {
    super();
  }

  public void setComponent(Component component) {
    this.component = component;
  }
  
  public void setDatabase(Database database) {
    this.database = database;
  }
  
  /**
   * <p>Zwraca zwi�zany z akcj� komponent.<br>
   * Mo�e to by� np JTable, QueryTable, SyntaxEditor, etc.<br>
   * Aby sprawdzi� w programie z jakim komponentem oraz jaki jest typ akcji jest 
   * zwi�zany przycisk Akcje nale�y najecha� na przycisk
   * z wci�ni�tym przyciskiem Ctrl i poczeka� na pojawienie si� tooltip-a
   * @return
   * @see pl.mpak.orbada.gui.cm.ComponentActionAction
   */
  public Component getComponent() {
    return component;
  }
  
  public Database getDatabase() {
    return database;
  }
  
  /**
   * <p>Powinna zwr�ci� informacje czy akcja ma si� pojawi� jako przycisk toolbar'a
   * @return
   */
  public boolean isToolButton() {
    return false;
  }

}
