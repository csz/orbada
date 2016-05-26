/*
 * ExportTableActionProvider.java
 *
 * Created on 2007-10-22, 21:08:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import pl.mpak.orbada.plugins.providers.abs.ActionProvider;

/**
 * <p>Dostawca eksport�w, dodany b�dzie do listy eksport�w dost�pnych z menu ka�dej JTable
 * @author akaluza
 */
public abstract class ExportTableActionProvider extends ActionProvider {
  
  protected JTable table;
  
  public void setTable(JTable table) {
    this.table = table;
  }

  protected JTable getTable(ActionEvent event) {
    if (table == null) {
      return (JTable)getParent(event, JTable.class);
    }
    return table;
  }
  
  /**
   * <p>Tutaj powinno odby� si� sprawdzenie czy element ma by� aktywny
   */
  public void checkEnable() {
    
  }
  
}
