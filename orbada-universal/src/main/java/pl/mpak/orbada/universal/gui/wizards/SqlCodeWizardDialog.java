package pl.mpak.orbada.universal.gui.wizards;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.swing.AbstractButton;
import javax.swing.JComponent;

import pl.mpak.orbada.gui.comps.OrbadaSyntaxTextArea;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.universal.OrbadaUniversalPlugin;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.task.Task;
import pl.mpak.util.task.TaskPool;
import pl.mpak.util.variant.Variant;

/**
 *
 * @author  akaluza
 */
public class SqlCodeWizardDialog extends javax.swing.JDialog {

  private final StringManager stringManager = StringManagerFactory.getStringManager(OrbadaUniversalPlugin.class);

  private int modalResult = ModalResult.NONE;
  private SqlCodeWizardPanel wizardPanel;
  private boolean execute;
  private boolean initialized = false;
  private boolean editableSql;
  private WizardResult result;
  private ISettings settings;
  
  public class WizardResult {
    private HashMap<String, String> resultMap = new HashMap<String, String>();
    private String sqlCode;
    
    public Map<String, String> getResultMap() {
      return resultMap;
    }
    
    public String getSqlCode() {
      return sqlCode;
    }
  }
  
  public SqlCodeWizardDialog(SqlCodeWizardPanel wizardPanel, boolean execute, boolean editableSql) {
    super(SwingUtil.getRootFrame());
    this.result = new WizardResult();
    this.wizardPanel = wizardPanel;
    this.execute = execute;
    this.editableSql = editableSql;
    this.wizardPanel.setResultMap(result.resultMap);
    initComponents();
    init();
  }
  
  public static WizardResult show(SqlCodeWizardPanel wizardPanel, boolean execute) {
    return show(wizardPanel, execute, false);
  }
  
  public static WizardResult show(final SqlCodeWizardPanel wizardPanel, final boolean execute, final boolean editableSql) {
    WizardResult result = SwingUtil.invokeAndWait(new Callable<WizardResult>() {
      public WizardResult call() throws Exception {
        SqlCodeWizardDialog dialog = new SqlCodeWizardDialog(wizardPanel, execute, editableSql);
        dialog.setVisible(true);
        if (dialog.modalResult == ModalResult.OK) {
          dialog.result.sqlCode = wizardPanel.getSqlCode();
          return dialog.result;
        }
        return null;
      }
    });
    return result;
  }

  private void init() {
    settings = Application.get().getSettings("wizard-" +wizardPanel.getClass().getCanonicalName());
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    getRootPane().setDefaultButton(buttonOk);
    setTitle(wizardPanel.getDialogTitle());
    tabbedWizard.insertTab(wizardPanel.getTabTitle(), null, wizardPanel, null, 0);
    tabbedWizard.setSelectedIndex(0);
    checkBackground.setVisible(execute);
    textSqlCode.setEditable(editableSql);
    int height = SwingUtil.setButtonSizesTheSame(new AbstractButton[] {buttonOk, buttonCancel});
    buttonScriptInfo.setPreferredSize(new Dimension(height, height));
    pack();
    try {
      setBounds(0, 0, settings.getValue("width", new Variant(getWidth())).getInteger(), settings.getValue("height", new Variant(getHeight())).getInteger());
    } catch (Exception ex) {
    }
    SwingUtil.centerWithinScreen(this);
  }

