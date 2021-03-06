/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.oracle.gui.javas;

import java.awt.Component;
import pl.mpak.orbada.gui.OrbadaTabbedPane;
import pl.mpak.orbada.oracle.gui.ObjectUsedByPanel;
import pl.mpak.orbada.oracle.gui.ObjectUsingPanel;
import pl.mpak.orbada.plugins.IViewAccesibilities;

/**
 *
 * @author akaluza
 */
public class JavaSourceTabbedPane extends OrbadaTabbedPane {
  
  public JavaSourceTabbedPane(IViewAccesibilities accesibilities) {
    super("JAVA SOURCE",
      new Component[] {
        new JavaSourcePanel(accesibilities, "JAVA SOURCE"),
        new JavaSourceErrorsPanel(accesibilities, "JAVA SOURCE"),
        new JavaMethodsPanel(accesibilities),
        new JavaFieldsPanel(accesibilities),
        new JavaImplementsPanel(accesibilities),
        new JavaInnersPanel(accesibilities),
        new JavaSourceGrantsPanel(accesibilities),
        new ObjectUsedByPanel(accesibilities, "JAVA CLASS"),
        new ObjectUsingPanel(accesibilities, "JAVA CLASS"),
        new JavaDetailsPanel(accesibilities, "JAVA SOURCE")
    });
  }
  
}
