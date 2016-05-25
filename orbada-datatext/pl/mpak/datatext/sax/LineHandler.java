package pl.mpak.datatext.sax;

import pl.mpak.datatext.DataTextException;

public interface LineHandler {

  /**
   * <p>Obs�uguje jeden odczytany wiersz.
   * @param line
   * @return true je�li reader ma przerwa� odczytywanie �r�d�a
   */
  public boolean lineReaded(String line) throws DataTextException;
  
}
