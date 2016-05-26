/*
 * DatabaseInfoProvider.java
 *
 * Created on 2007-10-23, 22:42:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Component;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.dbinfo.DbDatabaseInfo;
import pl.mpak.usedb.core.Database;
import pl.mpak.usedb.util.JdbcUtil;

/**
 * <p>Klasa do zbierania informacji o bazie danych.
 * @author akaluza
 */
public abstract class DatabaseInfoProvider implements IOrbadaPluginProvider {
  
  public final static int KEYWORDS = 1;
  public final static int USER_TABLES = 2;
  public final static int EXCEPTIONS = 3;
  public final static int SQL_FUNCTIONS = 4;
  public final static int USER_FUNCTIONS = 5;
  public final static int PUBLIC_TABLES = 6;
  public final static int DATA_TYPES = 7;
  public final static int SCHEMAS = 8;
  public final static int TABLE_TYPES = 9;
  public final static int OPERATORS = 10;
  
  protected IApplication application;
  
  public void setApplication(IApplication application) {
    this.application = application;
  }
  
  public boolean isSharedProvider() {
    return true;
  }
  
  /**
   * <p>Powinna zwr�ci� informacj� czy widok przeznaczony jest dla tej bazy danych
   * @param database 
   * @return 
   */
  public abstract boolean isForDatabase(Database database);
  
  public abstract String[] getKeywords(Database database);
  public abstract String[] getUserTables(Database database);
  public abstract String[] getExceptions(Database database);
  public abstract String[] getSqlFunctions(Database database);
  public abstract String[] getUserFunctions(Database database);
  public abstract String[] getPublicTables(Database database);
  public abstract String[] getDataTypes(Database database);
  public abstract String[] getTableTypes(Database database);
  public abstract String[] getOperators(Database database);
  public abstract String[] getSchemas(Database database);

  /**
   * <p>Powinna zwr�ci� rozszerzone informacje o bazie danych
   * @param database
   * @return
   */
  public abstract DbDatabaseInfo getDatabaseInfo(Database database);

  /**
   * <p>Powinna zwr�ci� list� kolumn klucza g��wnego tabeli.
   * <p>U�ywana mo�e by� do wyznaczania kolumn dla potrzeb edycji danych w tabeli
   * @param catalogName mo�e by� null
   * @param schemaName mo�e by� null wtedy domy�lny schemat
   * @param tableName
   * @return null oznacza, �e tabela nie ma klucza g��wnego
   */
  public String[] getUniqueIdentFields(Database database, String catalogName, String schemaName, String tableName) {
    return JdbcUtil.getPrimaryKeyFields(database, catalogName, schemaName, tableName);
  }
  
  /**
   * <p>Powinna zwr�ci� rozsze�one informacje na panelach, kt�re wstawione b�d� na zak�adki
   * informacji o bazie danych.
   * <p>Component powinien implementowa� interfejs pl.mapk.util.Titleable aby zwr�ci�
   * tytu� zak�adki.
   * @param database 
   * @param callBack 
   */
  public abstract Component[] getExtendedPanelInfo(Database database);
  
  /**
   * <p>Powinno spowodowa� zresetowanie informacji na temat bazy danych
   * @param database 
   */
  public abstract void resetDatabaseInfo(Database database);
  
  /**
   * <p>Powinna zwr�ci� rozsze�one informacje o bazie danych dla potrzeb wy�wietlenia
   * @param database 
   * @return 
   */
  public String getBanner(Database database) {
    return getDatabaseInfo(database).getBanner();
  }
  
  /**
   * <p>Powinna zwr�ci� tylko i wy�acznie dok�adn� wersj� bazy danych, major.minor.releas.build
   * albo co� w tym rodzaju (max 100 znak�w)
   * @param database 
   * @return 
   */
  public String getVersion(Database database) {
    return getDatabaseInfo(database).getVersion();
  }
  
}
