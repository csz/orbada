package pl.mpak.orbada.hsqldb.gui.tables;

import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.mpak.orbada.gui.comps.table.ViewTable;
import pl.mpak.orbada.gui.ITabObjectInfo;
import pl.mpak.orbada.gui.cm.ComponentActionsAction;
import pl.mpak.orbada.hsqldb.OrbadaHSqlDbPlugin;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.gui.swing.QueryTableCellRenderer;
import pl.mpak.usedb.gui.swing.QueryTableColumn;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.timer.Timer;

/**
 *
 * @author  akaluza
 */
public class TableExportedKeysPanel extends javax.swing.JPanel implements ITabObjectInfo {
  
  private StringManager stringManager = StringManagerFactory.getStringManager("hsqldb");

  private IViewAccesibilities accesibilities;
  private String currentSchemaName = "";
  private String currentTableName = "";
  private boolean isView;
  private boolean requestRefresh = false;
  
  private Timer timer;
  
  /** Creates new form TableColumns
   * @param accesibilities
   */
  public TableExportedKeysPanel(IViewAccesibilities accesibilities) {
    this(accesibilities, false);
  }
  
  public TableExportedKeysPanel(IViewAccesibilities accesibilities, boolean isView) {
    this.accesibilities = accesibilities;
    this.isView = isView;
    initComponents();
    init();
  }
  
  private void init() {
    timer = new Timer(250) {
      {
        setEnabled(false);
      }
      public void run() {
        setEnabled(false);
        refresh();
      }
    };
    OrbadaHSqlDbPlugin.getRefreshQueue().add(timer);
    
    tableExportedKeys.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int rowIndex = tableExportedKeys.getSelectedRow();
        if (rowIndex >= 0 && tableExportedKeys.getQuery().isActive()) {
          try {
            tableExportedKeys.getQuery().getRecord(rowIndex);
          } catch (Exception ex) {
            ExceptionUtil.processException(ex);
          }
        }
      }
    });
    
    tableExportedKeys.getQuery().setDatabase(getDatabase());
    try {
      tableExportedKeys.addColumn(new QueryTableColumn("FK_NAME", stringManager.getString("foreign-key-name"), 150));
      tableExportedKeys.addColumn(new QueryTableColumn("FKTABLE_SCHEM", stringManager.getString("foreign-key-table-schema"), 150));
      tableExportedKeys.addColumn(new QueryTableColumn("FKTABLE_NAME", stringManager.getString("foreign-key-table-name"), 150, new QueryTableCellRenderer(java.awt.Font.BOLD)));
      tableExportedKeys.addColumn(new QueryTableColumn("FKCOLUMN_NAME", stringManager.getString("foreign-key-column-name"), 150));
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
    new ComponentActionsAction(getDatabase(), tableExportedKeys, buttonActions, menuActions, "hsqldb-table-exported-keys-actions");
  }
  
  public Database getDatabase() {
    return accesibilities.getDatabase();
  }
  
  public String getTitle() {
    return stringManager.getString("TableExportedKeysPanel-title");
  }
  
  public void refresh() {
    try {
      requestRefresh = false;
      tableExportedKeys.getQuery().close();
      tableExportedKeys.getQuery().setResultSet(getDatabase().getMetaData().getExportedKeys(null, currentSchemaName, currentTableName));
      if (!tableExportedKeys.getQuery().isEmpty()) {
        tableExportedKeys.changeSelection(0, 0);
      }
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
  }
  
  public void refresh(String catalogName, String schemaName, String objectName) {
    if (!currentSchemaName.equals(schemaName) || !currentTableName.equals(objectName) || requestRefresh) {
      currentSchemaName = schemaName;
      currentTableName = objectName;
      if (isVisible()) {
        timer.restart();
      }
      else {
        requestRefresh = true;
      }
    }
  }
  
  @Override
  public boolean canClose() {
    return true;
  }

  public void close() throws IOException {
    timer.cancel();
    tableExportedKeys.getQuery().close();
    accesibilities = null;
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmRefresh = new pl.mpak.sky.gui.swing.Action();
    menuActions = new javax.swing.JPopupMenu();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableExportedKeys = new ViewTable();
    statusBarColumns = new pl.mpak.usedb.gui.swing.QueryTableStatusBar();
    toolBarColumns = new javax.swing.JToolBar();
    buttonRefresh = new pl.mpak.sky.gui.swing.comp.ToolButton();
    jSeparator1 = new javax.swing.JToolBar.Separator();
    buttonActions = new pl.mpak.sky.gui.swing.comp.ToolButton();

    cmRefresh.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/refresh16.gif")); // NOI18N
    cmRefresh.setText(stringManager.getString("cmRefresh-text")); // NOI18N
    cmRefresh.setTooltip(stringManager.getString("cmRefresh-hint")); // NOI18N
    cmRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRefreshActionPerformed(evt);
      }
    });

    addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentShown(java.awt.event.ComponentEvent evt) {
        formComponentShown(evt);
      }
    });
    setLayout(new java.awt.BorderLayout());

    jScrollPane1.setViewportView(tableExportedKeys);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    statusBarColumns.setShowFieldType(false);
    statusBarColumns.setShowOpenTime(false);
    statusBarColumns.setTable(tableExportedKeys);
    add(statusBarColumns, java.awt.BorderLayout.PAGE_END);

    toolBarColumns.setFloatable(false);
    toolBarColumns.setRollover(true);

    buttonRefresh.setAction(cmRefresh);
    buttonRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarColumns.add(buttonRefresh);
    toolBarColumns.add(jSeparator1);
    toolBarColumns.add(buttonActions);

    add(toolBarColumns, java.awt.BorderLayout.NORTH);
  }// </editor-fold>//GEN-END:initComponents

  private void cmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmRefreshActionPerformed
    refresh();
}//GEN-LAST:event_cmRefreshActionPerformed
  
private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
  refresh(null, currentSchemaName, currentTableName);
}//GEN-LAST:event_formComponentShown


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonActions;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonRefresh;
  private pl.mpak.sky.gui.swing.Action cmRefresh;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToolBar.Separator jSeparator1;
  private javax.swing.JPopupMenu menuActions;
  private pl.mpak.usedb.gui.swing.QueryTableStatusBar statusBarColumns;
  private ViewTable tableExportedKeys;
  private javax.swing.JToolBar toolBarColumns;
  // End of variables declaration//GEN-END:variables
  
}