  @Override
  public void dispose() {
    settings.setValue("width", new Variant(getWidth()));
    settings.setValue("height", new Variant(getHeight()));
    settings.store();
    try {
      wizardPanel.close();
    } catch (IOException ex) {
      ExceptionUtil.processException(ex);
    }
    super.dispose();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmCancel = new pl.mpak.sky.gui.swing.Action();
    cmOk = new pl.mpak.sky.gui.swing.Action();
    cmScriptInfo = new pl.mpak.sky.gui.swing.Action();
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();
    tabbedWizard = new javax.swing.JTabbedPane();
    panelSqlCode = new javax.swing.JPanel();
    textSqlCode = new OrbadaSyntaxTextArea();
    checkClipoard = new javax.swing.JCheckBox();
    checkBackground = new javax.swing.JCheckBox();
    buttonScriptInfo = new pl.mpak.sky.gui.swing.comp.ToolButton();

    cmCancel.setActionCommandKey("cmCancel"); // NOI18N
    cmCancel.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    cmCancel.setText(stringManager.getString("cmCancel-text")); // NOI18N
    cmCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmCancelActionPerformed(evt);
      }
    });

    cmOk.setActionCommandKey("cmOk"); // NOI18N
    cmOk.setText(stringManager.getString("cmOk-text")); // NOI18N
    cmOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmOkActionPerformed(evt);
      }
    });

    cmScriptInfo.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/help.gif")); // NOI18N
    cmScriptInfo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmScriptInfoActionPerformed(evt);
      }
    });

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Title"); // NOI18N
    setModal(true);
    addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentShown(java.awt.event.ComponentEvent evt) {
        formComponentShown(evt);
      }
    });

    buttonOk.setAction(cmOk);
    buttonOk.setPreferredSize(new java.awt.Dimension(75, 23));

    buttonCancel.setAction(cmCancel);
    buttonCancel.setPreferredSize(new java.awt.Dimension(75, 23));

    tabbedWizard.setFocusable(false);

    panelSqlCode.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentShown(java.awt.event.ComponentEvent evt) {
        panelSqlCodeComponentShown(evt);
      }
    });

    textSqlCode.setEditable(false);

    javax.swing.GroupLayout panelSqlCodeLayout = new javax.swing.GroupLayout(panelSqlCode);
    panelSqlCode.setLayout(panelSqlCodeLayout);
    panelSqlCodeLayout.setHorizontalGroup(
      panelSqlCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelSqlCodeLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(textSqlCode, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        .addContainerGap())
    );
    panelSqlCodeLayout.setVerticalGroup(
      panelSqlCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelSqlCodeLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(textSqlCode, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        .addContainerGap())
    );

    tabbedWizard.addTab(stringManager.getString("sql"), panelSqlCode); // NOI18N

    checkClipoard.setText(stringManager.getString("copy-toclipboard")); // NOI18N

    checkBackground.setText(stringManager.getString("background-execute")); // NOI18N

    buttonScriptInfo.setAction(cmScriptInfo);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(checkClipoard)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(checkBackground)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
            .addComponent(buttonScriptInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(tabbedWizard, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(tabbedWizard, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(checkClipoard)
          .addComponent(checkBackground)
          .addComponent(buttonScriptInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private void panelSqlCodeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSqlCodeComponentShown
  try {
    if (!initialized) {
      wizardPanel.wizardShow();
      initialized = true;
    }
    textSqlCode.setText(wizardPanel.getSqlCode());
    textSqlCode.setChanged(false);
    textSqlCode.getEditorArea().setCaretPosition(0);
  }
  catch (Throwable ex) {
    ExceptionUtil.processException(ex);
  }
}//GEN-LAST:event_panelSqlCodeComponentShown

private void cmOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmOkActionPerformed
  String sqlCode = wizardPanel.getSqlCode();
  result.getResultMap().put("sql-code", sqlCode);
  if (checkClipoard.isSelected()) {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection data = new StringSelection(sqlCode);
    clipboard.setContents(data, data);
    result.getResultMap().put("object-to-clipboard", "true");
  }
  else if (execute) {
    if (checkBackground.isSelected()) {
      TaskPool.getTaskPool("sql-code-wizard-execute").addTask(new Task(stringManager.getString("executing-3d") +" " +sqlCode.substring(0, Math.min(50, sqlCode.length()))) {
        public void run() {
          wizardPanel.execute();
        }
      });
    }
    else {
      if (!wizardPanel.execute()) {
        return;
      }
    }
    result.getResultMap().put("object-to-clipboard", "false");
  }
  modalResult = ModalResult.OK;
  dispose();
}//GEN-LAST:event_cmOkActionPerformed

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  modalResult = ModalResult.CANCEL;
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
  if (!initialized) {
    wizardPanel.wizardShow();
    initialized = true;
  }
}//GEN-LAST:event_formComponentShown

private void cmScriptInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmScriptInfoActionPerformed
  MessageBox.show(stringManager.getString("wizard-script-info"));
}//GEN-LAST:event_cmScriptInfoActionPerformed
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private pl.mpak.sky.gui.swing.comp.ToolButton buttonScriptInfo;
  private javax.swing.JCheckBox checkBackground;
  private javax.swing.JCheckBox checkClipoard;
  private pl.mpak.sky.gui.swing.Action cmCancel;
  private pl.mpak.sky.gui.swing.Action cmOk;
  private pl.mpak.sky.gui.swing.Action cmScriptInfo;
  private javax.swing.JPanel panelSqlCode;
  private javax.swing.JTabbedPane tabbedWizard;
  private OrbadaSyntaxTextArea textSqlCode;
  // End of variables declaration//GEN-END:variables
  
}
