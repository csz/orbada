package pl.mpak.orbada.sqlscripts.gui;

import java.beans.IntrospectionException;
import javax.swing.JComponent;

import pl.mpak.orbada.gui.comps.OrbadaSyntaxTextArea;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.db.InternalDatabase;
import pl.mpak.orbada.sqlscripts.OrbadaSqlScriptsPlugin;
import pl.mpak.orbada.sqlscripts.db.SqlScriptRecord;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.usedb.UseDBException;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.gui.RecordLink;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class SqlScriptEditDialog extends javax.swing.JDialog {
  
  private final StringManager stringManager = StringManagerFactory.getStringManager("sql-scripts");

  private String oss_id;
  private String dtp_id;
  private int modalResult = ModalResult.NONE;
  private SqlScriptRecord sqlScript;
  private RecordLink templateLink;
  private Database forDatabase;
  
  public SqlScriptEditDialog(Database forDatabase, String oss_id, String dtp_id) throws IntrospectionException, UseDBException {
    super(SwingUtil.getRootFrame());
    this.oss_id = oss_id;
    this.dtp_id = dtp_id;
    this.forDatabase = forDatabase;
    initComponents();
    init();
  }
  
  public static int showDialog(Database forDatabase, String oss_id, String dtp_id) throws IntrospectionException, UseDBException {
    SqlScriptEditDialog dialog = new SqlScriptEditDialog(forDatabase, oss_id, dtp_id);
    dialog.setVisible(true);
    return dialog.getModalResult();
  }
  
  private void init() throws IntrospectionException, UseDBException {
    SwingUtil.centerWithinScreen(this);

    templateLink = new RecordLink();
    templateLink.add("OSS_NAME", textName);
    templateLink.add("OSS_SCRIPT", textSqlScript);

    if (oss_id != null) {
      sqlScript = new SqlScriptRecord(InternalDatabase.get(), oss_id);
    }
    else {
      sqlScript = new SqlScriptRecord(InternalDatabase.get());
      sqlScript.setUsrId(Application.get().getUserId());
      sqlScript.setDtpId(dtp_id);
    }
    templateLink.updateComponents(sqlScript);

    textSqlScript.setDatabase(forDatabase);
    
    getRootPane().setDefaultButton(buttonOk);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
  }
  
  public int getModalResult() {
    return modalResult;
  }
  
  @Override
  public void dispose() {
    super.dispose();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmOk = new pl.mpak.sky.gui.swing.Action();
    cmCancel = new pl.mpak.sky.gui.swing.Action();
    jLabel1 = new javax.swing.JLabel();
    textName = new pl.mpak.sky.gui.swing.comp.TextField();
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();
    textSqlScript = new OrbadaSyntaxTextArea();

    cmOk.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
    cmOk.setText(stringManager.getString("ok-action")); // NOI18N
    cmOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmOkActionPerformed(evt);
      }
    });

    cmCancel.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    cmCancel.setText(stringManager.getString("cancel-action")); // NOI18N
    cmCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmCancelActionPerformed(evt);
      }
    });

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(stringManager.getString("edit-sql-script")); // NOI18N
    setModal(true);

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setLabelFor(textName);
    jLabel1.setText(stringManager.getString("name-collon")); // NOI18N

    buttonOk.setAction(cmOk);
    buttonOk.setPreferredSize(new java.awt.Dimension(75, 23));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setPreferredSize(new java.awt.Dimension(75, 23));

    jLabel3.setLabelFor(textName);
    jLabel3.setText(stringManager.getString("template-collon")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textSqlScript, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
          .addComponent(jLabel3))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textSqlScript, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  modalResult = ModalResult.CANCEL;
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed

private void cmOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmOkActionPerformed
 try {
    templateLink.updateRecord(sqlScript);
    if (sqlScript.isChanged()) {
      if (oss_id == null) {
        sqlScript.applyInsert();
      }
      else {
        sqlScript.applyUpdate();
      }
    }
    modalResult = ModalResult.OK;
    dispose();
  } catch (Exception ex) {
    MessageBox.show(this, stringManager.getString("error"), ex.getMessage(), new int[] {ModalResult.OK});
  }
}//GEN-LAST:event_cmOkActionPerformed
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmOk;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel3;
  private pl.mpak.sky.gui.swing.comp.TextField textName;
  private OrbadaSyntaxTextArea textSqlScript;
  // End of variables declaration//GEN-END:variables
  
}