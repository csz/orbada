/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import pl.mpak.usedb.core.Database;

/**
 *
 * @author akaluza
 */
public interface IGadgetAccesibilities {

  public Database getDatabase();

  public IApplication getApplication();
  
  public IPerspectiveAccesibilities getPerspectiveAccesibilities();
  
  /**
   * <p>Powinno by� wywo�ane je�li co� w gad�ecie si� zmieni�o, np tytu�
   */
  public void updated();
  
}
