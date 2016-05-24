package pl.mpak.orbada.snippets.gui;

import java.beans.IntrospectionException;
import javax.swing.JComponent;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.snippets.OrbadaSnippetsPlugin;
import pl.mpak.orbada.snippets.db.SnippetRecord;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.usedb.UseDBException;
import pl.mpak.usedb.gui.FieldLinkType;
import pl.mpak.usedb.gui.RecordLink;
import pl.mpak.usedb.gui.linkreq.FieldRequeiredNotNull;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class SnippetEditDialog extends javax.swing.JDialog {

  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaSnippetsPlugin.class);

  private IApplication application;
  private int modalResult = ModalResult.NONE;
  private SnippetRecord snippet;
  private RecordLink dataLink;
  private String snp_id;
  
  public SnippetEditDialog(IApplication application, String snp_id, SnippetRecord snippet) throws IntrospectionException, UseDBException {
    super(SwingUtil.getRootFrame());
    this.application = application;
    this.snp_id = snp_id;
    this.snippet = snippet;
    initComponents();
    init();
  }
  
  public static String show(IApplication application, String snp_id) throws IntrospectionException, UseDBException {
    SnippetEditDialog dialog = new SnippetEditDialog(application, snp_id, null);
    dialog.setVisible(true);
    return (dialog.modalResult == ModalResult.OK ? dialog.snp_id : null);
  }
  
  public static String show(IApplication application, String snp_id, SnippetRecord snippet) throws IntrospectionException, UseDBException {
    SnippetEditDialog dialog = new SnippetEditDialog(application, snp_id, snippet);
    dialog.setVisible(true);
    return (dialog.modalResult == ModalResult.OK ? dialog.snp_id : null);
  }
  
  private void init() throws IntrospectionException, UseDBException {
    try {
      queryDriverTypes.open();
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }

    dataLink = new RecordLink();
    dataLink.add("SNP_NAME", editName, new FieldRequeiredNotNull(stringManager.getString("snippet-name")));
    dataLink.add("SNP_EDITOR", comboEditor, "selectedItem");
    dataLink.add("SNP_DTP_ID", comboDriverType, "selectedItem");
    dataLink.add("SNP_ACTIVE", checkActive, "selected", FieldLinkType.Boolean_TF);
    dataLink.add("SNP_IMMEDIATE", checkImmediate, "selected", FieldLinkType.Boolean_TF);
    dataLink.add("SNP_CODE", editCode, new FieldRequeiredNotNull(stringManager.getString("snippet-code")));
    
    if (snippet == null) {
      if (snp_id != null) {
        snippet = new SnippetRecord(application.getOrbadaDatabase(), snp_id);
      } else {
        snippet = new SnippetRecord(application.getOrbadaDatabase());
      }
    }
    dataLink.updateComponents(snippet);
    checkAllUsers.setSelected(snippet.getUsrId() == null);
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
        jLabel3 = new javax.swing.JLabel();
        editCode = new pl.mpak.orbada.gui.comps.OrbadaSyntaxTextArea();
        checkAllUsers = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        comboDriverType = new pl.mpak.usedb.gui.swing.QueryComboBox();
        comboEditor = new pl.mpak.sky.gui.swing.comp.ComboBox();
        checkActive = new javax.swing.JCheckBox();
        checkImmediate = new javax.swing.JCheckBox();

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
            queryDriverTypes.setSqlText("select dtp_id, dtp_name from driver_types\nunion all select null, 'Any/All' from driver_types where dtp_id = (select min(dtp_id) from driver_types)\norder by dtp_name");
        } catch (pl.mpak.usedb.UseDBException e1) {
            e1.printStackTrace();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(stringManager.getString("SnippetEditDialog-title")); // NOI18N
        setModal(true);

        buttonOk.setAction(cmOk);
        buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
        buttonOk.setPreferredSize(new java.awt.Dimension(85, 25));

        buttonCancel.setAction(cmCancel);
        buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
        buttonCancel.setPreferredSize(new java.awt.Dimension(85, 25));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(stringManager.getString("snippet-name-dd")); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(stringManager.getString("snippet-editor-dd")); // NOI18N

        jLabel3.setText(stringManager.getString("code-information")); // NOI18N

        checkAllUsers.setText(stringManager.getString("for-all-users")); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText(stringManager.getString("for-driver-dd")); // NOI18N

        comboDriverType.setDisplayField("DTP_NAME");
        comboDriverType.setKeyField("DTP_ID");
        comboDriverType.setQuery(queryDriverTypes);

        comboEditor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sql", "java" }));

        checkActive.setText(stringManager.getString("snippet-active")); // NOI18N

        checkImmediate.setText(stringManager.getString("snippet-immediate-info")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkAllUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editName, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkActive)
                            .addComponent(comboDriverType, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addComponent(checkImmediate)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
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
                    .addComponent(comboEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboDriverType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkActive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkImmediate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCode, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
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
    dataLink.updateRecord(snippet);
    if (checkAllUsers.isSelected()) {
      snippet.setUsrId(null);
    }
    else {
      snippet.setUsrId(application.getUserId());
    }
    if (snippet.isChanged()) {
      if (snp_id == null) {
        snippet.applyInsert();
        snp_id = snippet.getId();
      } else {
        snippet.applyUpdate();
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
    private javax.swing.JCheckBox checkActive;
    private javax.swing.JCheckBox checkAllUsers;
    private javax.swing.JCheckBox checkImmediate;
    private pl.mpak.sky.gui.swing.Action cmCancel;
    private pl.mpak.sky.gui.swing.Action cmOk;
    private pl.mpak.usedb.gui.swing.QueryComboBox comboDriverType;
    private pl.mpak.sky.gui.swing.comp.ComboBox comboEditor;
    private pl.mpak.orbada.gui.comps.OrbadaSyntaxTextArea editCode;
    private pl.mpak.sky.gui.swing.comp.TextField editName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private pl.mpak.usedb.core.Query queryDriverTypes;
    // End of variables declaration//GEN-END:variables

}