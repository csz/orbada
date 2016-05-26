package pl.mpak.orbada.universal.gui.tables;

import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.mpak.orbada.gui.comps.table.ViewTable;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.gui.ITabObjectInfo;
import pl.mpak.orbada.universal.OrbadaUniversalPlugin;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.gui.swing.QueryTableCellRenderer;
import pl.mpak.usedb.gui.swing.QueryTableColumn;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;

/**
 *
 * @author  akaluza
 */
public class TableImportedKeysPanel extends javax.swing.JPanel implements ITabObjectInfo {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager("universal");

  private IViewAccesibilities accesibilities;
  private String currentCatalogName;
  private String currentSchemaName = "";
  private String currentTableName = "";
  private boolean isView;
  private boolean requestRefresh = false;
  private boolean closing = false;
  
  /** Creates new form TableColumns
   * @param accesibilities
   */
  public TableImportedKeysPanel(IViewAccesibilities accesibilities) {
    this(accesibilities, false);
  }
  
  public TableImportedKeysPanel(IViewAccesibilities accesibilities, boolean isView) {
    this.accesibilities = accesibilities;
    this.isView = isView;
    initComponents();
    init();
  }
  
  private void init() {
    tableImportedKeys.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int rowIndex = tableImportedKeys.getSelectedRow();
        if (rowIndex >= 0 && tableImportedKeys.getQuery().isActive()) {
          try {
            tableImportedKeys.getQuery().getRecord(rowIndex);
          } catch (Exception ex) {
            ExceptionUtil.processException(ex);
          }
        }
      }
    });
    
    tableImportedKeys.getQuery().setDatabase(getDatabase());
    try {
      tableImportedKeys.addColumn(new QueryTableColumn("FK_NAME", stringManager.getString("key-name"), 150));
      tableImportedKeys.addColumn(new QueryTableColumn("FKCOLUMN_NAME", stringManager.getString("column"), 150, new QueryTableCellRenderer(java.awt.Font.BOLD)));
      tableImportedKeys.addColumn(new QueryTableColumn("PK_NAME", stringManager.getString("ref-key-name"), 150));
      tableImportedKeys.addColumn(new QueryTableColumn("PKTABLE_SCHEM", stringManager.getString("ref-schema"), 150));
      tableImportedKeys.addColumn(new QueryTableColumn("PKTABLE_NAME", stringManager.getString("ref-table"), 150, new QueryTableCellRenderer(java.awt.Font.BOLD)));
      tableImportedKeys.addColumn(new QueryTableColumn("PKCOLUMN_NAME", stringManager.getString("ref-column"), 150));
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
  }
  
  public Database getDatabase() {
    return accesibilities.getDatabase();
  }
  
  public void refresh() {
    try {
      requestRefresh = false;
      tableImportedKeys.getQuery().close();
      tableImportedKeys.getQuery().setResultSet(getDatabase().getMetaData().getImportedKeys(currentCatalogName, currentSchemaName, currentTableName));
      if (!tableImportedKeys.getQuery().isEmpty()) {
        tableImportedKeys.changeSelection(0, 0);
      }
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
  }
  
  public void refresh(String catalogName, String schemaName, String objectName) {
    if (!StringUtil.equals(catalogName, currentCatalogName) || !StringUtil.equals(schemaName, currentSchemaName) || !currentTableName.equals(objectName) || requestRefresh) {
      currentCatalogName = catalogName;
      currentSchemaName = schemaName;
      currentTableName = objectName;
      if (isVisible()) {
        refresh();
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
    closing = true;
    tableImportedKeys.getQuery().close();
    accesibilities = null;
  }
  
  public String getTitle() {
    return stringManager.getString("TableImportedKeysPanel-title");
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmRefresh = new pl.mpak.sky.gui.swing.Action();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableImportedKeys = new ViewTable();
    statusBarImportedKeys = new pl.mpak.usedb.gui.swing.QueryTableStatusBar();
    toolBarImportedKeys = new javax.swing.JToolBar();
    buttonRefresh = new pl.mpak.sky.gui.swing.comp.ToolButton();

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

    jScrollPane1.setViewportView(tableImportedKeys);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    statusBarImportedKeys.setShowFieldType(false);
    statusBarImportedKeys.setShowOpenTime(false);
    statusBarImportedKeys.setTable(tableImportedKeys);
    add(statusBarImportedKeys, java.awt.BorderLayout.PAGE_END);

    toolBarImportedKeys.setFloatable(false);
    toolBarImportedKeys.setRollover(true);

    buttonRefresh.setAction(cmRefresh);
    buttonRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarImportedKeys.add(buttonRefresh);

    add(toolBarImportedKeys, java.awt.BorderLayout.NORTH);
  }// </editor-fold>//GEN-END:initComponents

  private void cmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmRefreshActionPerformed
    refresh();
}//GEN-LAST:event_cmRefreshActionPerformed
  
private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
  if (requestRefresh && !closing) {
    refresh();
  }
}//GEN-LAST:event_formComponentShown


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonRefresh;
  private pl.mpak.sky.gui.swing.Action cmRefresh;
  private javax.swing.JScrollPane jScrollPane1;
  private pl.mpak.usedb.gui.swing.QueryTableStatusBar statusBarImportedKeys;
  private ViewTable tableImportedKeys;
  private javax.swing.JToolBar toolBarImportedKeys;
  // End of variables declaration//GEN-END:variables
  
}
