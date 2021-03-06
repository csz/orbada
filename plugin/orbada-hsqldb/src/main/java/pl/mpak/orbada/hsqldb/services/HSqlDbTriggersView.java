/*
 * DerbyDbObjectsView.java
 * 
 * Created on 2007-10-28, 16:42:01
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.hsqldb.services;

import java.awt.Component;
import javax.swing.Icon;
import pl.mpak.orbada.hsqldb.OrbadaHSqlDbPlugin;
import pl.mpak.orbada.hsqldb.gui.triggers.TriggersPanelView;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.plugins.providers.ViewProvider;
import pl.mpak.usedb.core.Database;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author akaluza
 */
public class HSqlDbTriggersView extends ViewProvider {

  private StringManager stringManager = StringManagerFactory.getStringManager("hsqldb");

  public Component createView(IViewAccesibilities accesibilities) {
    return new TriggersPanelView(accesibilities);
  }
  
  public String getPublicName() {
    return stringManager.getString("HSqlDbTriggersView-public-name");
  }
  
  public String getViewId() {
    return "orbada-hsqldb-triggers-view";
  }
  
  public Icon getIcon() {
    return null;
  }

  public boolean isForDatabase(Database database) {
    if (database == null) {
      return false;
    }
    return OrbadaHSqlDbPlugin.hsqlDbDriverType.equals(database.getDriverType());
  }

  public String getDescription() {
    return stringManager.getString("HSqlDbTriggersView-description");
  }

  public String getGroupName() {
    return OrbadaHSqlDbPlugin.hsqlDbDriverType;
  }
  
}
