/*
 * WelcomeViewPanel.java
 *
 * Created on 22 grudzie� 2007, 22:06
 */
package pl.mpak.orbada.welcome.gui;

import java.io.IOException;
import java.io.InputStream;
import javax.swing.text.html.StyleSheet;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.gui.OrbadaSettingsDialog;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.plugins.IViewAccesibilities;
import pl.mpak.orbada.welcome.OrbadaWelcomePlugin;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class WelcomeViewPanel extends javax.swing.JPanel {

  private final static StringManager stringManager = StringManagerFactory.getStringManager(OrbadaWelcomePlugin.class);

  private IViewAccesibilities accesibilities;
  private ISettings settings;
  
  /** Creates new form WelcomeViewPanel */
  public WelcomeViewPanel(IViewAccesibilities accesibilities) {
    this.accesibilities = accesibilities;
    settings = accesibilities.getApplication().getSettings("orbada-welcome");
    initComponents();
    init();
  }

  private void init() {
    checkDisableView.setSelected(!settings.getValue("show", true));
//    InputStream is = getClass().getResourceAsStream("/pl/mpak/orbada/welcome/res/html/style.css");
//    if (is != null) {
//      try {
//        StyleSheet ss = loadStyleSheet(is);
//        ((HTMLEditorKit)html.getEditorKit()).setStyleSheet(ss);
//      } catch (IOException ex) {
//        ExceptionUtil.processException(ex);
//      }
//    }
//    try {
//      html.setPage(getClass().getResource("/pl/mpak/orbada/welcome/res/html/index.html"));
//    } catch (IOException ex) {
//      ExceptionUtil.processException(ex);
//    }
  }

  public StyleSheet loadStyleSheet(InputStream is) throws IOException {
    StyleSheet s = new StyleSheet();
//    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//    s.loadRules(reader, null);
//    reader.close();
//
    return s;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cmSettingsApperance = new pl.mpak.sky.gui.swing.Action();
    cmSettingsDataSettings = new pl.mpak.sky.gui.swing.Action();
    cmDrivers = new pl.mpak.sky.gui.swing.Action();
    cmNewConnection = new pl.mpak.sky.gui.swing.Action();
    cmSwitchShow = new pl.mpak.sky.gui.swing.Action();
    cmSettingsProxy = new pl.mpak.sky.gui.swing.Action();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    panelWelcome = new javax.swing.JPanel();
    checkDisableView = new javax.swing.JCheckBox();
    jLabel1 = new javax.swing.JLabel();
    iconPanel1 = new pl.mpak.sky.gui.swing.comp.IconPanel();
    scrollAdvices = new javax.swing.JScrollPane();
    panelAdvices = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    buttonSettingsApperance = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    buttonSettingsDataFormat = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();
    jPanel8 = new javax.swing.JPanel();
    buttonSettingsDataFormat2 = new javax.swing.JButton();
    jLabel8 = new javax.swing.JLabel();
    jPanel4 = new javax.swing.JPanel();
    butttonDrivers = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    buttonSettingsDataFormat1 = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    jPanel7 = new javax.swing.JPanel();
    jLabel7 = new javax.swing.JLabel();

    cmSettingsApperance.setActionCommandKey("cmSettingsApperance");
    cmSettingsApperance.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    cmSettingsApperance.setText(stringManager.getString("settings-text")); // NOI18N
    cmSettingsApperance.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmSettingsApperanceActionPerformed(evt);
      }
    });

    cmSettingsDataSettings.setActionCommandKey("cmSettingsDataSettings");
    cmSettingsDataSettings.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    cmSettingsDataSettings.setText(stringManager.getString("settings-text")); // NOI18N
    cmSettingsDataSettings.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmSettingsDataSettingsActionPerformed(evt);
      }
    });

    cmDrivers.setActionCommandKey("cmDrivers");
    cmDrivers.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/driverproperties.gif")); // NOI18N
    cmDrivers.setText(stringManager.getString("cmDrivers-text")); // NOI18N
    cmDrivers.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmDriversActionPerformed(evt);
      }
    });

    cmNewConnection.setActionCommandKey("cmNewConnection");
    cmNewConnection.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/connection.gif")); // NOI18N
    cmNewConnection.setText(stringManager.getString("cmNewConnection-text")); // NOI18N
    cmNewConnection.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmNewConnectionActionPerformed(evt);
      }
    });

    cmSwitchShow.setActionCommandKey("cmSwitchShow");
    cmSwitchShow.setText(stringManager.getString("cmSwitchShow-text")); // NOI18N
    cmSwitchShow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmSwitchShowActionPerformed(evt);
      }
    });

    cmSettingsProxy.setActionCommandKey("cmSettingsProxy");
    cmSettingsProxy.setSmallIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    cmSettingsProxy.setText(stringManager.getString("settings-text")); // NOI18N
    cmSettingsProxy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmSettingsProxyActionPerformed(evt);
      }
    });

    setPreferredSize(new java.awt.Dimension(747, 1024));
    setLayout(new java.awt.BorderLayout());

    jTabbedPane1.setFocusable(false);

    panelWelcome.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));

    checkDisableView.setAction(cmSwitchShow);
    checkDisableView.setOpaque(false);

    jLabel1.setText(stringManager.getString("WelcomeViewPanel-welcome-label")); // NOI18N

    iconPanel1.setIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/orbada64.png")); // NOI18N
    iconPanel1.setOpaque(false);

    javax.swing.GroupLayout iconPanel1Layout = new javax.swing.GroupLayout(iconPanel1);
    iconPanel1.setLayout(iconPanel1Layout);
    iconPanel1Layout.setHorizontalGroup(
      iconPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 64, Short.MAX_VALUE)
    );
    iconPanel1Layout.setVerticalGroup(
      iconPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 64, Short.MAX_VALUE)
    );

    scrollAdvices.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, javax.swing.UIManager.getDefaults().getColor("controlShadow")));

    panelAdvices.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
    panelAdvices.setPreferredSize(new java.awt.Dimension(600, 800));
    panelAdvices.setLayout(new pl.mpak.sky.gui.swing.VerticalFlowLayout());

    jPanel2.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel2.setOpaque(false);

    buttonSettingsApperance.setAction(cmSettingsApperance);
    buttonSettingsApperance.setPreferredSize(new java.awt.Dimension(110, 34));

    jLabel2.setText(stringManager.getString("WelcomeViewPanel-settings-apperance-label")); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(buttonSettingsApperance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(buttonSettingsApperance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelAdvices.add(jPanel2);

    jPanel3.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel3.setOpaque(false);

    buttonSettingsDataFormat.setAction(cmSettingsDataSettings);
    buttonSettingsDataFormat.setIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    buttonSettingsDataFormat.setPreferredSize(new java.awt.Dimension(110, 34));

    jLabel3.setText(stringManager.getString("WelcomeViewPanel-settings-datatype-label")); // NOI18N

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(buttonSettingsDataFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(buttonSettingsDataFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelAdvices.add(jPanel3);

    jPanel8.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel8.setOpaque(false);

    buttonSettingsDataFormat2.setAction(cmSettingsProxy);
    buttonSettingsDataFormat2.setIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    buttonSettingsDataFormat2.setPreferredSize(new java.awt.Dimension(110, 34));

    jLabel8.setText(stringManager.getString("WelcomeViewPanel-settings-proxy-label")); // NOI18N

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
      jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel8Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(buttonSettingsDataFormat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel8Layout.setVerticalGroup(
      jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel8Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
          .addComponent(buttonSettingsDataFormat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelAdvices.add(jPanel8);

    jPanel4.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel4.setOpaque(false);

    butttonDrivers.setAction(cmDrivers);
    butttonDrivers.setPreferredSize(new java.awt.Dimension(110, 34));

    jLabel4.setText(stringManager.getString("WelcomeViewPanel-drivers-label")); // NOI18N

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(butttonDrivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(butttonDrivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelAdvices.add(jPanel4);

    jPanel5.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel5.setOpaque(false);

    buttonSettingsDataFormat1.setAction(cmNewConnection);
    buttonSettingsDataFormat1.setIcon(pl.mpak.sky.gui.swing.ImageManager.getImage("/pl/mpak/res/icons/visual_props.gif")); // NOI18N
    buttonSettingsDataFormat1.setPreferredSize(new java.awt.Dimension(110, 34));

    jLabel5.setText(stringManager.getString("WelcomeViewPanel-connection-schema-label")); // NOI18N

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(buttonSettingsDataFormat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(buttonSettingsDataFormat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelAdvices.add(jPanel5);

    jPanel6.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel6.setOpaque(false);

    jLabel6.setText(stringManager.getString("WelcomeViewPanel-after-connection-label")); // NOI18N

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel6Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        .addContainerGap())
    );

    panelAdvices.add(jPanel6);

    jPanel7.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("controlShadow"), 1, true));
    jPanel7.setOpaque(false);

    jLabel7.setText(stringManager.getString("WelcomeViewPanel-more-views-label")); // NOI18N

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
      jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel7Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel7Layout.setVerticalGroup(
      jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel7Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        .addContainerGap())
    );

    panelAdvices.add(jPanel7);

    scrollAdvices.setViewportView(panelAdvices);

    javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
    panelWelcome.setLayout(panelWelcomeLayout);
    panelWelcomeLayout.setHorizontalGroup(
      panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWelcomeLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(scrollAdvices, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelWelcomeLayout.createSequentialGroup()
            .addComponent(iconPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
          .addComponent(checkDisableView, javax.swing.GroupLayout.Alignment.LEADING))
        .addContainerGap())
    );
    panelWelcomeLayout.setVerticalGroup(
      panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelWelcomeLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(iconPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(scrollAdvices, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(checkDisableView)
        .addContainerGap())
    );

    jTabbedPane1.addTab(stringManager.getString("WelcomeViewPanel-tab-welcome-text"), panelWelcome); // NOI18N

    add(jTabbedPane1, java.awt.BorderLayout.CENTER);
  }// </editor-fold>//GEN-END:initComponents

  private void cmSettingsApperanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSettingsApperanceActionPerformed
    OrbadaSettingsDialog.showDialog(OrbadaSettingsDialog.Tab.tabApperance);
  }//GEN-LAST:event_cmSettingsApperanceActionPerformed

  private void cmSettingsDataSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSettingsDataSettingsActionPerformed
    OrbadaSettingsDialog.showDialog(OrbadaSettingsDialog.Tab.tabDataFormat);
}//GEN-LAST:event_cmSettingsDataSettingsActionPerformed

  private void cmDriversActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmDriversActionPerformed
    Application.get().getMainFrame().cmDrivers.performe();
  }//GEN-LAST:event_cmDriversActionPerformed

  private void cmNewConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmNewConnectionActionPerformed
    Application.get().getMainFrame().cmNewConnection.performe();
  }//GEN-LAST:event_cmNewConnectionActionPerformed

  private void cmSwitchShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSwitchShowActionPerformed
    settings.setValue("show", !checkDisableView.isSelected());
    settings.store();
  }//GEN-LAST:event_cmSwitchShowActionPerformed

  private void cmSettingsProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSettingsProxyActionPerformed
    OrbadaSettingsDialog.showDialog(OrbadaSettingsDialog.Tab.tabProxy);
}//GEN-LAST:event_cmSettingsProxyActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonSettingsApperance;
  private javax.swing.JButton buttonSettingsDataFormat;
  private javax.swing.JButton buttonSettingsDataFormat1;
  private javax.swing.JButton buttonSettingsDataFormat2;
  private javax.swing.JButton butttonDrivers;
  private javax.swing.JCheckBox checkDisableView;
  private pl.mpak.sky.gui.swing.Action cmDrivers;
  private pl.mpak.sky.gui.swing.Action cmNewConnection;
  private pl.mpak.sky.gui.swing.Action cmSettingsApperance;
  private pl.mpak.sky.gui.swing.Action cmSettingsDataSettings;
  private pl.mpak.sky.gui.swing.Action cmSettingsProxy;
  private pl.mpak.sky.gui.swing.Action cmSwitchShow;
  private pl.mpak.sky.gui.swing.comp.IconPanel iconPanel1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JPanel jPanel8;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JPanel panelAdvices;
  private javax.swing.JPanel panelWelcome;
  private javax.swing.JScrollPane scrollAdvices;
  // End of variables declaration//GEN-END:variables
}