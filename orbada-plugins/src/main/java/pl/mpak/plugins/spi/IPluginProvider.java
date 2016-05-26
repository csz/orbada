package pl.mpak.plugins.spi;

/**
 * <p>Interfejs prowidera og�lnego zastosowania.
 * <p>Klasy tego interfejsu s� �adowane automatycznie podczas wyszukiwania plugin-�w
 * dla ka�dego z PluginManager-a
 * <p>Niezale�nie od tego czy Plugin jako taki zostanie za�adowany to ten interfejs
 * znajdzie si� na og�lnej li�cie serwis�w PluginManager-a
 * @see PluginManager.getServices
 * @author akaluza
 * 
 */
public interface IPluginProvider {
  
  /**
   * <p>Czy klasa tego interfejsu mo�e by� wsp�dzielona. Czy ma powsta� jedna
   * instancja tego interfejsu
   * @return
   */
  public boolean isSharedProvider();
  
  /**
   * <p>Nazwa grupy dostawcy, w niekt�rych miejscach b�dzie to potrzebne do zgrupwania
   * np w Menu. W ustawieniach grupy takie r�wnie� mog� si� pojawi�.
   * @return
   */
  public String getGroupName();

}
