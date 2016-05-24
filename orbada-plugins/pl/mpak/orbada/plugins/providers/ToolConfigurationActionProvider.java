package pl.mpak.orbada.plugins.providers;

import pl.mpak.orbada.plugins.providers.abs.ActionProvider;

/**
 * <p>Akcja zostanie dodana do listy narz�dzi w menu "Narz�dzia" (Tools)
 * @author akaluza
 */
public abstract class ToolConfigurationActionProvider extends ActionProvider {

  @Override
  public boolean isSharedProvider() {
    return true;
  }

  /**
   * <p>Pozwla okre�li� czy akcja ma opr�cz miejsca w menu "Narz�dzia" znale�� si� r�wnie� jako
   * przycisk na pasku przycisk�w narz�dzi.
   * @return
   */
  public boolean isButton() {
    return false;
  }
  
}
