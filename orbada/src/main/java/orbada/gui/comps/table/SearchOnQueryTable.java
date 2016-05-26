/*
 * SearchOnTable.java
 *
 * Created on 2007-10-27, 19:31:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package orbada.gui.comps.table;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import javax.swing.table.TableModel;
import pl.mpak.usedb.core.QueryListener;
import pl.mpak.usedb.gui.swing.QueryTable;
import pl.mpak.util.StringUtil;
import pl.mpak.util.timer.Timer;

/**
 *
 * @author akaluza
 */
public class SearchOnQueryTable {
  
  private final static pl.mpak.util.timer.TimerQueue timerQueue = pl.mpak.util.timer.TimerManager.getTimer("orbada-search-on-query-table");
  
  private QueryTable table;
  private Timer searchTimer;
  private String searchText = "";
  private long lastSearch = System.currentTimeMillis();
  
  public SearchOnQueryTable(QueryTable table) {
    this.table = table;
    init();
  }
  
  public void setSearchText(String searchText) {
    if (!StringUtil.nvl(this.searchText, "").equals(searchText)) {
      this.searchText = searchText;
      lastSearch = System.currentTimeMillis();
      if (this.searchText.length() > 0 && searchTimer != null) {
        searchTimer.restart();
      }
    }
  }

  private void init() {
    table.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyPressed(KeyEvent e) {
        if (!table.isEditing() && searchTimer != null && !e.isAltDown() && !e.isControlDown()) {
          if (System.currentTimeMillis() -lastSearch > 1000) {
            searchText = "";
          }
          if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (!StringUtil.nvl(searchText, "").equals("")) {
              setSearchText(searchText.substring(0, searchText.length() -1));
            }
          }
          else if (Character.isJavaIdentifierPart(e.getKeyChar()) && e.getKeyChar() > 31) {
            setSearchText(searchText +e.getKeyChar());
          }
        }
      }
      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
    table.getQuery().addQueryListener(new QueryListener() {
      @Override
      public void beforeScroll(EventObject event) {
      }
      @Override
      public void afterScroll(EventObject event) {
      }
      @Override
      public void beforeOpen(EventObject event) {
      }
      @Override
      public void afterOpen(EventObject event) {
        timerQueue.add(searchTimer = new Timer(250) {
          {
            setEnabled(false);
          }
          @Override
          public void run() {
            setEnabled(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
              @Override
              public void run() {
                findRowBySearchText(false);
              }
            });
          }
        });
      }
      @Override
      public void beforeClose(EventObject event) {
        if (searchTimer != null) {
          searchTimer.cancel();
          searchTimer = null;
        }
      }
      @Override
      public void afterClose(EventObject event) {
      }
      @Override
      public void flushedPerformed(EventObject event) {
      }
      @Override
      public void errorPerformed(EventObject event) {
      }
    });
  }
  
  public int findRowBySearchText(boolean startSelected) {
    String searchText_l = new String(searchText);
    if (table.getSelectedColumn() >= 0 && !StringUtil.nvl(searchText_l, "").equals("")) {
      TableModel tm = table.getModel();
      int modelIndex = table.getColumnModel().getColumn(table.getSelectedColumn()).getModelIndex();
      int r = (startSelected ? Math.max(table.getSelectedRow() +1, 0) : 0);
      while (r<tm.getRowCount()) {
        Object value = tm.getValueAt(r, modelIndex);
        if (value != null && value.toString().toUpperCase().startsWith(searchText_l.toUpperCase())) {
          final int row = r;
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              table.changeSelection(row, table.getSelectedColumn());
            }
          });
          return r;
        }
        r++;
      }
    }
    return -1;
  }
  
}
