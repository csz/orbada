package pl.mpak.orbada.hsqldb.gui.tables;

import java.awt.Dialog;
import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.mpak.orbada.gui.comps.table.ViewTable;
import pl.mpak.orbada.gui.ITabObjectInfo;
import pl.mpak.orbada.gui.cm.ComponentActionsAction;
import pl.mpak.orbada.hsqldb.OrbadaHSqlDbPlugin;
import pl.mpak.orbada.hsqldb.Sql;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.universal.gui.filter.SqlFilter;
import pl.mpak.orbada.universal.gui.filter.SqlFilterDef;
import pl.mpak.orbada.universal.gui.filter.SqlFilterDefComponent;
import pl.mpak.orbada.universal.gui.filter.SqlFilterDialog;
import pl.mpak.orbada.universal.gui.wizards.CreateIndexWizardPanel;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardDialog;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.gui.swing.QueryTableCellRenderer;
import pl.mpak.usedb.gui.swing.QueryTableColumn;
import pl.mpak.usedb.util.SQLUtil;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.timer.Timer;

/**
 *
 * @author  akaluza
 */
public class TableIndexesPanel extends javax.swing.JPanel implements ITabObjectInfo {
  
  private StringManager stringManager = StringManagerFactory.getStringManager(OrbadaHSqlDbPlugin.class);

  private IViewAccesibilities accesibilities;
  private String currentSchemaName = "";
  private String currentTableName = "";
  private boolean requestRefresh = false;
  private SqlFilter filter;
  
  private Timer timer;
  
  /** Creates new form TableIndexesPanel 
   * @param accesibilities 
   */
  public TableIndexesPanel(IViewAccesibilities accesibilities) {
    this.accesibilities = accesibilities;
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
    
    tableIndexes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int rowIndex = tableIndexes.getSelectedRow();
        if (rowIndex >= 0 && tableIndexes.getQuery().isActive()) {
          try {
            tableIndexes.getQuery().getRecord(rowIndex);
          } catch (Exception ex) {
            ExceptionUtil.processException(ex);
          }
        }
      }
    });
    
    tableIndexes.getQuery().setDatabase(getDatabase());
    try {
      tableIndexes.addColumn(new QueryTableColumn("ordinal_position", stringManager.getString("pos"), 30));
      tableIndexes.addColumn(new QueryTableColumn("index_name", stringManager.getString("index-name"), 200, new QueryTableCellRenderer(java.awt.Font.BOLD)));
      tableIndexes.addColumn(new QueryTableColumn("column_name", stringManager.getString("column-name"), 150));
      tableIndexes.addColumn(new QueryTableColumn("pk_index", stringManager.getString("pk-index"), 50));
      tableIndexes.addColumn(new QueryTableColumn("unique_index", stringManager.getString("unique"), 50));
      tableIndexes.addColumn(new QueryTableColumn("index_type", stringManager.getString("index-type"), 70));
      SqlFilterDef def = new SqlFilterDef();
      def.add(new SqlFilterDefComponent("idx.index_name", stringManager.getString("index-name"), (String[])null));
      def.add(new SqlFilterDefComponent("idx.column_name", stringManager.getString("column-name"), (String[])null));
      def.add(new SqlFilterDefComponent("idx.non_unique = 'YES'", stringManager.getString("uniquiness")));
      filter = new SqlFilter(
        accesibilities.getApplication().getSettings(getDatabase().getUserProperties().getProperty("schemaId"), "hsqldb-table-indexes-filter"),
        cmFilter, buttonFilter, 
        def);
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
    new ComponentActionsAction(getDatabase(), tableIndexes, buttonActions, menuActions, "hsqldb-table-indexes-actions");
  }
  
  public Database getDatabase() {
    return accesibilities.getDatabase();
  }
  
  public String getTitle() {
    return stringManager.getString("TableIndexesPanel-title");
  }
  
  public void refresh() {
    try {
      requestRefresh = false;
      tableIndexes.getQuery().close();
      tableIndexes.getQuery().setSqlText(Sql.getIndexList(filter.getSqlText()));
      tableIndexes.getQuery().paramByName("schema_name").setString(currentSchemaName);
      tableIndexes.getQuery().paramByName("table_name").setString(currentTableName);
      tableIndexes.getQuery().open();
      if (!tableIndexes.getQuery().isEmpty()) {
        tableIndexes.changeSelection(0, 0);
      }
    } catch (Exception ex) {
      MessageBox.show(stringManager.getString("error"), ex.getMessage(), ModalResult.OK);
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
    tableIndexes.getQuery().close();
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
    cmFilter = new pl.mpak.sky.gui.swing.Action();
    cmDropIndex = new pl.mpak.sky.gui.swing.Action();
    cmCreateIndex = new pl.mpak.sky.gui.swing.Action();
    menuActions = new javax.swing.JPopupMenu();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableIndexes = new ViewTable();
    statusBarIndexes = new pl.mpak.usedb.gui.swing.QueryTableStatusBar();
    jPanel1 = new javax.swing.JPanel();
    toolBarIndexes = new javax.swing.JToolBar();
    buttonRefresh = new pl.mpak.sky.gui.swing.comp.ToolButton();
    buttonFilter = new pl.mpak.sky.gui.swing.comp.ToolButton();
    jSeparator2 = new javax.swing.JSeparator();
    buttonCreateIndex = new pl.mpak.sky.gui.swing.comp.ToolButton();
    jSeparator1 = new javax.swing.JSeparator();
    buttonDtopIndex = new pl.mpak.sky.gui.swing.comp.ToolButton();
    jSeparator3 = new javax.swing.JToolBar.Separator();
    buttonActions = new pl.mpak.sky.gui.swing.comp.ToolButton();

    cmRefresh.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/refresh16.gif")); // NOI18N
    cmRefresh.setText(stringManager.getString("cmRefresh-text")); // NOI18N
    cmRefresh.setTooltip(stringManager.getString("cmRefresh-hint")); // NOI18N
    cmRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmRefreshActionPerformed(evt);
      }
    });

    cmFilter.setActionCommandKey("cmFilter");
    cmFilter.setText(stringManager.getString("cmFilter-text")); // NOI18N
    cmFilter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmFilterActionPerformed(evt);
      }
    });

    cmDropIndex.setActionCommandKey("cmDropIndex");
    cmDropIndex.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/trash.gif")); // NOI18N
    cmDropIndex.setText(stringManager.getString("cmDropIndex-text")); // NOI18N
    cmDropIndex.setTooltip(stringManager.getString("cmDropIndex-hint")); // NOI18N
    cmDropIndex.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmDropIndexActionPerformed(evt);
      }
    });

    cmCreateIndex.setActionCommandKey("cmCreateIndex");
    cmCreateIndex.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/create_index16.gif")); // NOI18N
    cmCreateIndex.setText(stringManager.getString("cmCreateIndex-text")); // NOI18N
    cmCreateIndex.setTooltip(stringManager.getString("cmCreateIndex-hint")); // NOI18N
    cmCreateIndex.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmCreateIndexActionPerformed(evt);
      }
    });

    addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentShown(java.awt.event.ComponentEvent evt) {
        formComponentShown(evt);
      }
    });
    setLayout(new java.awt.BorderLayout());

    jScrollPane1.setViewportView(tableIndexes);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    statusBarIndexes.setShowFieldType(false);
    statusBarIndexes.setShowOpenTime(false);
    statusBarIndexes.setTable(tableIndexes);
    add(statusBarIndexes, java.awt.BorderLayout.PAGE_END);

    jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

    toolBarIndexes.setFloatable(false);
    toolBarIndexes.setRollover(true);

    buttonRefresh.setAction(cmRefresh);
    buttonRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarIndexes.add(buttonRefresh);

    buttonFilter.setAction(cmFilter);
    buttonFilter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonFilter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarIndexes.add(buttonFilter);

    jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
    toolBarIndexes.add(jSeparator2);

    buttonCreateIndex.setAction(cmCreateIndex);
    buttonCreateIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonCreateIndex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarIndexes.add(buttonCreateIndex);

    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
    toolBarIndexes.add(jSeparator1);

    buttonDtopIndex.setAction(cmDropIndex);
    buttonDtopIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonDtopIndex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBarIndexes.add(buttonDtopIndex);
    toolBarIndexes.add(jSeparator3);
    toolBarIndexes.add(buttonActions);

    jPanel1.add(toolBarIndexes);

    add(jPanel1, java.awt.BorderLayout.NORTH);
  }// </editor-fold>//GEN-END:initComponents

