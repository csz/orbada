package pl.mpak.orbada.mysql.gui.wizards;

import pl.mpak.orbada.gui.comps.table.Table;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;
import pl.mpak.orbada.mysql.OrbadaMySQLPlugin;
import pl.mpak.usedb.core.Database;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;

/**
 *
 * @author  akaluza
 */
public class ParameterDefinitionWizard extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaMySQLPlugin.class);

  private Database database;
  private int paramCount = 0;
  
  public ParameterDefinitionWizard(Database database) {
    this.database = database;
    initComponents();
    init();
  }
  
  private void init() {
    tableParameters.setRowHeight(20);
    
    paramCount++;
    tableParameters.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {"PARAMETR_1", "VARCHAR(1000)", ""}
      },
      new String [] {
        stringManager.getString("parameter-name"), stringManager.getString("parameter-type"), stringManager.getString("parameter-mode")
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };

      @Override
      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
    });
    tableParameters.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboDataTypes));
    tableParameters.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboInOut));
    tableParameters.getColumnModel().getColumn(0).setPreferredWidth(100);
    tableParameters.getColumnModel().getColumn(1).setPreferredWidth(150);
    tableParameters.getColumnModel().getColumn(2).setPreferredWidth(50);
  }
  
  public void wizardShow() {
  }
  
  public String getDialogTitle() {
    return stringManager.getString("ParameterDefinitionWizard-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("ParameterDefinitionWizard-tab-title");
  }
  
  public String getSqlCode() {
    StringBuffer columns = new StringBuffer();
    boolean addednl = false;
    for (int i=0; i<tableParameters.getRowCount(); i++) {
      Object name = tableParameters.getValueAt(i, 0);
      Object dataType = tableParameters.getValueAt(i, 1);
      Object inOutValue = tableParameters.getValueAt(i, 2);
      if (name != null && dataType != null) {
        if (columns.length() > 0) {
          if (!addednl) {
            columns.insert(0, "\n  ");
            addednl = true;
          }
          columns.append(",\n  ");
        }
        if (!StringUtil.isEmpty(inOutValue.toString().trim())) {
          columns.append(inOutValue.toString().trim() +" ");
        }
        columns.append(name);
        columns.append(" " +dataType);
      }
    }
    return columns.toString();
  }
  
  public boolean execute() {
    return false;
  }
  
  private void cancelEdit() {
    tableParameters.getColumnModel().getColumn(1).getCellEditor().cancelCellEditing();
    tableParameters.getColumnModel().getColumn(2).getCellEditor().cancelCellEditing();
    tableParameters.getColumnModel().getColumn(4).getCellEditor().cancelCellEditing();
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
    cmMoveDown = new pl.mpak.sky.gui.swing.Action();
    cmMoveUp = new pl.mpak.sky.gui.swing.Action();
    comboDataTypes = new javax.swing.JComboBox();
    comboInOut = new javax.swing.JComboBox();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableParameters = new Table();
    buttonAddRow = new javax.swing.JButton();
    buttonRemoveRow = new javax.swing.JButton();
    buttonMoveUp = new javax.swing.JButton();
    buttonMoveDown = new javax.swing.JButton();

    cmAddRow.setActionCommandKey("cmAddRow"); // NOI18N
    cmAddRow.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/mpak/res/icons/add8.gif"))); // NOI18N
    cmAddRow.setText(stringManager.getString("cmAddRow-text")); // NOI18N
    cmAddRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmAddRowActionPerformed(evt);
      }
    });

    cmRemoveRow.setActionCommandKey("cmRemoveRow"); // NOI18N
    cmRemoveRow.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/mpak/res/icons/remove8.gif"))); // NOI18N
    cmRemoveRow.setText(stringManager.getString("cmRemoveRow-text")); // NOI18N
    cmRemoveRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRemoveRowActionPerformed(evt);
      }
    });

    cmMoveDown.setActionCommandKey("cmMoveDown"); // NOI18N
    cmMoveDown.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/mpak/res/icons/down10.gif"))); // NOI18N
    cmMoveDown.setText(stringManager.getString("cmMoveDown-text")); // NOI18N
    cmMoveDown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveDownActionPerformed(evt);
      }
    });

    cmMoveUp.setActionCommandKey("cmMoveUp"); // NOI18N
    cmMoveUp.setSmallIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/mpak/res/icons/up10.gif"))); // NOI18N
    cmMoveUp.setText(stringManager.getString("cmMoveUp-text")); // NOI18N
    cmMoveUp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveUpActionPerformed(evt);
      }
    });

    comboDataTypes.setEditable(true);
    comboDataTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VARCHAR(100)", "VARCHAR(1000)", "VARCHAR(4000)", "SMALLINT", "MEDIUMINT", "INTEGER", "BIGINT", "FLOAT(M,D)", "DOUBLE(M,D)", "DOUBLE PRECISION(M,D)", "DECIMAL(M,D)", "DATE", "DATETIME", "TIMESTAMP", "CHAR(M)", "VARCHAR(M)", "BINARY(M)", "VARBINARY(M)", "TINYBLOB", "TINYTEXT", "BLOB(M)", "TEXT(M)", "MEDIUMBLOB", "MEDIUMTEXT", "LONGBLOB", "LONGTEXT" }));

    comboInOut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "IN", "OUT", "IN OUT" }));

    setPreferredSize(new java.awt.Dimension(600, 300));

    jScrollPane1.setMinimumSize(new java.awt.Dimension(24, 100));

    tableParameters.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jScrollPane1.setViewportView(tableParameters);

    buttonAddRow.setAction(cmAddRow);
    buttonAddRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonRemoveRow.setAction(cmRemoveRow);
    buttonRemoveRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonMoveUp.setAction(cmMoveUp);
    buttonMoveUp.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonMoveDown.setAction(cmMoveDown);
    buttonMoveDown.setMargin(new java.awt.Insets(2, 2, 2, 2));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(buttonMoveUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
          .addComponent(buttonRemoveRow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
          .addComponent(buttonAddRow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
          .addComponent(buttonMoveDown, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(buttonAddRow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemoveRow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveUp)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveDown)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

private void cmRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmRemoveRowActionPerformed
  if (tableParameters.getRowCount() > 0 && tableParameters.getSelectedRow() >= 0) {
    cancelEdit();
    ((DefaultTableModel)tableParameters.getModel()).removeRow(tableParameters.getSelectedRow());
  }
}//GEN-LAST:event_cmRemoveRowActionPerformed

private void cmAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmAddRowActionPerformed
  if (tableParameters.getRowCount() == 0 || tableParameters.getValueAt(tableParameters.getRowCount() -1, 0) != null) {
    paramCount++;
    ((DefaultTableModel)tableParameters.getModel()).addRow(new Object[] {"PARAMETR_" +paramCount, "VARCHAR(1000)", ""});
    tableParameters.changeSelection(tableParameters.getRowCount() -1, tableParameters.getRowCount() -1);
  }
}//GEN-LAST:event_cmAddRowActionPerformed

private void cmMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveDownActionPerformed
  if (tableParameters.getSelectedRow() >= 0 && tableParameters.getSelectedRow() < tableParameters.getRowCount() -1) {
    cancelEdit();
    ((DefaultTableModel)tableParameters.getModel()).moveRow(tableParameters.getSelectedRow(), tableParameters.getSelectedRow(), tableParameters.getSelectedRow() +1);
    tableParameters.changeSelection(tableParameters.getSelectedRow() +1, tableParameters.getSelectedRow() +1);
  }
}//GEN-LAST:event_cmMoveDownActionPerformed

private void cmMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveUpActionPerformed
  if (tableParameters.getSelectedRow() > 0) {
    cancelEdit();
    ((DefaultTableModel)tableParameters.getModel()).moveRow(tableParameters.getSelectedRow(), tableParameters.getSelectedRow(), tableParameters.getSelectedRow() -1);
    tableParameters.changeSelection(tableParameters.getSelectedRow() -1, tableParameters.getSelectedRow() -1);
  }
}//GEN-LAST:event_cmMoveUpActionPerformed
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddRow;
  private javax.swing.JButton buttonMoveDown;
  private javax.swing.JButton buttonMoveUp;
  private javax.swing.JButton buttonRemoveRow;
  private pl.mpak.sky.gui.swing.Action cmAddRow;
  private pl.mpak.sky.gui.swing.Action cmMoveDown;
  private pl.mpak.sky.gui.swing.Action cmMoveUp;
  private pl.mpak.sky.gui.swing.Action cmRemoveRow;
  private javax.swing.JComboBox comboDataTypes;
  private javax.swing.JComboBox comboInOut;
  private javax.swing.JScrollPane jScrollPane1;
  private Table tableParameters;
  // End of variables declaration//GEN-END:variables
}
