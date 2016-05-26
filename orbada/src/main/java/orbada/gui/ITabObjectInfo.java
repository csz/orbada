/*
 * ITabObjectInfo.java
 * 
 * Created on 2007-10-28, 17:42:47
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package orbada.gui;

import java.io.Closeable;
import pl.mpak.util.CloseAbilitable;
import pl.mpak.util.Titleable;

/**
 *
 * @author akaluza
 */
public interface ITabObjectInfo extends Closeable, Titleable, CloseAbilitable {

  /**
   * <p>Od�wierzenie natychmiastowe wybranego obiektu
   */
  public void refresh();
  
  /**
   * <p>Od�wierzenie obiektu z jego zmian�.
   * @param schemaName mo�e by� null
   * @param objectName mo�e to by� identyfikator jakiego� obiektu
   */
  public void refresh(String catalogName, String schemaName, String objectName);
  
}
