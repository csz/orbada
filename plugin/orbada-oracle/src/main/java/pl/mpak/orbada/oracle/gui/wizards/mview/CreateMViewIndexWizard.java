package pl.mpak.orbada.oracle.gui.wizards.mview;

import pl.mpak.orbada.gui.comps.table.Table;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import pl.mpak.orbada.oracle.OrbadaOraclePlugin;
import pl.mpak.orbada.oracle.dbinfo.OracleMViewInfo;
import pl.mpak.orbada.oracle.gui.util.MViewColumnComboBoxModel;
import pl.mpak.orbada.oracle.gui.util.MViewComboBoxModel;
import pl.mpak.orbada.oracle.gui.util.MViewItemListener;
import pl.mpak.orbada.oracle.gui.util.TablespaceComboBoxModel;
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
public class CreateMViewIndexWizard extends SqlCodeWizardPanel {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager("oracle");

  private Database database;
  private String schemaName;
  private String viewName;
  private JComboBox comboColumnName;
  private JComboBox comboColumnOrder;
  
  public CreateMViewIndexWizard(Database database, String schemaName, String viewName) {
    this.database = database;
    this.schemaName = schemaName;
    this.viewName = viewName;
    initComponents();
    init();
  }
  
  private void init() {
    tableColumns.setRowHeight(20);
    
    tableColumns.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null}
      },
      new String [] {
        stringManager.getString("index-columns"), stringManager.getString("order")
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
    });

    comboTablespaces.setModel(new TablespaceComboBoxModel(database));
    comboColumnName = new JComboBox(new MViewColumnComboBoxModel(database));
    comboColumnOrder = new JComboBox(new DefaultComboBoxModel(new Object[] {"", "ASC", "DESC"}));
    TableColumn tc = tableColumns.getColumnModel().getColumn(0);
    tc.setCellEditor(new DefaultCellEditor(comboColumnName));
    tc = tableColumns.getColumnModel().getColumn(1);
    tc.setCellEditor(new DefaultCellEditor(comboColumnOrder));

    comboTables.addItemListener(new MViewItemListener() {
      public void itemChanged(OracleMViewInfo info) {
        ((MViewColumnComboBoxModel)comboColumnName.getModel()).change(info.getSchema().getName(), info.getName());
      }
    });
  }
  
  public void wizardShow() {
    ((TablespaceComboBoxModel)comboTablespaces.getModel()).change();
    ((MViewComboBoxModel)comboTables.getModel()).change(schemaName);
    ((MViewComboBoxModel)comboTables.getModel()).select(viewName, comboTables);
  }
  
  public String getDialogTitle() {
    return stringManager.getString("CreateMViewIndexWizard-dialog-title");
  }
  
  public String getTabTitle() {
    return stringManager.getString("CreateMViewIndexWizard-tab-title");
  }
  
  public String getSqlCode() {
    getResultMap().put("object_name", textName.getText());
    String fkColumns = "";
    if (radioColumns.isSelected()) {
      for (int i=0; i<tableColumns.getRowCount(); i++) {
        Object column = tableColumns.getValueAt(i, 0);
        Object columnOrder = tableColumns.getValueAt(i, 1);
        if (column != null) {
          if (!StringUtil.isEmpty(fkColumns)) {
            fkColumns = fkColumns +", ";
          }
          fkColumns = fkColumns +SQLUtil.createSqlName(column.toString());
          if (columnOrder != null && !StringUtil.isEmpty(columnOrder.toString())) {
            fkColumns = fkColumns +" " +columnOrder.toString();
          }
        }
      }
    }
    else if (radioExpression.isSelected()) {
      fkColumns = textExpression.getText();
    }
    
    return
      "CREATE " +comboType.getSelectedItem().toString() +
      " INDEX " +textName.getText() +
      " ON " +SQLUtil.createSqlName(comboTables.getSelectedItem().toString()) +
      "(" +fkColumns +")" +
      (checkParallel.isSelected() ? "\n PARALLEL " +textDegree.getText() : "") +
      (comboTablespaces.getSelectedItem() != null && checkTablespace.isSelected() ? "\n TABLESPACE " +SQLUtil.createSqlName(comboTablespaces.getSelectedItem().toString()) : "");
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
    group = new javax.swing.ButtonGroup();
    jLabel2 = new javax.swing.JLabel();
    comboTables = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    textName = new pl.mpak.sky.gui.swing.comp.TextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableColumns = new Table();
    buttonAddRow = new javax.swing.JButton();
    buttonRemoveRow = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    comboType = new javax.swing.JComboBox();
    radioColumns = new javax.swing.JRadioButton();
    radioExpression = new javax.swing.JRadioButton();
    textExpression = new pl.mpak.sky.gui.swing.comp.TextField();
    checkParallel = new javax.swing.JCheckBox();
    textDegree = new pl.mpak.sky.gui.swing.comp.TextField();
    comboTablespaces = new javax.swing.JComboBox();
    checkTablespace = new javax.swing.JCheckBox();

    cmAddRow.setActionCommandKey("cmAddRow");
    cmAddRow.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/add8.gif")); // NOI18N
    cmAddRow.setText(stringManager.getString("cmAdd-text")); // NOI18N
    cmAddRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmAddRowActionPerformed(evt);
      }
    });

    cmRemoveRow.setActionCommandKey("cmRemoveRow");
    cmRemoveRow.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/remove8.gif")); // NOI18N
    cmRemoveRow.setText(stringManager.getString("cmRemove-text")); // NOI18N
    cmRemoveRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRemoveRowActionPerformed(evt);
      }
    });

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("index-view-dd")); // NOI18N

    comboTables.setModel(new MViewComboBoxModel(database));

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("index-name-dd")); // NOI18N

    jScrollPane1.setMinimumSize(new java.awt.Dimension(24, 100));
    jScrollPane1.setViewportView(tableColumns);

    buttonAddRow.setAction(cmAddRow);
    buttonAddRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    buttonRemoveRow.setAction(cmRemoveRow);
    buttonRemoveRow.setMargin(new java.awt.Insets(2, 2, 2, 2));

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel4.setText(stringManager.getString("index-type-dd")); // NOI18N

    comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "UNIQUE", "BITMAP" }));

    group.add(radioColumns);
    radioColumns.setSelected(true);
    radioColumns.setText(stringManager.getString("columns")); // NOI18N

    group.add(radioExpression);
    radioExpression.setText(stringManager.getString("expression")); // NOI18N

    checkParallel.setText(stringManager.getString("parallel")); // NOI18N

    comboTablespaces.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Unikalny", "Bitmapowy" }));

    checkTablespace.setText(stringManager.getString("tablespace-name")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textExpression, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
          .addComponent(radioExpression)
          .addComponent(radioColumns)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(buttonRemoveRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(buttonAddRow, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
              .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(comboType, 0, 247, Short.MAX_VALUE)
              .addComponent(comboTables, 0, 247, Short.MAX_VALUE)
              .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addComponent(checkParallel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(checkTablespace)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboTablespaces, 0, 275, Short.MAX_VALUE)))
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
          .addComponent(comboTables, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(radioColumns)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(buttonAddRow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemoveRow))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(radioExpression)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textExpression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(checkParallel)
          .addComponent(textDegree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(checkTablespace)
          .addComponent(comboTablespaces, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(24, Short.MAX_VALUE))
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
  private javax.swing.JCheckBox checkParallel;
  private javax.swing.JCheckBox checkTablespace;
  private pl.mpak.sky.gui.swing.Action cmAddRow;
  private pl.mpak.sky.gui.swing.Action cmRemoveRow;
  private javax.swing.JComboBox comboTables;
  private javax.swing.JComboBox comboTablespaces;
  private javax.swing.JComboBox comboType;
  private javax.swing.ButtonGroup group;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JRadioButton radioColumns;
  private javax.swing.JRadioButton radioExpression;
  private Table tableColumns;
  private pl.mpak.sky.gui.swing.comp.TextField textDegree;
  private pl.mpak.sky.gui.swing.comp.TextField textExpression;
  private pl.mpak.sky.gui.swing.comp.TextField textName;
  // End of variables declaration//GEN-END:variables
  
}
