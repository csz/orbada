package pl.mpak.orbada.localhistory.gui;

import javax.swing.SpinnerNumberModel;
import pl.mpak.orbada.localhistory.OrbadaLocalHistoryPlugin;
import pl.mpak.orbada.localhistory.services.LocalHistorySettingsService;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.plugins.ISettingsComponent;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;

/**
 *
 * @author  akaluza
 */
public class GeneralSettingsPanel extends javax.swing.JPanel implements ISettingsComponent {
  
  private StringManager stringManager = StringManagerFactory.getStringManager("local-history");

  private IApplication application;
  private ISettings settings;
  
  /** Creates new form GeneralSettingsProvider */
  public GeneralSettingsPanel(IApplication application) {
    this.application = application;
    initComponents();
    init();
  }
  
  private void init() {
    spinDeleteAfterDays.setModel(new SpinnerNumberModel(1, 0, 1000, 1));
    settings = application.getSettings(LocalHistorySettingsService.settingsName);
    restoreSettings();
  }
  
  public void restoreSettings() {
    spinDeleteAfterDays.setValue(settings.getValue(LocalHistorySettingsService.setDeleteAfterDays, 30L).intValue());
    checkTurnedOn.setSelected(settings.getValue(LocalHistorySettingsService.setTurnedOn, true));
  }

  public void applySettings() {
    settings.setValue(LocalHistorySettingsService.setDeleteAfterDays, (long)(Integer)spinDeleteAfterDays.getValue());
    settings.setValue(LocalHistorySettingsService.setTurnedOn, checkTurnedOn.isSelected());
    settings.store();
  }

  public void cancelSettings() {
    restoreSettings();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    spinDeleteAfterDays = new javax.swing.JSpinner();
    jLabel2 = new javax.swing.JLabel();
    checkTurnedOn = new javax.swing.JCheckBox();

    jLabel1.setText(stringManager.getString("GeneralSettingsPanel-remember-history-past-dd")); // NOI18N

    jLabel2.setText(stringManager.getString("days")); // NOI18N

    checkTurnedOn.setText(stringManager.getString("GeneralSettingsPanel-checkTurnedOn-text")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(checkTurnedOn)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(spinDeleteAfterDays, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel2)))
        .addContainerGap(237, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(checkTurnedOn)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jLabel2)
          .addComponent(spinDeleteAfterDays, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(297, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox checkTurnedOn;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JSpinner spinDeleteAfterDays;
  // End of variables declaration//GEN-END:variables
  
}
