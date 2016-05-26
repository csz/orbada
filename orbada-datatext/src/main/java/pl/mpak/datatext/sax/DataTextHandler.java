package pl.mpak.datatext.sax;

import pl.mpak.datatext.DataTextException;

public interface DataTextHandler {

  /**
   * <p>Linia rozpoczynaj�ca si� od DataTextFormat.COMMENT_CHAR '.'
   * @param comment
   */
  public void commentReaded(String comment) throws DataTextException;
  
  /**
   * <p>Linia rozpoczynaj�ca si� od DataTextFormat.TABLE_CHAR '#'
   * <p>Wywo�ywane w przypadku wyst�pienia powy�szego oraz gdy nie wyst�pi�o powy�sze,
   * a wyst�pi� DataTextFormat.HEADER_CHAR '-'. Wtedy tableName = "".
   * @param tableName
   */
  public void tableNameReaded(String tableName) throws DataTextException;
  
  /**
   * <p>Linia rozpoczynaj�ca si� od DataTextFormat.HEADER_CHAR '-'
   * @param field
   */
  public void headerReaded(DataTextColumn[] columns) throws DataTextException;
  
  /**
   * <p>Linia rozpoczynaj�ca si� od DataTextFormat.DATA_CHAR ' '
   * @param data
   */
  public void dataReaded(String[] datas) throws DataTextException;
  
  public void emptyLineReaded() throws DataTextException;
  
}
