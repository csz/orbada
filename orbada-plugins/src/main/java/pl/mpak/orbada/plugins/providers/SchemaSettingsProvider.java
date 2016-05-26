/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import pl.mpak.orbada.plugins.providers.abs.AbstractSettingsProvider;

/**
 *
 * @author akaluza
 */
public abstract class SchemaSettingsProvider extends AbstractSettingsProvider {

  /**
   * <p>Powinna zwr�ci� informacj� czy widok przeznaczony jest dla tego schematu.
   * <p>Wywo�ywane jest przy ka�dej zmianie sterownika, getSettingsComponent ukrywany jest lub
   * pokazywany w zale�no�ci od warto�ci zwracanej przez t� funkcj�
   * @param database
   * @return
   */
  public abstract boolean isForDriverType(String driverTypeName);

  /**
   * <p>Je�li maj� by� wykonane jakie� akcje przy akceptacji lub anulowaniu
   * ustawie� to zwracany komponent powinien implementowa� interfejs
   * <p>Najpierw wywo�ywana jest ta funkcja, a dopiero potem isForDriver
   * ISettingsComponent
   * @return
   */
  public abstract Component getSettingsComponent(String schemaId);



}
