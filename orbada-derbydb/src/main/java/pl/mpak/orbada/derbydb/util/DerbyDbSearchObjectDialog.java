/*
 * DerbyDbSearchObjectDialog.java
 *
 * Created on 11 listopad 2007, 20:11
 */

package pl.mpak.orbada.derbydb.util;

import javax.swing.JComponent;

import pl.mpak.orbada.gui.comps.table.ViewTable;
import pl.mpak.orbada.derbydb.DerbyDbSql;
import pl.mpak.orbada.derbydb.OrbadaDerbyDbPlugin;
import pl.mpak.orbada.derbydb.procedures.FunctionsPanelView;
import pl.mpak.orbada.derbydb.procedures.ProceduresPanelView;
import pl.mpak.orbada.derbydb.services.DerbyDbFunctionsView;
import pl.mpak.orbada.derbydb.services.DerbyDbProceduresView;
import pl.mpak.orbada.derbydb.services.DerbyDbTablesView;
import pl.mpak.orbada.derbydb.services.DerbyDbViewsView;
import pl.mpak.orbada.derbydb.tables.TablesPanelView;
import pl.mpak.orbada.derbydb.views.ViewsPanelView;
import pl.mpak.orbada.plugins.IPerspectiveAccesibilities;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.sky.gui.swing.TableRowChangeKeyListener;
import pl.mpak.usedb.UseDBException;
import pl.mpak.usedb.gui.swing.QueryTableCellRenderer;
import pl.mpak.usedb.gui.swing.QueryTableColumn;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class DerbyDbSearchObjectDialog extends javax.swing.JDialog {
  
  private final static StringManager stringManager = StringManagerFactory.getStringManager(OrbadaDerbyDbPlugin.class);

  private IPerspectiveAccesibilities accesibilities;
  private int modalResult = ModalResult.OK;
  
  /**
   * Creates new form DerbyDbSearchObjectDialog
   * @param accesibilities
   */
  public DerbyDbSearchObjectDialog(IPerspectiveAccesibilities accesibilities) {
    super();
    this.accesibilities = accesibilities;
    initComponents();
    init();
  }
  
  public static void showDialog(IPerspectiveAccesibilities accesibilities) {
    DerbyDbSearchObjectDialog dialog = new DerbyDbSearchObjectDialog(accesibilities);
    dialog.setVisible(true);
  }
  
  private void init() {
    SwingUtil.centerWithinScreen(this);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    getRootPane().setDefaultButton(buttonOk);
    SwingUtil.addAction(this, cmSearch.getActionCommandKey(), cmSearch);
    textSearch.addKeyListener(new TableRowChangeKeyListener(tableObjects));
    
    tableObjects.getQuery().setDatabase(accesibilities.getDatabase());
    try {
      tableObjects.addColumn(new QueryTableColumn("schemaname", stringManager.getString("DerbyDbSearchObjectDialog-schema"), 150));
      tableObjects.addColumn(new QueryTableColumn("objectname", stringManager.getString("DerbyDbSearchObjectDialog-object-name"), 250, new QueryTableCellRenderer(java.awt.Font.BOLD)));
      tableObjects.addColumn(new QueryTableColumn("objecttype", stringManager.getString("DerbyDbSearchObjectDialog-type"), 150, new QueryTableCellRenderer(SwingUtil.Color.GREEN)));
      tableObjects.getQuery().setSqlText(DerbyDbSql.getObjectList() +"\n where x.objecttype <> 'INDEX' and (x.objectname like '%'||:objectname||'%' or x.objectname like '%'||upper(:objectname)||'%')");
    } catch (UseDBException ex) {
      ExceptionUtil.processException(ex);
    }
    
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        textSearch.requestFocusInWindow();
      }
    });
  }
  
  public void dispose() {
    tableObjects.getQuery().close();
    super.dispose();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmSearch = new pl.mpak.sky.gui.swing.Action();
    cmCancel = new pl.mpak.sky.gui.swing.Action();
    cmGo = new pl.mpak.sky.gui.swing.Action();
    jLabel1 = new javax.swing.JLabel();
    textSearch = new pl.mpak.sky.gui.swing.comp.TextField();
    jButton1 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableObjects = new ViewTable();
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();

    cmSearch.setActionCommandKey("cmSearch");
    cmSearch.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_MASK));
    cmSearch.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/orbada/derbydb/res/icons/go16.gif")); // NOI18N
    cmSearch.setTooltip(stringManager.getString("DerbyDbSearchObjectDialog-cmSearch-hint")); // NOI18N
    cmSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmSearchActionPerformed(evt);
      }
    });

    cmCancel.setActionCommandKey("cmCancel");
    cmCancel.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    cmCancel.setText(stringManager.getString("cmCancel-text")); // NOI18N
    cmCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmCancelActionPerformed(evt);
      }
    });

    cmGo.setActionCommandKey("cmGo");
    cmGo.setEnabled(false);
    cmGo.setText(stringManager.getString("cmGo-text")); // NOI18N
    cmGo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmGoActionPerformed(evt);
      }
    });

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(stringManager.getString("DerbyDbSearchObjectDialog-title")); // NOI18N
    setModal(true);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jLabel1.setText(stringManager.getString("DerbyDbSearchObjectDialog-object-name-dd")); // NOI18N

    jButton1.setAction(cmSearch);
    jButton1.setMargin(new java.awt.Insets(1, 1, 1, 1));

    jScrollPane1.setViewportView(tableObjects);

    buttonOk.setAction(cmGo);
    buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonOk.setPreferredSize(new java.awt.Dimension(75, 23));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonCancel.setPreferredSize(new java.awt.Dimension(75, 23));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton1)
          .addComponent(jLabel1)
          .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
  tableObjects.getQuery().close();
}//GEN-LAST:event_formWindowClosing
  
