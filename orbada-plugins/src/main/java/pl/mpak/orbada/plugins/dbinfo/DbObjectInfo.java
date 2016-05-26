/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.dbinfo;

/**
 * <p>Informuje, �e wybrany obiekt jest obiektem g��wnym bazy danych<br>
 * TABLE, VIEW, FUNTION, PROCEDURE, PACKAGE, SEQUENCE, etc
 * <p>Mo�e (ale nie musi) by� zaimplementowana przez wtyczk� i s�u�y�
 * do pobrania informacji o obiekcie gdy� getName i getRemarks zdefiniowane s�
 * na poziomie DbObjectIdentified
 * @author akaluza
 */
public interface DbObjectInfo {
  
  public String getObjectType();

}
