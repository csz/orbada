package pl.mpak.orbada.mysql.gui.views;

import java.awt.Component;
import orbada.gui.OrbadaTabbedPane;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import orbada.gui.ContentPanel;

/**
 *
 * @author akaluza
 */
public class ViewTabbedPane extends OrbadaTabbedPane {
  
  public ViewTabbedPane(IViewAccesibilities accesibilities) {
    super("VIEW",
      new Component[] {
        new ViewColumnsPanel(accesibilities),
        new ViewUtilsPanel(accesibilities),
        new ContentPanel(accesibilities),
        new ViewSourcePanel(accesibilities)
    });
  }
  
}