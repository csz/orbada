/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import javax.swing.Icon;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.providers.abs.AbstractSettingsProvider;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;
import pl.mpak.usedb.core.Database;

/**
 * <p>Klasa us�gui ustawie� perspektywy.
 * @see ISettingsComponent
 * @author akaluza
 */
public abstract class PerspectiveSettingsProvider extends AbstractSettingsProvider {

  /**
   * <p>Powinna zwr�ci� informacj� czy widok przeznaczony jest dla tej bazy danych
   * @param database 
   * @return 
   */
  public abstract boolean isForDatabase(Database database);

  /**
   * <p>Je�li maj� by� wykonane jakie� akcje przy akceptacji lub anulowaniu
   * ustawie� to zwracany komponent powinien implementowa� interfejs
   * ISettingsComponent
   * @return
   */
  public abstract Component getSettingsComponent(Database database);

}
