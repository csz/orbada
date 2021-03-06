/*
 * DerbyDbTableColumnInfo.java
 *
 * Created on 2007-11-15, 19:41:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.derbydb.dbinfo;

import pl.mpak.orbada.plugins.dbinfo.DbObjectIdentified;
import pl.mpak.util.variant.Variant;

/**
 *
 * @author akaluza
 */
public class DerbyDbViewColumnInfo extends DbObjectIdentified {
  
  private int position;
  private String type;
  
  public DerbyDbViewColumnInfo(String name, DerbyDbViewInfo owner) {
    super(name, owner);
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getMemberNames() {
    return new String[] {"Pozycja", "Typ"};
  }

  public Variant[] getMemberValues() {
    return new Variant[] {new Variant(getPosition()), new Variant(getType())};
  }

  public void refresh() throws Exception {
  }
  
}
