/*
 * ChangeUserPasswordDialog.java
 *
 * Created on 9 stycze� 2008, 17:24
 */

package pl.mpak.orbada.gui;

import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import pl.mpak.orbada.Consts;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.core.Application;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.StringUtil;

/**
 *
 * @author  akaluza
 */
public class ChangeUserPasswordDialog extends javax.swing.JDialog {
  
  private final static StringManager stringManager = StringManagerFactory.getStringManager(Consts.class);

  private int modalResult = ModalResult.NONE;

  /** Creates new form ChangeUserPasswordDialog */
  public ChangeUserPasswordDialog() {
    super(SwingUtil.getRootFrame());
    initComponents();
    init();
  }
  
  public static void showDialog() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        ChangeUserPasswordDialog dialog = new ChangeUserPasswordDialog();
        dialog.setVisible(true);
      }
    });
  }

  private void init() {
    if (StringUtil.isEmpty(Application.get().getUser().getPassword())) {
      textPassword.setEnabled(false);
    }
    DocumentListener docListener = new DocumentListener() {
      public void insertUpdate(DocumentEvent e) {
        compareNewPassword();
      }
      public void removeUpdate(DocumentEvent e) {
        compareNewPassword();
      }
      public void changedUpdate(DocumentEvent e) {
      }
    };
    textNewPassword.getDocument().addDocumentListener(docListener);
    textReplayPassword.getDocument().addDocumentListener(docListener);
    compareNewPassword();
    getRootPane().setDefaultButton(buttonOk);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    SwingUtil.centerWithinScreen(this);
  }
  
  private void compareNewPassword() {
    String newPass = new String(textNewPassword.getPassword());
    String repPass = new String(textReplayPassword.getPassword());
    cmOk.setEnabled(newPass.equals(repPass));
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
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();
    iconPanel1 = new pl.mpak.sky.gui.swing.comp.IconPanel();
    labelPassword = new javax.swing.JLabel();
    textPassword = new javax.swing.JPasswordField();
    jLabel3 = new javax.swing.JLabel();
    textNewPassword = new javax.swing.JPasswordField();
    jLabel4 = new javax.swing.JLabel();
    textReplayPassword = new javax.swing.JPasswordField();

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
    setTitle(stringManager.getString("ChangeUserPasswordDialog-title")); // NOI18N
    setModal(true);

    buttonOk.setAction(cmOk);
    buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonOk.setPreferredSize(new java.awt.Dimension(85, 25));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
    buttonCancel.setPreferredSize(new java.awt.Dimension(85, 25));

    iconPanel1.setIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/login.gif")); // NOI18N

    javax.swing.GroupLayout iconPanel1Layout = new javax.swing.GroupLayout(iconPanel1);
    iconPanel1.setLayout(iconPanel1Layout);
    iconPanel1Layout.setHorizontalGroup(
      iconPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 48, Short.MAX_VALUE)
    );
    iconPanel1Layout.setVerticalGroup(
      iconPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 48, Short.MAX_VALUE)
    );

    labelPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelPassword.setText(stringManager.getString("ChangeUserPasswordDialog-old-password-dd")); // NOI18N

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("ChangeUserPasswordDialog-new-password-dd")); // NOI18N

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel4.setText(stringManager.getString("ChangeUserPasswordDialog-rewrite-password-dd")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textReplayPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(iconPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelPassword)
              .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel3)
              .addComponent(textNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel4)
              .addComponent(textReplayPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addComponent(iconPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cmOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmOkActionPerformed
    String passwd = new String(textPassword.getPassword());
    if (passwd.equals(Application.get().getUser().getPassword())) {
      Application.get().getUser().setPassword(new String(textNewPassword.getPassword()));
      try {
        Application.get().getUser().applyUpdate();
        modalResult = ModalResult.OK;
        dispose();
      } catch (Exception ex) {
        MessageBox.show(this, stringManager.getString("error"), ex.getMessage(), ModalResult.OK, MessageBox.ERROR);
      }
    }
    else {
      MessageBox.show(this, stringManager.getString("error"), stringManager.getString("ChangeUserPasswordDialog-old-password-expected"), ModalResult.OK, MessageBox.ERROR);
    }
  }//GEN-LAST:event_cmOkActionPerformed

  private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
    modalResult = ModalResult.CANCEL;
    dispose();
  }//GEN-LAST:event_cmCancelActionPerformed
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmOk;
  private pl.mpak.sky.gui.swing.comp.IconPanel iconPanel1;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel labelPassword;
  private javax.swing.JPasswordField textNewPassword;
  private javax.swing.JPasswordField textPassword;
  private javax.swing.JPasswordField textReplayPassword;
  // End of variables declaration//GEN-END:variables
  
}
