/*
 * DerbyDbTableContentInfo.java
 *
 * Created on 2007-11-24, 20:30:52
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
public class DerbyDbTableContentInfo extends DbObjectIdentified {
  
  public DerbyDbTableContentInfo(DbObjectIdentified owner) {
    super("CONTENT", owner);
  }

  public String[] getMemberNames() {
    return new String[] {};
  }

  public Variant[] getMemberValues() {
    return new Variant[] {};
  }

  public void refresh() throws Exception {
    
  }
  
}
