package pl.mpak.orbada.mysql.gui.wizards;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import pl.mpak.orbada.gui.comps.table.Table;
import pl.mpak.orbada.mysql.OrbadaMySQLPlugin;
import pl.mpak.orbada.mysql.gui.util.TableColumnComboBoxModel;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
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
public class CreateUniqueTableConstraintWizardPanel extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager("mysql");

  private Database database;
  private String databaseName;
  private String tableName;
  private boolean constraint;
  private JComboBox comboColumnPrimaryKey;
  
  public CreateUniqueTableConstraintWizardPanel(Database database, String databaseName, String tableName, boolean constraint) {
    this.database = database;
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.constraint = constraint;
    initComponents();
    init();
  }
  
  private void init() {
    tableColumns.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, ""}
      },
      new String [] {
        stringManager.getString("key-columns"),
        stringManager.getString("order")
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class,
        java.lang.String.class
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
    });
    tableColumns.setRowHeight(20);

    textTableName.setText(SQLUtil.createSqlName(databaseName, tableName, database));
    
    comboColumnPrimaryKey = new JComboBox(new TableColumnComboBoxModel(database));
    TableColumn tc = tableColumns.getColumnModel().getColumn(0);
    tc.setCellEditor(new DefaultCellEditor(comboColumnPrimaryKey));

    tc = tableColumns.getColumnModel().getColumn(1);
    tc.setCellEditor(new DefaultCellEditor(new JComboBox(new DefaultComboBoxModel(new String[] {"", "ASC", "DESC"}))));
    tableColumns.getColumnModel().getColumn(0).setWidth(100);
  }
  
  public void wizardShow() {
    ((TableColumnComboBoxModel)comboColumnPrimaryKey.getModel()).change(databaseName, tableName);
  }
  
  public String getDialogTitle() {
    return stringManager.getString("CreateUniqueTableConstraintWizardPanel-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("CreateUniqueTableConstraintWizardPanel-tab-title");
  }
  
  public String getSqlCode() {
    String fkColumns = "";
    for (int i=0; i<tableColumns.getRowCount(); i++) {
      Object column = tableColumns.getValueAt(i, 0);
      if (column != null) {
        if (!StringUtil.isEmpty(fkColumns)) {
          fkColumns = fkColumns +", ";
        }
        fkColumns = fkColumns +SQLUtil.createSqlName(column.toString(), database);
        Object order = tableColumns.getValueAt(i, 1);
        if (order != null && !StringUtil.isEmpty(order.toString())) {
          fkColumns = fkColumns +" " +order;
        }
      }
    }
    
    return
      "ALTER TABLE " +SQLUtil.createSqlName(databaseName, tableName, database) +"\n" +
      " ADD" +(constraint ? " CONSTRAINT " +textName.getText() : "") +" UNIQUE INDEX " +textName.getText() +
      (StringUtil.isEmpty(comboUsing.getText().trim()) ? "" : (" USING " +comboUsing.getText())) +
      " (" +fkColumns +")" +
      (!StringUtil.isInteger(textBlockSize.getText()) ? "" : (" KEY_BLOCK_SIZE = " +textBlockSize.getText()));
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
    jLabel3 = new javax.swing.JLabel();
    textName = new pl.mpak.sky.gui.swing.comp.TextField();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableColumns = new Table();
    buttonAddRow = new javax.swing.JButton();
    buttonRemoveRow = new javax.swing.JButton();
    textTableName = new pl.mpak.sky.gui.swing.comp.TextField();
    jLabel4 = new javax.swing.JLabel();
    comboUsing = new pl.mpak.sky.gui.swing.comp.ComboBox();
    jLabel5 = new javax.swing.JLabel();
    textBlockSize = new pl.mpak.sky.gui.swing.comp.TextField();

    cmAddRow.setActionCommandKey("cmAddRow");
    cmAddRow.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/add8.gif"))); // NOI18N
    cmAddRow.setText(stringManager.getString("cmAddRow-text")); // NOI18N
    cmAddRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmAddRowActionPerformed(evt);
      }
    });

    cmRemoveRow.setActionCommandKey("cmRemoveRow");
    cmRemoveRow.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icons/remove8.gif"))); // NOI18N
    cmRemoveRow.setText(stringManager.getString("cmRemoveRow-text")); // NOI18N
    cmRemoveRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRemoveRowActionPerformed(evt);
      }
    });

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("index-table-dd")); // NOI18N

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("index-name-dd")); // NOI18N

    jLabel1.setText(stringManager.getString("unique-key-columns-dd")); // NOI18N

    jScrollPane1.setMinimumSize(new java.awt.Dimension(24, 100));
    jScrollPane1.setViewportView(tableColumns);

    buttonAddRow.setAction(cmAddRow);
    buttonAddRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonRemoveRow.setAction(cmRemoveRow);
    buttonRemoveRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    textTableName.setEditable(false);
    textTableName.setFocusable(false);

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel4.setText(stringManager.getString("key-kind-dd")); // NOI18N

    comboUsing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "BTREE", "HASH", "RTREE" }));

    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel5.setText(stringManager.getString("block-size-dd")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(buttonRemoveRow, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
              .addComponent(buttonAddRow, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
              .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(comboUsing, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
              .addComponent(textTableName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
              .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
              .addComponent(textBlockSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
          .addComponent(textTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(comboUsing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(textBlockSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(buttonAddRow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemoveRow))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
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
    ((DefaultTableModel)tableColumns.getModel()).addRow(new Object[] {null, ""});
  }
}//GEN-LAST:event_cmAddRowActionPerformed
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddRow;
  private javax.swing.JButton buttonRemoveRow;
  private pl.mpak.sky.gui.swing.Action cmAddRow;
  private pl.mpak.sky.gui.swing.Action cmRemoveRow;
  private pl.mpak.sky.gui.swing.comp.ComboBox comboUsing;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane1;
  private Table tableColumns;
  private pl.mpak.sky.gui.swing.comp.TextField textBlockSize;
  private pl.mpak.sky.gui.swing.comp.TextField textName;
  private pl.mpak.sky.gui.swing.comp.TextField textTableName;
  // End of variables declaration//GEN-END:variables
  
}
