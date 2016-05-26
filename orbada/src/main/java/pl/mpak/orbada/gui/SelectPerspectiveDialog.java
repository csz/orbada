package pl.mpak.orbada.gui;

import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;

import pl.mpak.orbada.Consts;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.SwingUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class SelectPerspectiveDialog extends javax.swing.JDialog {
  
  private final static StringManager stringManager = StringManagerFactory.getStringManager(Consts.class);

  private int modalResult = ModalResult.NONE;
  private PerspectivePanel perspective;
  private JTabbedPane tabPerspectives;
  private boolean sameDriverType;

  public SelectPerspectiveDialog(JTabbedPane tabPerspectives, PerspectivePanel perspective, boolean sameDriverType) {
    super(SwingUtil.getRootFrame());
    this.tabPerspectives = tabPerspectives;
    this.perspective = perspective;
    this.sameDriverType = sameDriverType;
    initComponents();
    init();
  }
  
  public static PerspectivePanel show(JTabbedPane tabPerspectives, PerspectivePanel perspective) {
    return show(tabPerspectives, perspective, false);
  }
  
  public static PerspectivePanel show(JTabbedPane tabPerspectives, PerspectivePanel perspective, boolean sameDriverType) {
    SelectPerspectiveDialog dialog = new SelectPerspectiveDialog(tabPerspectives, perspective, sameDriverType);
    dialog.setVisible(true);
    if (dialog.modalResult == ModalResult.OK) {
      return (PerspectivePanel)dialog.listPerspectives.getSelectedValue();
    }
    return null;
  }
  
  private void init() {
    listPerspectives.setCellRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof PerspectivePanel) {
          PerspectivePanel panel = (PerspectivePanel)value;
          String text = "<html>";
          text = text +"<b>" +panel.getPerspective().getDisplayName(panel.getDatabase()) +"</b>";
          if (panel.getDatabase() != null) {
            text = text +" - " +panel.getDatabase().getUrl() +" as " +panel.getDatabase().getUserName();
          }
          if (panel.getSchema() != null) {
            label.setIcon(panel.getSchema().getIcon());
          }
          label.setText(text);
          label.setVerticalAlignment(JLabel.TOP);
        }
        return label;
      }
    });
    
    listPerspectives.removeAll();
    listPerspectives.setModel(new DefaultListModel());
    for (int i=0; i<tabPerspectives.getTabCount(); i++) {
      Component c = tabPerspectives.getComponentAt(i);
      if (c instanceof PerspectivePanel) {
        PerspectivePanel panel = (PerspectivePanel)c;
        if (sameDriverType) {
          if (perspective == panel) {
            continue;
          }
          if (panel.getDatabase() != null && perspective.getDatabase() == null) {
            continue;
          }
          if (panel.getDatabase() != null && perspective.getDatabase() != null &&
              !panel.getDatabase().getDriverType().equals(perspective.getDatabase().getDriverType())) {
            continue;
          }
        }
        ((DefaultListModel)listPerspectives.getModel()).addElement(panel);
      }
    }
    cmSelect.setEnabled(false);
    listPerspectives.setSelectedValue(perspective, true);
    
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        listPerspectives.requestFocus();
      }
    });

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cmCancel.getShortCut(), "cmCancel");
    getRootPane().getActionMap().put("cmCancel", cmCancel);
    getRootPane().setDefaultButton(buttonOk);
    SwingUtil.setButtonSizesTheSame(new AbstractButton[] {buttonOk, buttonCancel});
    pack();
    SwingUtil.centerWithinScreen(this);
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmCancel = new pl.mpak.sky.gui.swing.Action();
        cmSelect = new pl.mpak.sky.gui.swing.Action();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPerspectives = new javax.swing.JList();
        buttonOk = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        cmCancel.setActionCommandKey("cmCancel");
        cmCancel.setShortCut(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        cmCancel.setText(stringManager.getString("cmCancel-text")); // NOI18N
        cmCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmCancelActionPerformed(evt);
            }
        });

        cmSelect.setActionCommandKey("cmSelect");
        cmSelect.setText(stringManager.getString("cmSelect-text")); // NOI18N
        cmSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmSelectActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(stringManager.getString("SelectPerspectiveDialog-title")); // NOI18N
        setModal(true);

        listPerspectives.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listPerspectivesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listPerspectives);

        buttonOk.setAction(cmSelect);
        buttonOk.setMargin(new java.awt.Insets(2, 2, 2, 2));
        buttonOk.setPreferredSize(new java.awt.Dimension(85, 25));

        buttonCancel.setAction(cmCancel);
        buttonCancel.setMargin(new java.awt.Insets(2, 2, 2, 2));
        buttonCancel.setPreferredSize(new java.awt.Dimension(85, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void listPerspectivesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listPerspectivesValueChanged
  cmSelect.setEnabled(listPerspectives.getSelectedIndex() != -1);
}//GEN-LAST:event_listPerspectivesValueChanged

private void cmSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSelectActionPerformed
  modalResult = ModalResult.OK;
  dispose();
}//GEN-LAST:event_cmSelectActionPerformed

private void cmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmCancelActionPerformed
  modalResult = ModalResult.CANCEL;
  dispose();
}//GEN-LAST:event_cmCancelActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOk;
    private pl.mpak.sky.gui.swing.Action cmCancel;
    private pl.mpak.sky.gui.swing.Action cmSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listPerspectives;
    // End of variables declaration//GEN-END:variables
  
}
