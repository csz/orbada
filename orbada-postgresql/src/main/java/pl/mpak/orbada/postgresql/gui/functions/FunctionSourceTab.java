/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mpak.orbada.postgresql.gui.functions;

import pl.mpak.orbada.gui.comps.AbsOrbadaSyntaxTextArea;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.postgresql.OrbadaPostgreSQLPlugin;
import pl.mpak.orbada.postgresql.util.SourceCreator;
import pl.mpak.orbada.universal.gui.tabbed.UniversalSourceTab;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author akaluza
 */
public class FunctionSourceTab extends UniversalSourceTab {

  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaPostgreSQLPlugin.class);

  public FunctionSourceTab(IViewAccesibilities accesibilities) {
    super(accesibilities);
  }

  @Override
  public String getPanelName() {
    return "postgresql-function-source";
  }

  @Override
  public void updateBody(AbsOrbadaSyntaxTextArea textArea) {
    new SourceCreator(getDatabase(), textArea).getSource(currentSchemaName, "FUNCTION", currentObjectName, null);
  }

  @Override
  public boolean isStorable() {
    return true;
  }

  @Override
  public String getTitle() {
    return stringManager.getString("source");
  }
  
}
