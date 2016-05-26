/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import pl.mpak.orbada.plugins.providers.abs.AbstractSettingsProvider;

/**
 * <p>Klasa us�gui ustawie� wtyczki.
 * <p>W getDescription() powinien by� opis ustawie�. Pokazany on b�dzie
 * w nag��wku panelu ustawie�.
 * @see ISettingsComponent
 * @author akaluza
 */
public abstract class SettingsProvider extends AbstractSettingsProvider {

  /**
   * <p>Je�li maj� by� wykonane jakie� akcje przy akceptacji lub anulowaniu
   * ustawie� to zwracany komponent powinien implementowa� interfejs
   * ISettingsComponent
   * @return
   */
  public abstract Component getSettingsComponent();

}