private void cmSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSearchActionPerformed
  if (!"".equals(textSearch.getText())) {
    try {
      tableObjects.getQuery().close();
      tableObjects.getQuery().paramByName("objectname").setString(textSearch.getText());
      tableObjects.getQuery().open();
      cmGo.setEnabled(!tableObjects.getQuery().eof());
      if (!tableObjects.getQuery().eof()) {
        tableObjects.changeSelection(0, tableObjects.getSelectedColumn());
      }
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
  }
}//GEN-LAST:event_cmSearchActionPerformed

private void cmGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmGoActionPerformed
  try {
    tableObjects.getQuery().getRecord(tableObjects.getSelectedRow());
    final String schemaName = tableObjects.getQuery().fieldByName("schemaname").getString();
    final String objectName = tableObjects.getQuery().fieldByName("objectname").getString();
    final String schemaType = tableObjects.getQuery().fieldByName("objecttype").getString();
    if ("TABLE".equals(schemaType) || "SYSTEM TABLE".equals(schemaType)) {
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          DerbyDbTablesView views[] = accesibilities.getApplication().getServiceArray(DerbyDbTablesView.class);
          if (views != null && views.length > 0) {
            TablesPanelView c = (TablesPanelView)accesibilities.getViewComponent(views[0]);
            if (c == null) {
              c = (TablesPanelView)accesibilities.createView(views[0]);
            }
            c.setCurrentSchemaName(schemaName);
            c.refresh(objectName);
            accesibilities.setSelectedView(c);
          }
        }
      });
    }
    else if ("VIEW".equals(schemaType)) {
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          DerbyDbViewsView tables[] = accesibilities.getApplication().getServiceArray(DerbyDbViewsView.class);
          if (tables != null && tables.length > 0) {
            ViewsPanelView c = (ViewsPanelView)accesibilities.getViewComponent(tables[0]);
            if (c == null) {
              c = (ViewsPanelView)accesibilities.createView(tables[0]);
            }
            c.setCurrentSchemaName(schemaName);
            c.refresh(objectName);
            accesibilities.setSelectedView(c);
          }
        }
      });
    } 
    else if ("PROCEDURE".equals(schemaType)) {
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          DerbyDbProceduresView procs[] = accesibilities.getApplication().getServiceArray(DerbyDbProceduresView.class);
          if (procs != null && procs.length > 0) {
            ProceduresPanelView c = (ProceduresPanelView)accesibilities.getViewComponent(procs[0]);
            if (c == null) {
              c = (ProceduresPanelView)accesibilities.createView(procs[0]);
            }
            c.setCurrentSchemaName(schemaName);
            c.refresh(objectName);
            accesibilities.setSelectedView(c);
          }
        }
      });
    }
    else if ("FUNCTION".equals(schemaType)) {
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          DerbyDbFunctionsView funcs[] = accesibilities.getApplication().getServiceArray(DerbyDbFunctionsView.class);
          if (funcs != null && funcs.length > 0) {
            FunctionsPanelView c = (FunctionsPanelView)accesibilities.getViewComponent(funcs[0]);
            if (c == null) {
              c = (FunctionsPanelView)accesibilities.createView(funcs[0]);
            }
            c.setCurrentSchemaName(schemaName);
            c.refresh(objectName);
            accesibilities.setSelectedView(c);
          }
        }
      });
    }
    modalResult = ModalResult.OK;
    dispose();
  } catch (Exception ex) {
    ExceptionUtil.processException(ex);
  }
}//GEN-LAST:event_cmGoActionPerformed

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  modalResult = ModalResult.CANCEL;
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmGo;
  private pl.mpak.sky.gui.swing.Action cmSearch;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private ViewTable tableObjects;
  private pl.mpak.sky.gui.swing.comp.TextField textSearch;
  // End of variables declaration//GEN-END:variables
  
}
