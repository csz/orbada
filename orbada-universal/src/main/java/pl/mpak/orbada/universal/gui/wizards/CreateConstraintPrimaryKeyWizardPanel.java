package pl.mpak.orbada.universal.gui.wizards;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import pl.mpak.orbada.gui.comps.table.Table;
import pl.mpak.orbada.plugins.dbinfo.jdbc.JdbcDbSchemaInfo;
import pl.mpak.orbada.plugins.dbinfo.jdbc.JdbcDbTableInfo;
import pl.mpak.orbada.universal.OrbadaUniversalPlugin;
import pl.mpak.orbada.universal.gui.util.TableColumnComboBoxModel;
import pl.mpak.orbada.universal.gui.util.TableComboBoxModel;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.util.SQLUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;

/**
 *
 * @author  akaluza
 */
public class CreateConstraintPrimaryKeyWizardPanel extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaUniversalPlugin.class);

  private Database database;
  private String schemaName;
  private String tableName;
  private String columnName;
  private JComboBox comboColumnPrimaryKey;
  
  public CreateConstraintPrimaryKeyWizardPanel(Database database, String schemaName, String tableName) {
    this(database, schemaName, tableName, null);
  }
  
  public CreateConstraintPrimaryKeyWizardPanel(Database database, String schemaName, String tableName, String columnName) {
    this.database = database;
    this.schemaName = schemaName;
    this.tableName = tableName;
    this.columnName = columnName;
    initComponents();
    init();
  }

  private void init() {
    tableColumns.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {columnName}
      },
      new String [] {
        stringManager.getString("key-columns")
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
    });
    tableColumns.setRowHeight(20);
    
    comboColumnPrimaryKey = new JComboBox(new TableColumnComboBoxModel(database));
    TableColumn tc = tableColumns.getColumnModel().getColumn(0);
    tc.setCellEditor(new DefaultCellEditor(comboColumnPrimaryKey));

    comboTablesConstraints.addItemListener(new ItemListener() {
      JdbcDbTableInfo lastTable;
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() != null && e.getItem() instanceof JdbcDbTableInfo) {
          JdbcDbTableInfo ti = (JdbcDbTableInfo)e.getItem();
          if (!ti.equals(lastTable) || lastTable == null) {
            JdbcDbSchemaInfo schema = ti.getSchema();
            ((TableColumnComboBoxModel)comboColumnPrimaryKey.getModel()).change(schema == null ? null : schema.getName(), ti.getName());
            lastTable = ti;
          }
        }
      }
    });
  }
  
  public void wizardShow() {
    ((TableComboBoxModel)comboTablesConstraints.getModel()).change(schemaName);
    ((TableComboBoxModel)comboTablesConstraints.getModel()).select(tableName, comboTablesConstraints);
  }
  
  public String getDialogTitle() {
    return stringManager.getString("CreateConstraintPrimaryKeyWizardPanel-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("CreateConstraintPrimaryKeyWizardPanel-tab-title");
  }
  
  public String getSqlCode() {
    String fkColumns = "";
    for (int i=0; i<tableColumns.getRowCount(); i++) {
      Object column = tableColumns.getValueAt(i, 0);
      if (column != null) {
        if (!StringUtil.isEmpty(fkColumns)) {
          fkColumns = fkColumns +", ";
        }
        fkColumns = fkColumns +SQLUtil.createSqlName(column.toString());
      }
    }
    
    return
      "ALTER TABLE " +SQLUtil.createSqlName(schemaName, comboTablesConstraints.getSelectedItem().toString()) +"\n" +
      "  ADD CONSTRAINT " +textName.getText() +"\n" +
      "  PRIMARY KEY (" +fkColumns +")";
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

    cmAddRow = new pl.mpak.sky.gui.swing.Action();
    cmRemoveRow = new pl.mpak.sky.gui.swing.Action();
    jLabel2 = new javax.swing.JLabel();
    comboTablesConstraints = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    textName = new pl.mpak.sky.gui.swing.comp.TextField();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableColumns = new Table();
    buttonAddRow = new javax.swing.JButton();
    buttonRemoveRow = new javax.swing.JButton();

    cmAddRow.setActionCommandKey("cmAddRow");
    cmAddRow.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/add8.gif")); // NOI18N
    cmAddRow.setText(stringManager.getString("cmAddRow-text")); // NOI18N
    cmAddRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmAddRowActionPerformed(evt);
      }
    });

    cmRemoveRow.setActionCommandKey("cmRemoveRow");
    cmRemoveRow.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/remove8.gif")); // NOI18N
    cmRemoveRow.setText(stringManager.getString("cmRemoveRow-text")); // NOI18N
    cmRemoveRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRemoveRowActionPerformed(evt);
      }
    });

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("constraint-table-dd")); // NOI18N

    comboTablesConstraints.setModel(new TableComboBoxModel(database));

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("constraint-name-dd")); // NOI18N

    jLabel1.setText(stringManager.getString("primary-key-columns")); // NOI18N

    jScrollPane1.setMinimumSize(new java.awt.Dimension(24, 100));
    jScrollPane1.setViewportView(tableColumns);

    buttonAddRow.setAction(cmAddRow);
    buttonAddRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonRemoveRow.setAction(cmRemoveRow);
    buttonRemoveRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
              .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(comboTablesConstraints, javax.swing.GroupLayout.Alignment.TRAILING, 0, 198, Short.MAX_VALUE)
              .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(buttonRemoveRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(buttonAddRow, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addComponent(jLabel1))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(comboTablesConstraints, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(buttonAddRow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemoveRow))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

private void cmRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmRemoveRowActionPerformed
  if (tableColumns.getRowCount() > 1) {
    tableColumns.getColumnModel().getColumn(0).getCellEditor().cancelCellEditing();
    ((DefaultTableModel)tableColumns.getModel()).removeRow(tableColumns.getRowCount() -1);
  }
}//GEN-LAST:event_cmRemoveRowActionPerformed

private void cmAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmAddRowActionPerformed
  if (tableColumns.getValueAt(tableColumns.getRowCount() -1, 0) != null) {
    ((DefaultTableModel)tableColumns.getModel()).addRow(new Object[] {null, null});
  }
}//GEN-LAST:event_cmAddRowActionPerformed
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddRow;
  private javax.swing.JButton buttonRemoveRow;
  private pl.mpak.sky.gui.swing.Action cmAddRow;
  private pl.mpak.sky.gui.swing.Action cmRemoveRow;
  private javax.swing.JComboBox comboTablesConstraints;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JScrollPane jScrollPane1;
  private Table tableColumns;
  private pl.mpak.sky.gui.swing.comp.TextField textName;
  // End of variables declaration//GEN-END:variables
  
}
