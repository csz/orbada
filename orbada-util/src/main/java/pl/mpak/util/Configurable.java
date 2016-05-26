package pl.mpak.util;

/**
 * @author akaluza
 * <p>Interfejs do obs�ugi konfiguracji raczej w aplikacjach okienkowych.
 */
public interface Configurable {

  /**
   * <p>Wywo�ywane na rz�danie u�ytkownika, powinna pokaza� oknienko lub inny interfejs do konfiguracji.
   * @return
   */
  public boolean configure();
  
  /**
   * <p>Powinna zwr�ci� informacje o tym czy mo�na wywo�a� konfiguracj�.
   * @return
   */
  public boolean isConfig();
  
}
