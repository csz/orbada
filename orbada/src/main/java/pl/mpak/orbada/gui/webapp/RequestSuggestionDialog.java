/*
 * RequestSuggestionDialog.java
 *
 * Created on 11 stycze� 2009, 15:19
 */

package pl.mpak.orbada.gui.webapp;

import javax.swing.JComponent;

import pl.mpak.orbada.Consts;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.plugins.IWebAppAccessibilities;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;
import pl.mpak.util.task.Task;
import pl.mpak.util.task.TaskPool;
import pl.mpak.waitdlg.WaitDialog;

/**
 *
 * @author  akaluza
 */
public class RequestSuggestionDialog extends javax.swing.JDialog {

  private final static StringManager stringManager = StringManagerFactory.getStringManager(Consts.class);

  /** Creates new form RequestSuggestionDialog */
  public RequestSuggestionDialog() {
    super(SwingUtil.getRootFrame(), false);
    initComponents();
    init();
  }
  
  private void init() {
    textContent.setFont(textTitle.getFont());
    textWorkaround.setFont(textTitle.getFont());
    textNecessery.setFont(textTitle.getFont());
    textExpected.setFont(textTitle.getFont());
    textHelpCode.setFont(textHelpCode.getFont().deriveFont((float)getFont().getSize()));
    getRootPane().setDefaultButton(buttonOk);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    SwingUtil.centerWithinScreen(this);
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        textContent.requestFocusInWindow();
      }
    });
  }
  
  public static void showDialog() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        RequestSuggestionDialog dialog = new RequestSuggestionDialog();
        dialog.setVisible(true);
      }
    });
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
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    labelTitle = new javax.swing.JLabel();
    textTitle = new pl.mpak.sky.gui.swing.comp.TextField();
    labelContent = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    textContent = new pl.mpak.sky.gui.swing.comp.TextArea();
    labelSecessery = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    textNecessery = new pl.mpak.sky.gui.swing.comp.TextArea();
    labelContent2 = new javax.swing.JLabel();
    jScrollPane4 = new javax.swing.JScrollPane();
    textExpected = new pl.mpak.sky.gui.swing.comp.TextArea();
    jPanel2 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    textWorkaround = new pl.mpak.sky.gui.swing.comp.TextArea();
    jPanel3 = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jScrollPane5 = new javax.swing.JScrollPane();
    textHelpCode = new pl.mpak.sky.gui.swing.comp.TextArea();

    cmOk.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
    cmOk.setText(stringManager.getString("cmOk-text")); // NOI18N
    cmOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmOkActionPerformed(evt);
      }
    });

    cmCancel.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    cmCancel.setText(stringManager.getString("cmCancel-text")); // NOI18N
    cmCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmCancelActionPerformed(evt);
      }
    });

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(stringManager.getString("RequestSuggestionDialog-title")); // NOI18N

    buttonOk.setAction(cmOk);
    buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonOk.setPreferredSize(new java.awt.Dimension(85, 25));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonCancel.setPreferredSize(new java.awt.Dimension(85, 25));

    jLabel1.setText(stringManager.getString("RequestSuggestionDialog-field-required")); // NOI18N

    labelTitle.setText(stringManager.getString("RequestSuggestionDialog-suggestion-title")); // NOI18N
    labelTitle.setEnabled(false);

    textTitle.setEditable(false);

    labelContent.setText(stringManager.getString("RequestSuggestionDialog-description")); // NOI18N

    textContent.setLineWrap(true);
    textContent.setWrapStyleWord(true);
    jScrollPane1.setViewportView(textContent);

    labelSecessery.setText(stringManager.getString("RequestSuggestionDialog-why")); // NOI18N

    textNecessery.setLineWrap(true);
    textNecessery.setWrapStyleWord(true);
    jScrollPane3.setViewportView(textNecessery);

    labelContent2.setText(stringManager.getString("RequestSuggestionDialog-expected-act")); // NOI18N

    textExpected.setLineWrap(true);
    textExpected.setWrapStyleWord(true);
    jScrollPane4.setViewportView(textExpected);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
          .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelContent, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelSecessery, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelContent2, javax.swing.GroupLayout.Alignment.LEADING))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labelTitle)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelContent)
        .addGap(8, 8, 8)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSecessery)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelContent2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab(stringManager.getString("RequestSuggestionDialog-suggestion"), jPanel1); // NOI18N

    jLabel4.setText(stringManager.getString("RequestSuggestionDialog-how-do-without")); // NOI18N

    textWorkaround.setLineWrap(true);
    textWorkaround.setWrapStyleWord(true);
    jScrollPane2.setViewportView(textWorkaround);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2)
          .addComponent(jLabel4))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab(stringManager.getString("RequestSuggestionDialog-known-solution"), jPanel2); // NOI18N

    jLabel5.setText(stringManager.getString("RequestSuggestionDialog-helpful-code")); // NOI18N

    textHelpCode.setLineWrap(true);
    textHelpCode.setWrapStyleWord(true);
    textHelpCode.setFont(new java.awt.Font("Courier New", 0, 9));
    jScrollPane5.setViewportView(textHelpCode);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel5)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab(stringManager.getString("RequestSuggestionDialog-helpful-code-tab"), jPanel3); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jTabbedPane1)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private void cmOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmOkActionPerformed
  if (StringUtil.isEmpty(textContent.getText())) {
    MessageBox.show(this, stringManager.getString("error"), stringManager.getString("RequestSuggestionDialog-content-required"), ModalResult.OK, MessageBox.ERROR);
    return;
  }
  if (StringUtil.isEmpty(textNecessery.getText())) {
    MessageBox.show(this, stringManager.getString("error"), stringManager.getString("RequestSuggestionDialog-need-exist-required"), ModalResult.OK, MessageBox.ERROR);
    return;
  }
  cmOk.setEnabled(false);
  TaskPool.getTaskPool().addTask(new Task(stringManager.getString("RequestSuggestionDialog-register-request")) {
    String title = textTitle.getText();
    String content = 
      "(DESCRIPTION)\n" +textContent.getText() +"\n" +
      "(NECESSERY)\n" +textNecessery.getText() +"\n" +
      "(EXPECTED)\n" +(StringUtil.isEmpty(textExpected.getText()) ? "N/A" : textExpected.getText()) +"\n" +
      "(HELP CODE)\n" +(StringUtil.isEmpty(textHelpCode.getText()) ? "N/A" : ("<code>" +textHelpCode.getText() +"</code>"));
      @Override
    public void run() {
      WaitDialog.showWaitDialog(stringManager.getString("RequestSuggestionDialog-sending-request"));
      try {
        Application
                .get().getWebAppAccessibilities().requestPost(IWebAppAccessibilities.SUGGESTION, title, null, null, null, null, null, null, content, textWorkaround.getText());
      }
      finally {
        cmOk.setEnabled(true);
        WaitDialog.hideWaitDialog();
      }
      //MessageBox.show(stringManager.getString("RequestSuggestionDialog-request"), stringManager.getString("RequestSuggestionDialog-request-id"));
      RequestSuggestionDialog.this.dispose();
    }
  });
}//GEN-LAST:event_cmOkActionPerformed

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmOk;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JScrollPane jScrollPane5;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JLabel labelContent;
  private javax.swing.JLabel labelContent2;
  private javax.swing.JLabel labelSecessery;
  private javax.swing.JLabel labelTitle;
  private pl.mpak.sky.gui.swing.comp.TextArea textContent;
  private pl.mpak.sky.gui.swing.comp.TextArea textExpected;
  private pl.mpak.sky.gui.swing.comp.TextArea textHelpCode;
  private pl.mpak.sky.gui.swing.comp.TextArea textNecessery;
  private pl.mpak.sky.gui.swing.comp.TextField textTitle;
  private pl.mpak.sky.gui.swing.comp.TextArea textWorkaround;
  // End of variables declaration//GEN-END:variables

}
