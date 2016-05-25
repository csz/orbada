package pl.mpak.usedb.gui.swing;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author akaluza
 * <p>S�u�y do obs�ugi modelu kolumn automatycznie tworzonych przy otwieraniu Query
 */
public interface QueryTableColumnModelListener extends EventListener {

  public void beforeCreateColumns(EventObject e);
  
  public void afterCreateColumns(EventObject e);
  
}
