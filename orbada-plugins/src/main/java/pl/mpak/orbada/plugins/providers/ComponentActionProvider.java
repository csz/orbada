package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import pl.mpak.orbada.plugins.providers.abs.ActionProvider;
import pl.mpak.usedb.core.Database;

/**
 * <p>Akcja zostanie dodana do odpowiedniego przycisku Akcje zwi�zanego z komponentem table, edytora, etc.
 * @author akaluza
 * @see pl.mpak.orbada.gui.cm.ComponentActionAction
 */
public abstract class ComponentActionProvider extends ActionProvider {
  
  protected Component component;
  protected Database database;

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
   * <p>Testuje czy akcja jest przeznaczona dla konkretnej bazy danych i konkretnego typu akcji
   * @param database
   * @param actionType
   * @return
   */
  public abstract boolean isForComponent(Database database, String actionType);
  
  /**
   * <p>Powinna zwr�ci� informacje czy akcja ma si� pojawi� jako przycisk toolbar'a
   * @return
   */
  public boolean isToolButton() {
    return false;
  }
  
}
