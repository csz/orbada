package pl.mpak.orbada.oracle.gui.wizards.table;

import pl.mpak.orbada.oracle.OrbadaOraclePlugin;
import pl.mpak.orbada.oracle.gui.util.TableComboBoxModel;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
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
public class DropTableWizard extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager("oracle");

  private Database database;
  private String schemaName;
  private String tableName;
  
  public DropTableWizard(Database database, String schemaName, String tableName) {
    this.database = database;
    this.schemaName = schemaName;
    this.tableName = tableName;
    initComponents();
    init();
  }
  
  private void init() {
    comboTables.setModel(new TableComboBoxModel(database));
    checkPurge.setEnabled("true".equalsIgnoreCase(database.getUserProperties().getProperty("Ora10+")));
  }
  
  public void wizardShow() {
    ((TableComboBoxModel)comboTables.getModel()).change(schemaName);
    ((TableComboBoxModel)comboTables.getModel()).select(tableName, comboTables);
  }
  
  public String getDialogTitle() {
    return stringManager.getString("DropTableWizard-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("DropTableWizard-tab-title");
  }
  
  public String getSqlCode() {
    return
      "DROP TABLE " +SQLUtil.createSqlName(schemaName, comboTables.getSelectedItem().toString()) +
      (checkConstraintCascade.isSelected() ? " CASCADE CONSTRAINTS" : "") +
      (checkPurge.isSelected() ? " PURGE" : "");
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
    checkPurge = new javax.swing.JCheckBox();
    checkConstraintCascade = new javax.swing.JCheckBox();

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("table-dd")); // NOI18N

    checkPurge.setText(stringManager.getString("skip-bin")); // NOI18N

    checkConstraintCascade.setText(stringManager.getString("delete-relations-cascade")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(checkPurge)
          .addComponent(checkConstraintCascade)
          .addComponent(comboTables, 0, 196, Short.MAX_VALUE))
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
        .addComponent(checkConstraintCascade)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(checkPurge)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox checkConstraintCascade;
  private javax.swing.JCheckBox checkPurge;
  private javax.swing.JComboBox comboTables;
  private javax.swing.JLabel jLabel2;
  // End of variables declaration//GEN-END:variables
  
}
