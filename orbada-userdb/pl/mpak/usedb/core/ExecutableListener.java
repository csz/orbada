package pl.mpak.usedb.core;

import java.sql.SQLException;
import java.util.EventListener;
import java.util.EventObject;

/**
 * @author akaluza
 * <p>Listener w kt�rym zwraca si� informacj� o tym czy polecenie e.getSource() mo�e by� wykonane.
 * <p>Listener wywo�ywany jest w Command.execeute() lub Query.open() jeszcze przed jakimkolwiek innym sprawdzeniem lub zda�eniem.
 */
public interface ExecutableListener extends EventListener {

  /**
   * <p>Powinno zwr�ci� informacj� czy polecenie mo�e by� wykonane czy nie.
   * @param e
   * @return
   * @throws SQLException
   */
  public boolean canExecute(EventObject e) throws SQLException;

}
