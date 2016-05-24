package pl.mpak.orbada.reports.gui;

import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import pl.mpak.orbada.reports.db.ReportUsersUpdater;
import pl.mpak.orbada.reports.util.Applyable;
import pl.mpak.sky.gui.mr.ModalResult;
import pl.mpak.sky.gui.swing.MessageBox;
import pl.mpak.sky.gui.swing.comp.TableColumn;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.core.Query;
import pl.mpak.util.variant.Variant;
import pl.mpak.util.id.UniqueID;

/**
 *
 * @author  akaluza
 */
public class ReportUserListPanel extends javax.swing.JPanel implements Applyable {

  private Database database;
  private String orep_id;
  private ArrayList<ReportUsersUpdater> list;
  
    /** Creates new form ProjectCategoryListPanel */
  public ReportUserListPanel(Database database, String orep_id) {
    this.database = database;
    this.orep_id = orep_id;
    this.list = new ArrayList<ReportUsersUpdater>();
    initComponents();
    init();
  }

  private void init() {
    tableUsers.setModel(new AbstractTableModel() {
      public int getRowCount() {
        return list.size();
      }
      public int getColumnCount() {
        return 0;
      }
      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
	return columnIndex == 0;
      }
      @Override
      public Class<?> getColumnClass(int columnIndex) {
	return (new Class<?>[] {Boolean.class, String.class})[columnIndex];
      }
      public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
          return list.get(rowIndex).isChecked();
        }
        else if (columnIndex == 1) {
          return list.get(rowIndex).getUsrName();
        }
        return null;
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
          ReportUsersUpdater i = list.get(rowIndex);
          if ((Boolean)aValue) {
            i.setChecked(true);
          } else {
            i.setChecked(false);
          }
          fireTableRowsUpdated(rowIndex, rowIndex);
        }
      }
    });
    tableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableUsers.addColumn(new TableColumn(0, 30, java.util.ResourceBundle.getBundle("pl/mpak/orbada/orbada-reports").getString("zn")));
    tableUsers.addColumn(new TableColumn(1, 350, java.util.ResourceBundle.getBundle("pl/mpak/orbada/orbada-reports").getString("users")));
    
    refresh();
  }
  
  private void refresh() {
    list.clear();
    Query query = database.createQuery();
    try {
      query.setSqlText(ReportUsersUpdater.getSql());
      query.paramByName("orep_id").setString(orep_id);
      query.open();
      while (!query.eof()) {
        ReportUsersUpdater i = new ReportUsersUpdater(database);
        i.updateFrom(query);
        i.setUsrName(query.fieldByName("usr_name").getString());
        i.setExists(!"".equals(i.getId()));
        list.add(i);
        query.next();
      }
    }
    catch (Exception ex) {
      MessageBox.show(this, java.util.ResourceBundle.getBundle("pl/mpak/orbada/orbada-reports").getString("error"), ex.getMessage(), ModalResult.OK, MessageBox.ERROR);
    }
    finally {
      query.close();
    }
    tableUsers.revalidate();
  }

  public void apply() throws Exception {
    for (ReportUsersUpdater u : list) {
      if (!u.isExists() && u.isChecked()) {
        u.setPrimaryKeyValue(new Variant(new UniqueID().toString()));
        u.applyInsert();
      }
      else if (u.isExists() && !u.isChecked()) {
        u.applyDelete();
      }
      else if (u.isChanged()) {
        u.applyUpdate();
      }
    }
  }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    tableUsers = new pl.mpak.orbada.gui.comps.table.Table();

    tableUsers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jScrollPane1.setViewportView(tableUsers);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private pl.mpak.orbada.gui.comps.table.Table tableUsers;
  // End of variables declaration//GEN-END:variables

}