package pl.mpak.usedb.core;

import pl.mpak.sky.SkyException;
import pl.mpak.usedb.UseDBException;
import pl.mpak.usedb.UseDBObject;

public class ParametrizedCommand extends UseDBObject {
  private static final long serialVersionUID = 2760793526568164758L;

  private String sqlText = "";
  protected String preparedSqlText = null;
  protected transient ParameterList parameterList = null;
  protected boolean paramCheck = true;
  protected Database database = null;
  protected boolean databaseSet = false;

  public ParametrizedCommand() {
    super();
    parameterList = new ParameterList();
  }
  
  public ParametrizedCommand(Database database) {
    this();
    setDatabase(database);
  }
  
  public void doUpdateSQLText() {
    
  }

  /**
   * Ustawienie polecenia SQL zamyka kursor je�li by� otwarty
   * Poleceni przygotowuje r�wnie� list� parametr�w
   * Mo�na si� do nich odwo�a� poprzez paramByName() lub getParameterList()
   * Przed ustawieniem SqlText mo�na wywo�a� setParseParameters(false) aby
   * parametry nie by�y przetwarzane
   * 
   * @param sqlText
   * @throws UseDBException 
   * @throws SkyException 
   */
  public void setSqlText(String sqlText) throws UseDBException {
    if (!this.sqlText.equals(sqlText)) {
      doUpdateSQLText();
      this.sqlText = sqlText;
      if (paramCheck) {
        preparedSqlText = parameterList.parseParameters(sqlText);
      }
      else {
        preparedSqlText = sqlText;
        parameterList.clear();
      }
    }
  }

  public String getSqlText() {
    return this.sqlText;
  }

  /**
   * Pozwala kontrolowa� sprawdzanie parametr�w w przekazywanym zapytaniu SQL
   * Sparsowane parametry pojawi� si� w ParameterList dost�pne r�wnie� poprzez paramByName()
   *  
   * @see paramByName
   * @see getParameterList
   */
  public void setParamCheck(boolean paramCheck) {
    this.paramCheck = paramCheck;
  }

  public boolean getParamCheck() {
    return paramCheck;
  }

  /**
   * Pozwala uzyskac dost�p do parametru o podanej nazwie
   * Wywo�uje wyj�tek je�li parametr o podanej nazwie nie istnieje
   * Aby sprawdzi� czy podany parametr istnieje na li�cie nale�y si� pos�u�y� getParamList().findParamByName();
   * 
   * @param name
   * @return
   * @throws UseDBException 
   * @throws SkyException
   */
  public Parameter paramByName(String name) throws UseDBException {
    return parameterList.paramByName(name);
  }
  
  public ParameterList getParameterList() {
    return parameterList;
  }
  
  public int getParameterCount() {
    return parameterList.parameterCount();
  }
  
  public Parameter getParameter(int index) {
    return parameterList.getParameter(index);
  }
  
  public void setDatabase(Database database) {
    if (this.database == null && database != null && !databaseSet) {
      database.statCreatedCommands++;
      databaseSet = true;
    }
    this.database = database;
  }

  public Database getDatabase() {
    return database;
  }

  /**
   * <p>Zwraca przygotowane do wykonania polecenie SQL 
   * @return
   * @see setSqlText
   */
  public String getPreparedSqlText() {
    return preparedSqlText;
  }
  
}
