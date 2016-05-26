/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import javax.swing.UnsupportedLookAndFeelException;
import pl.mpak.orbada.plugins.IApplication;

/**
 * <p>Interfejs do tworzenia klasy uruchamiaj�cej Look And Feel</p>
 * <p>W klasie tej nie mo�na u�ywa� ustawie� standardowych gdy� po��czenie z baz� danych jeszcze nie istnieje</p>
 * @author akaluza
 */
public interface ILookAndFeelStarter {

  public void setApplication(IApplication application);

  /**
   * <p>Powinna pobra� sobie parametry i przygotowa� system LookAndFeel.</p>
   * <p>Wywo�ywana jest przy uruchomieniu programu.
   */
  public void start() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException;

}
