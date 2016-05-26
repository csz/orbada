/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orbada.gui;

import java.io.Closeable;
import pl.mpak.util.CloseAbilitable;

/**
 * <p>Interfejs jest/powinie by� implementowany przez g��wne listy obiekt�w bazy danych
 * @author akaluza
 */
public interface IRootTabObjectInfo extends CloseAbilitable, Closeable {

  /**
   * <p>Od�wierzenie natychmiastowe listy obiekt�w
   */
  public void refresh();

  /**
   * <p>Od�wierzenie obiektu z jego zmian�.
   * @param objectName mo�e to by� identyfikator jakiego� obiektu (lub null) kt�ry ma by� wyr�niony
   */
  public void refresh(String objectName);

}
