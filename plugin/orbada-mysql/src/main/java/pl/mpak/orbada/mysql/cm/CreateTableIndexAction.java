/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.mysql.cm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.mpak.orbada.gui.ITabObjectInfo;
import pl.mpak.orbada.gui.comps.table.ViewTable;
import pl.mpak.orbada.mysql.OrbadaMySQLPlugin;
import pl.mpak.orbada.mysql.gui.tables.TableIndexesPanel;
import pl.mpak.orbada.mysql.gui.wizards.CreateTableIndexWizardPanel;
import pl.mpak.orbada.plugins.ComponentAction;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardDialog;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;

/**
 *
 * @author akaluza
 */
public class CreateTableIndexAction extends ComponentAction {

  private final StringManager stringManager = StringManagerFactory.getStringManager("mysql");

  public CreateTableIndexAction() {
    super();
    setText(stringManager.getString("CreateTableIndexAction-text"));
    setTooltip(stringManager.getString("CreateTableIndexAction-hint"));
    setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/index.gif"));
    setActionCommandKey("CreateUniqueTableConstraintAction");
    addActionListener(createActionListener());
  }

  private ActionListener createActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (getComponent() instanceof ViewTable) {
          TableIndexesPanel panel = (TableIndexesPanel)SwingUtil.getOwnerComponent(TableIndexesPanel.class, getComponent());
          if (!StringUtil.isEmpty(panel.getCurrentObjectName())) {
            ITabObjectInfo ip = (ITabObjectInfo)SwingUtil.getOwnerComponent(ITabObjectInfo.class, getComponent());
            try {
              String databaseName = panel.getCurrentSchemaName();
              String tableName = panel.getCurrentObjectName();
              if (SqlCodeWizardDialog.show(new CreateTableIndexWizardPanel(getDatabase(), databaseName, tableName), true) != null) {
                ip.refresh();
              }
            } catch (Exception ex) {
              MessageBox.show(stringManager.getString("error"), ex.getMessage(), ModalResult.OK);
            }
          }
        }
      }
    };
  }

  @Override
  public boolean isToolButton() {
    return true;
  }

}
