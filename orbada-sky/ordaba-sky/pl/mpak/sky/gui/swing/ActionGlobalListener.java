package pl.mpak.sky.gui.swing;

import java.util.EventListener;

/**
 * @author akaluza
 * <p>Globalny nas�uchiwacz wszystkich akcji
 */
public interface ActionGlobalListener extends EventListener {

  /**
   * <p>Zdarzenie wywo�ywane PRZED wywo�aniem dowolnego zdarzenia
   * @param e
   */
  public void beforeAction(ActionGlobalEvent e);

  /**
   * <p>Zdarzenie wywo�ywane PO wywo�aniem dowolnego zdarzenia.<br>
   * Zdarzenie b�dzie wywo�ane nawet wtedy gdy sama akcja wygeneruje b��d.
   * @param e
   */
  public void afterAction(ActionGlobalEvent e);
  
}
