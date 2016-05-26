package pl.mpak.orbada.oracle.dba.gui.wizards;

import java.util.HashMap;
import javax.swing.DefaultListModel;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.db.Template;
import pl.mpak.orbada.oracle.dba.OrbadaOracleDbaPlugin;
import pl.mpak.orbada.oracle.services.OracleTemplatesSettingsProvider;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.universal.gui.wizards.SqlCodeWizardPanel;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.usedb.core.Database;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class CreateDatabaseTriggerWizard extends SqlCodeWizardPanel {

  private final StringManager stringManager = StringManagerFactory.getStringManager("oracle-dba");

  private Database database;
  private String schemaName;
  private Template template;
  
  private static String[] beforeEvents = {
    "ALTER", "ANALYZE", "ASSOCIATE STATISTICS", "AUDIT",
    "COMMENT", "CREATE", "DDL", "DISASSOCIATE STATISTICS",
    "DROP", "GRANT", "LOGOFF", "NOAUDIT", "RENAME",
    "REVOKE", "SHUTDOWN", "TRUNCATE"
  };

  private static String[] afterEvents = {
    "ALTER", "ANALYZE", "ASSOCIATE STATISTICS", "AUDIT",
    "COMMENT", "CREATE", "DB_ROLE_CHANGE", "DDL", "DISASSOCIATE STATISTICS",
    "DROP", "GRANT", "LOGON", "NOAUDIT", "RENAME",
    "REVOKE", "SERVERERROR", "STARTUP", "SUSPEND", "TRUNCATE"
  };

  /** 
   * @param database
   * @param schemaName
   */
  public CreateDatabaseTriggerWizard(Database database, String schemaName) {
    this.database = database;
    this.schemaName = schemaName;
    initComponents();
    init();
  }

  private void init() {
    comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
      stringManager.getString("trigger-before-event"),
      stringManager.getString("trigger-after-event") }));
    listAvailEvents.setModel(new DefaultListModel());
    listSelectedEvents.setModel(new DefaultListModel());
  }

  public void wizardShow() {
    ISettings oracle = Application.get().getSettings(OracleTemplatesSettingsProvider.settingsName);
    template = new Template(Application.get().getOrbadaDatabase()).loadByName(oracle.getValue(OracleTemplatesSettingsProvider.setTrigger, "oracle-trigger"));

    updateEventList();
  }
  
  private void updateMoveActions() {
    cmMoveRight.setEnabled(listAvailEvents.getSelectedValue() != null);
    cmMoveAllRight.setEnabled(listAvailEvents.getModel().getSize() > 0);
    cmMoveLeft.setEnabled(listSelectedEvents.getSelectedValue() != null);
    cmMoveAllLeft.setEnabled(listSelectedEvents.getModel().getSize() > 0);
  }

  private void updateEventList() {
    DefaultListModel model = (DefaultListModel)listSelectedEvents.getModel();
    model.removeAllElements();
    model = (DefaultListModel)listAvailEvents.getModel();
    model.removeAllElements();
    for (String s : (comboType.getSelectedIndex() == 0 ? beforeEvents : afterEvents)) {
      model.addElement(s);
    }
    updateMoveActions();
  }
      
  public String getDialogTitle() {
    return stringManager.getString("CreateDatabaseTriggerWizard-dialog-title");
  }

  public String getTabTitle() {
    return stringManager.getString("CreateDatabaseTriggerWizard-tab-title");
  }

  public String getSqlCode() {
    getResultMap().put("object_name", textName.getText());
    String type = (comboType.getSelectedIndex() == 0 ? "BEFORE" : "AFTER");
    DefaultListModel sel = (DefaultListModel)listSelectedEvents.getModel();
    if (sel.getSize() > 0) {
      for (int i=0; i<sel.getSize(); i++) {
        if (i > 0) {
          type = type +" OR";
        }
        type = type +" " +sel.getElementAt(i);
      }
    }
    if (template == null) {
      return
        "CREATE TRIGGER " +textName.getText() +"\n" +
        "  " +type +" ON DATABASE\n" +
        "BEGIN\n" +
        "  NULL;\n" +
        "END;";
    }
    else {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("&name", textName.getText());
      map.put("&type", "  " +type +" ON DATABASE");
      map.put("&body", "  NULL;");
      map.put("&description", "");
      return template.expand(map);
    }
  }

  public boolean execute() {
    try {
      database.executeCommand(getSqlCode());
      return true;
    } catch (Exception ex) {
      MessageBox.show(this, stringManager.getString("error"), ex.getMessage(), ModalResult.OK, MessageBox.ERROR);
      return false;
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmMoveRight = new pl.mpak.sky.gui.swing.Action();
    cmMoveAllRight = new pl.mpak.sky.gui.swing.Action();
    cmMoveLeft = new pl.mpak.sky.gui.swing.Action();
    cmMoveAllLeft = new pl.mpak.sky.gui.swing.Action();
    jLabel3 = new javax.swing.JLabel();
    textName = new pl.mpak.sky.gui.swing.comp.TextField();
    jLabel5 = new javax.swing.JLabel();
    comboType = new javax.swing.JComboBox();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    listAvailEvents = new javax.swing.JList();
    buttonMoveRight = new javax.swing.JButton();
    buttonMoveAllRight = new javax.swing.JButton();
    buttonMoveRight1 = new javax.swing.JButton();
    buttonMoveAllRight1 = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    listSelectedEvents = new javax.swing.JList();
    labelSelectedColumns = new javax.swing.JLabel();

    cmMoveRight.setActionCommandKey("cmMoveRight");
    cmMoveRight.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/move_right.gif")); // NOI18N
    cmMoveRight.setText(stringManager.getString("cmMoveRight-text")); // NOI18N
    cmMoveRight.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveRightActionPerformed(evt);
      }
    });

    cmMoveAllRight.setActionCommandKey("cmMoveAllRight");
    cmMoveAllRight.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/move_all_right.gif")); // NOI18N
    cmMoveAllRight.setText(stringManager.getString("cmMoveAllRight-text")); // NOI18N
    cmMoveAllRight.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveAllRightActionPerformed(evt);
      }
    });

    cmMoveLeft.setActionCommandKey("cmMoveLeft");
    cmMoveLeft.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/move_left.gif")); // NOI18N
    cmMoveLeft.setText(stringManager.getString("cmMoveLeft-text")); // NOI18N
    cmMoveLeft.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveLeftActionPerformed(evt);
      }
    });

    cmMoveAllLeft.setActionCommandKey("cmMoveAllLeft");
    cmMoveAllLeft.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/move_all_left.gif")); // NOI18N
    cmMoveAllLeft.setText(stringManager.getString("cmMoveAllLeft-text")); // NOI18N
    cmMoveAllLeft.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmMoveAllLeftActionPerformed(evt);
      }
    });

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText(stringManager.getString("trigger-name-dd")); // NOI18N

    textName.setText("DATABASE_TRG");

    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel5.setText(stringManager.getString("trigger-type-dd")); // NOI18N

    comboType.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        comboTypeItemStateChanged(evt);
      }
    });

    jLabel1.setText(stringManager.getString("available-events-dd")); // NOI18N

    listAvailEvents.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        listAvailEventsValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(listAvailEvents);

    buttonMoveRight.setAction(cmMoveRight);
    buttonMoveRight.setHideActionText(true);
    buttonMoveRight.setMargin(new java.awt.Insets(1, 1, 1, 1));
    buttonMoveRight.setPreferredSize(new java.awt.Dimension(50, 23));

    buttonMoveAllRight.setAction(cmMoveAllRight);
    buttonMoveAllRight.setHideActionText(true);
    buttonMoveAllRight.setMargin(new java.awt.Insets(1, 1, 1, 1));
    buttonMoveAllRight.setPreferredSize(new java.awt.Dimension(50, 23));

    buttonMoveRight1.setAction(cmMoveLeft);
    buttonMoveRight1.setHideActionText(true);
    buttonMoveRight1.setMargin(new java.awt.Insets(1, 1, 1, 1));
    buttonMoveRight1.setPreferredSize(new java.awt.Dimension(50, 23));

    buttonMoveAllRight1.setAction(cmMoveAllLeft);
    buttonMoveAllRight1.setHideActionText(true);
    buttonMoveAllRight1.setMargin(new java.awt.Insets(1, 1, 1, 1));
    buttonMoveAllRight1.setPreferredSize(new java.awt.Dimension(50, 23));

    listSelectedEvents.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        listSelectedEventsValueChanged(evt);
      }
    });
    jScrollPane2.setViewportView(listSelectedEvents);

    labelSelectedColumns.setText(stringManager.getString("selected-events-dd")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboType, 0, 225, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(buttonMoveAllRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(buttonMoveRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(buttonMoveAllRight, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(buttonMoveRight, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
              .addComponent(labelSelectedColumns))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel3)
              .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel5)
              .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(labelSelectedColumns))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addGap(85, 85, 85)
            .addComponent(buttonMoveRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveAllRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveRight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveAllRight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void comboTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTypeItemStateChanged
    updateEventList();
}//GEN-LAST:event_comboTypeItemStateChanged

  private void listAvailEventsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAvailEventsValueChanged
    updateMoveActions();
}//GEN-LAST:event_listAvailEventsValueChanged

  private void listSelectedEventsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSelectedEventsValueChanged
    updateMoveActions();
}//GEN-LAST:event_listSelectedEventsValueChanged

  private void cmMoveRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveRightActionPerformed
    DefaultListModel sel = (DefaultListModel)listSelectedEvents.getModel();
    DefaultListModel tab = (DefaultListModel)listAvailEvents.getModel();
    sel.addElement(listAvailEvents.getSelectedValue());
    tab.removeElement(listAvailEvents.getSelectedValue());
    updateMoveActions();
  }//GEN-LAST:event_cmMoveRightActionPerformed

  private void cmMoveLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveLeftActionPerformed
    DefaultListModel sel = (DefaultListModel)listSelectedEvents.getModel();
    DefaultListModel tab = (DefaultListModel)listAvailEvents.getModel();
    tab.addElement(listSelectedEvents.getSelectedValue());
    sel.removeElement(listSelectedEvents.getSelectedValue());
    updateMoveActions();
  }//GEN-LAST:event_cmMoveLeftActionPerformed

  private void cmMoveAllRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveAllRightActionPerformed
    DefaultListModel sel = (DefaultListModel)listSelectedEvents.getModel();
    DefaultListModel tab = (DefaultListModel)listAvailEvents.getModel();
    for (int i=0; i<tab.getSize(); i++) {
      sel.addElement(tab.get(i));
    }
    tab.removeAllElements();
    updateMoveActions();
  }//GEN-LAST:event_cmMoveAllRightActionPerformed

  private void cmMoveAllLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmMoveAllLeftActionPerformed
    DefaultListModel sel = (DefaultListModel)listSelectedEvents.getModel();
    DefaultListModel tab = (DefaultListModel)listAvailEvents.getModel();
    for (int i=0; i<sel.getSize(); i++) {
      tab.addElement(sel.get(i));
    }
    sel.removeAllElements();
    updateMoveActions();
  }//GEN-LAST:event_cmMoveAllLeftActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonMoveAllRight;
  private javax.swing.JButton buttonMoveAllRight1;
  private javax.swing.JButton buttonMoveRight;
  private javax.swing.JButton buttonMoveRight1;
  private pl.mpak.sky.gui.swing.Action cmMoveAllLeft;
  private pl.mpak.sky.gui.swing.Action cmMoveAllRight;
  private pl.mpak.sky.gui.swing.Action cmMoveLeft;
  private pl.mpak.sky.gui.swing.Action cmMoveRight;
  private javax.swing.JComboBox comboType;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel labelSelectedColumns;
  private javax.swing.JList listAvailEvents;
  private javax.swing.JList listSelectedEvents;
  private pl.mpak.sky.gui.swing.comp.TextField textName;
  // End of variables declaration//GEN-END:variables
}
