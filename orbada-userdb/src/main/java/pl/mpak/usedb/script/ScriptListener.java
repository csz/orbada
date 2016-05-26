package pl.mpak.usedb.script;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author akaluza
 *
 */
public interface ScriptListener extends EventListener {

  public void beforeScript(EventObject e);
  
  /**
   * <p>W e.getSource() znajduje si� obiekt Command
   * @param e
   */
  public void beforeCommand(EventObject e);
  
  /**
   * <p>W e.getSource() znajduje si� obiekt Command
   * @param e
   */
  public void afterCommand(EventObject e);
  
  /**
   * <p>W e.getSource() znajduje si� obiekt Query
   * @param e
   */
  public void beforeQuery(EventObject e);
  
  /**
   * <p>W e.getSource() znajduje si� obiekt Query
   * @param e
   */
  public void afterQuery(EventObject e);
  
  /**
   * <p>Wyst�pi� b�ad. Funkcja powinna zwr�ci� true je�li wykonywanie 
   * skryptu ma by� przerwane.
   * @param e
   * @return
   */
  public boolean errorOccured(ErrorScriptEventObject e);
  
  public void afterScript(EventObject e);
  
}
