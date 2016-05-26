/*
 * ViewAsStringPanel.java
 *
 * Created on 26 listopad 2007, 18:53
 */

package pl.mpak.orbada.gui.comps.table.view;

import java.io.Closeable;
import java.io.IOException;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.variant.Variant;
import pl.mpak.util.variant.VariantType;

/**
 *
 * @author  akaluza
 */
public class ViewAsHexPanel extends javax.swing.JPanel implements Closeable {
  
  private Variant value;
  
  /** Creates new form ViewAsStringPanel 
   * @param value 
   */
  public ViewAsHexPanel(Variant value) {
    this.value = value;
    initComponents();
    init();
  }
  
  private void init() {
    if (value != null) {
      convert();
    }
  }
  
  private void convert() {
    StringBuilder sb = new StringBuilder();
    StringBuilder sbLine = new StringBuilder();
    try {
      byte[] buffer = null;
      if (((Variant)value).getValueType() == VariantType.varBinary) {
        buffer = value.getBinary();
      }
      else {
        buffer = value.getString().getBytes();
      }
      for (int i=0; i<buffer.length; i++) {
        sb.append(String.format("%02X", new Object[] {buffer[i]}));
        if ((i +1) % 8 == 0 && (i +1) % 16 != 0) {
          sb.append('|');
        }
        else {
          sb.append(' ');
        }
        if (buffer[i] < 32) {
          sbLine.append((char)0);
        }
        else {
          sbLine.append((char)buffer[i]);
        }
        if ((i +1) % 8 == 0 && (i +1) % 16 != 0) {
          sbLine.append('|');
        }
        if ((i +1) % 16 == 0) {
          sb.append(" | " +sbLine.toString());
          sbLine.setLength(0);
          sb.append('\n');
        }
      }
      if (sbLine.length() > 0) {
        for (int i=buffer.length % 16; i<16; i++) {
          sb.append("   ");
        }
        sb.append(" | " +sbLine.toString());
      }
    } catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
    textView.setText(sb.toString());
    textView.setCaretPosition(0);
  }
  
  @Override
  public void close() throws IOException {
    
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    textView = new javax.swing.JTextArea();

    setLayout(new java.awt.BorderLayout());

    textView.setColumns(20);
    textView.setEditable(false);
    textView.setFont(new java.awt.Font("Courier New", 0, 12));
    textView.setRows(5);
    jScrollPane1.setViewportView(textView);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea textView;
  // End of variables declaration//GEN-END:variables
  
}
