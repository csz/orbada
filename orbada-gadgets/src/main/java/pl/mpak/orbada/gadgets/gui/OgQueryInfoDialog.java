/*
 * OgQueryInfoDialog.java
 *
 * Created on 15 październik 2008, 18:37
 */

package pl.mpak.orbada.gadgets.gui;

import java.beans.IntrospectionException;
import javax.swing.JComponent;

import pl.mpak.orbada.gui.comps.OrbadaSyntaxTextArea;
import pl.mpak.orbada.gadgets.OrbadaGadgetsPlugin;
import pl.mpak.orbada.gadgets.db.OgQueryInfo;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.usedb.UseDBException;
import pl.mpak.usedb.gui.RecordLink;
import pl.mpak.usedb.gui.linkreq.FieldRequeiredNotNull;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class OgQueryInfoDialog extends javax.swing.JDialog {

  private StringManager stringManager = StringManagerFactory.getStringManager(OrbadaGadgetsPlugin.class);

  private IApplication application;
  private int modalResult = ModalResult.NONE;
  private OgQueryInfo info;
  private RecordLink dataLink;
  private String gqi_id;
  
  /** Creates new form OgQueryInfoDialog */
  public OgQueryInfoDialog(IApplication application, String gqi_id) throws IntrospectionException, UseDBException {
    super(SwingUtil.getRootFrame(), true);
    this.application = application;
    this.gqi_id = gqi_id;
    initComponents();
    init();
  }
  
  public static String show(IApplication application, String gqi_id) throws IntrospectionException, UseDBException {
    OgQueryInfoDialog dialog = new OgQueryInfoDialog(application, gqi_id);
    dialog.setVisible(true);
    return (dialog.modalResult == ModalResult.OK ? dialog.gqi_id : null);
  }
  
  private void init() throws IntrospectionException, UseDBException {
    try {
      queryDriverTypes.open();
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }

    dataLink = new RecordLink();
    dataLink.add("GQI_NAME", editName, new FieldRequeiredNotNull(stringManager.getString("name-of-information-query")));
    dataLink.add("GQI_DTP_ID", comboDriverType, "selectedItem");
    dataLink.add("GQI_SQL", editSql, new FieldRequeiredNotNull(stringManager.getString("sql-command")));
    
    if (gqi_id != null) {
      info = new OgQueryInfo(application.getOrbadaDatabase(), gqi_id);
    } else {
      info = new OgQueryInfo(application.getOrbadaDatabase());
    }
    dataLink.updateComponents(info);
    checkAllUsers.setSelected(info.getUsrId() == null);
    if (!application.isUserAdmin()) {
      checkAllUsers.setEnabled(false);
      checkAllUsers.setSelected(true);
    }
    
    getRootPane().setDefaultButton(buttonOk);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    
    SwingUtil.centerWithinScreen(this);
  }
  
  @Override
  public void dispose() {
    queryDriverTypes.close();
    super.dispose();
  }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmOk = new pl.mpak.sky.gui.swing.Action();
    cmCancel = new pl.mpak.sky.gui.swing.Action();
    queryDriverTypes = new pl.mpak.usedb.core.Query();
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    editName = new pl.mpak.sky.gui.swing.comp.TextField();
    jLabel2 = new javax.swing.JLabel();
    comboDriverType = new pl.mpak.usedb.gui.swing.QueryComboBox();
    jLabel3 = new javax.swing.JLabel();
    editSql = new OrbadaSyntaxTextArea();
    checkAllUsers = new javax.swing.JCheckBox();

    cmOk.setActionCommandKey("cmOk");
    cmOk.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
    cmOk.setText(stringManager.getString("cmOk-text")); // NOI18N
    cmOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmOkActionPerformed(evt);
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

    queryDriverTypes.setDatabase(application.getOrbadaDatabase());
    try {
      queryDriverTypes.setSqlText("select * from driver_types order by dtp_name");
    } catch (pl.mpak.usedb.UseDBException e1) {
      e1.printStackTrace();
    }

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(stringManager.getString("OgQueryInfoDialog-title")); // NOI18N

    buttonOk.setAction(cmOk);
    buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonOk.setPreferredSize(new java.awt.Dimension(75, 23));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonCancel.setPreferredSize(new java.awt.Dimension(75, 23));

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setText(stringManager.getString("query-name-dd")); // NOI18N

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText(stringManager.getString("for-driver-dd")); // NOI18N

    comboDriverType.setDisplayField("DTP_NAME");
    comboDriverType.setKeyField("DTP_ID");
    comboDriverType.setQuery(queryDriverTypes);

    jLabel3.setText(stringManager.getString("information-sql-command-dd")); // NOI18N

    checkAllUsers.setText(stringManager.getString("for-all-users-dd")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(editSql, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(checkAllUsers)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
            .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(editName, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboDriverType, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
          .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(comboDriverType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(editSql, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(checkAllUsers))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private void cmOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmOkActionPerformed
try {
    dataLink.updateRecord(info);
    if (checkAllUsers.isSelected()) {
      info.setUsrId(null);
    }
    else {
      info.setUsrId(application.getUserId());
    }
    if (info.isChanged()) {
      if (gqi_id == null) {
        info.applyInsert();
        gqi_id = info.getId();
      } else {
        info.applyUpdate();
      }
    }
    modalResult = ModalResult.OK;
    dispose();
  } catch (Exception ex) {
    ExceptionUtil.processException(ex);
    MessageBox.show(this, stringManager.getString("error"), ex.getMessage(), new int[] {ModalResult.OK});
  }
}//GEN-LAST:event_cmOkActionPerformed

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  modalResult = ModalResult.CANCEL;
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private javax.swing.JCheckBox checkAllUsers;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmOk;
  private pl.mpak.usedb.gui.swing.QueryComboBox comboDriverType;
  private pl.mpak.sky.gui.swing.comp.TextField editName;
  private OrbadaSyntaxTextArea editSql;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private pl.mpak.usedb.core.Query queryDriverTypes;
  // End of variables declaration//GEN-END:variables

}