private void cmCreateIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCreateIndexActionPerformed
  SqlCodeWizardDialog.show(new CreateIndexWizardPanel(getDatabase(), currentSchemaName, currentTableName), true);
  timer.restart();
}//GEN-LAST:event_cmCreateIndexActionPerformed

  private void cmDropIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmDropIndexActionPerformed
  if (tableIndexes.getSelectionModel().getLeadSelectionIndex() >= 0) {
    try {
      tableIndexes.getQuery().getRecord(tableIndexes.getSelectionModel().getLeadSelectionIndex());
      String objectName = SQLUtil.createSqlName(currentSchemaName, tableIndexes.getQuery().fieldByName("index_name").getString(), getDatabase());
      if (MessageBox.show((Dialog)null, stringManager.getString("deleting"), String.format(stringManager.getString("TableIndexesPanel-drop-index-q"), new Object[] {objectName}), ModalResult.YESNO) == ModalResult.YES) {
        getDatabase().createCommand("drop index " +objectName, true);
        timer.restart();
      }
    } catch (Exception ex) {
      MessageBox.show(stringManager.getString("error"), ex.getMessage(), ModalResult.OK);
      ExceptionUtil.processException(ex);
    }
  }
}//GEN-LAST:event_cmDropIndexActionPerformed

private void cmFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmFilterActionPerformed
  if (SqlFilterDialog.show(filter)) {
    refresh();
  }
}//GEN-LAST:event_cmFilterActionPerformed

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
  refresh(null, currentSchemaName, currentTableName);
}//GEN-LAST:event_formComponentShown

private void cmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmRefreshActionPerformed
  refresh();
}//GEN-LAST:event_cmRefreshActionPerformed
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonActions;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonCreateIndex;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonDtopIndex;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonFilter;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonRefresh;
  private pl.mpak.sky.gui.swing.Action cmCreateIndex;
  private pl.mpak.sky.gui.swing.Action cmDropIndex;
  private pl.mpak.sky.gui.swing.Action cmFilter;
  private pl.mpak.sky.gui.swing.Action cmRefresh;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JToolBar.Separator jSeparator3;
  private javax.swing.JPopupMenu menuActions;
  private pl.mpak.usedb.gui.swing.QueryTableStatusBar statusBarIndexes;
  private ViewTable tableIndexes;
  private javax.swing.JToolBar toolBarIndexes;
  // End of variables declaration//GEN-END:variables
  
}
