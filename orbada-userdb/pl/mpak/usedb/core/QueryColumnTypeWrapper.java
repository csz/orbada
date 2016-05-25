package pl.mpak.usedb.core;

import java.sql.SQLException;
import java.util.HashMap;

public interface QueryColumnTypeWrapper {
  
  public static HashMap<String, QueryColumnTypeWrapper> wrapperMap = new HashMap<String, QueryColumnTypeWrapper>();

  /**
   * <p>Powinna konwertowa� obiekt "o" bazy danych do typu kt�ry klasa Variant b�dzie w stanie zapisa� i odczyta�.
   * Mo�e to by� obiekt serializowany lub zgodny (i zarejestrowany) z VariantConnectable.
   * <p>W najprostrzej postaci mo�e by� konwertowany do typu String. 
   * @param o
   * @return
   */
  public Object convert(Object o) throws SQLException;
  
}
