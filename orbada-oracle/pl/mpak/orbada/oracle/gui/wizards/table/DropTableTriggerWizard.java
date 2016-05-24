package pl.mpak.orbada.oracle.gui.wizards.table;

import pl.mpak.orbada.oracle.OrbadaOraclePlugin;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
import pl.mpak.orbada.oracle.dbinfo.OracleTableInfo;
import pl.mpak.orbada.oracle.gui.util.TableComboBoxModel;
import pl.mpak.orbada.oracle.gui.util.TableItemListener;
import pl.mpak.orbada.oracle.gui.util.TriggerComboBoxModel;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.util.SQLUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class DropTableTriggerWizard extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaOraclePlugin.class);

  private Database database;
  private String schemaName;
  private String objectName;
  private String triggerName;
  
  public DropTableTriggerWizard(Database database, String schemaName, String objectName, String triggerName) {
    this.database = database;
    this.schemaName = schemaName;
    this.objectName = objectName;
    this.triggerName = triggerName;
    initComponents();
    init();
  }
  
  private void init() {
    comboTriggers.setModel(new TriggerComboBoxModel(database));
    comboTables.setModel(new TableComboBoxModel(database));
    comboTables.addItemListener(new TableItemListener() {
      OracleTableInfo lastTable;
      public void itemChanged(OracleTableInfo info) {
        ((TriggerComboBoxModel)comboTriggers.getModel()).change(info.getSchema().getName(), info.getName(), "TABLE");
      }
    });
  }
  
  public void wizardShow() {
    ((TableComboBoxModel)comboTables.getModel()).change(schemaName);
    ((TableComboBoxModel)comboTables.getModel()).select(objectName, comboTables);
    ((TriggerComboBoxModel)comboTriggers.getModel()).select(triggerName, comboTriggers);
  }
  
  public String getDialogTitle() {
    return stringManager.getString("DropTableTriggerWizard-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("DropTableTriggerWizard-tab-title");
  }
  
  public String getSqlCode() {
    return "DROP TRIGGER " +SQLUtil.createSqlName(schemaName, comboTriggers.getSelectedItem().toString());
  }
  
  public boolean execute() {
    try {
      database.executeCommand(getSqlCode());
      return true;
    } catch (Exception ex) {
      MessageBox.show(this, stringManager.getString("error"), ex.getMessage(), ModalResult.OK, MessageBox.ERROR);
      return false;
    }
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel2 = new javax.swing.JLabel();
    comboTables = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    comboTriggers = new javax.swing.JComboBox();

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("table-dd")); // NOI18N

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("trigger-list-dd")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboTables, 0, 196, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboTriggers, 0, 196, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(comboTables, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(comboTriggers, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox comboTables;
  private javax.swing.JComboBox comboTriggers;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  // End of variables declaration//GEN-END:variables
  
}