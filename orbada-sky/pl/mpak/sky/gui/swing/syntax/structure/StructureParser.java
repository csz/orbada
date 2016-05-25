package pl.mpak.sky.gui.swing.syntax.structure;

import java.util.HashMap;
import java.util.List;

import pl.mpak.sky.gui.swing.syntax.SyntaxDocument;
import pl.mpak.sky.gui.swing.syntax.SyntaxEditor.TokenRef;
import pl.mpak.sky.gui.swing.syntax.TokenCursor;

public abstract class StructureParser {

  private TokenCursor tc;
  private int[] skipBlanks;
  protected HashMap<String, Integer> keyWordList;
  private boolean ignoreCase = true;
  
  public StructureParser(int[] skipBlanks) {
    this.skipBlanks = skipBlanks;
    this.keyWordList = new HashMap<String, Integer>(); 
  }
  
  public void setTokenList(List<TokenRef> list) {
    tc = new TokenCursor(list, skipBlanks);
    tc.setIgnoreCase(ignoreCase);
  }
  
  public void setIgnoreCase(boolean ignoreCase) {
    if (tc != null) {
      tc.setIgnoreCase(ignoreCase);
    }
    else {
      this.ignoreCase = ignoreCase;
    }
  }
  
  protected TokenRef checkNull(TokenRef token) throws ParserException {
    if (token == null) {
      throw new ParserException("Nieoczekiwane zako�czenie kodu!");
    }
    return token;
  }
  
  /**
   * <p>Zwraca nast�pny element lub null je�li koniec
   * @return
   * @throws ParserException 
   * @see getToken().prev
   * @see getToken().next
   * @see getToken()
   */
  protected TokenRef nextToken() throws ParserException {
    return checkNull(tc.nextToken(false));
  }
  
  /**
   * <p>Zwraca nast�pny element i przy okazji pomija wszystko co zosta�o zdefiniowane w skipBlank()
   * @param skipBlanks
   * @return
   * @throws ParserException
   * @see skipBlank()
   */
  protected TokenRef nextToken(boolean skipBlanks) throws ParserException {
    return checkNull(tc.nextToken(skipBlanks));
  }
  
  /**
   * <p>Domy�lnie pomija elementy SyntaxDocument.NONE ale mo�na funkcj� przej�� i pomija� inne elementy
   * @return
   * @throws ParserException
   * @see nextToken(boolean skipBlanks)
   */
  protected TokenRef skipBlank() throws ParserException {
    return checkNull(tc.skipBlank());
  }
  
  /**
   * <p>Zwraca poprzednio uzyskany token.<br>
   * Nie myli� z getToken().prev
   * @return
   * @throws ParserException
   */
  protected TokenRef getLastToken() throws ParserException {
    return checkNull(tc.lastToken());
  }
  
  /**
   * <p>Zwraca offset dla okre�lenia pocz�tku bloku
   * @return
   * @throws ParserException
   */
  protected int getStartOffset() throws ParserException {
    return tc.getStartOffset();
  }
  
  /**
   * <p>Zwraca offset dla okre�lenia ko�ca bloku
   * @return
   * @throws ParserException
   */
  protected int getEndOffset() throws ParserException {
    checkNull(tc.token());
    return tc.getEndOffset();
  }
  
  /**
   * <p>Sprawdzenie czy token jest identyczny co text
   * @param text
   * @return
   * @throws ParserException
   * @see Parser(Iterator&lt;TokenRef&gt; iterator, boolean <b>ignoreCase</b>)
   */
  protected boolean isToken(String text) throws ParserException {
    checkNull(tc.token());
    return tc.isToken(text);
  }
  
  /**
   * <p>Zwraca ci�g znak�w aktualnego tokenu
   * @return
   * @throws ParserException
   */
  protected String getTokenString() throws ParserException {
    checkNull(tc.token());
    return tc.getString();
  }
  
  /**
   * <p>Pozwla zmieni� styleId elementu zale�nego (w edytorze)
   * @param styleId
   * @throws ParserException
   */
  protected void setStyle(int styleId) throws ParserException {
    checkNull(tc.token());
    getToken().ref.styleId = styleId;
  }
  
  /**
   * <p>Zwraca bierz�cy token
   * @return
   * @throws ParserException
   */
  protected TokenRef getToken() throws ParserException {
    return checkNull(tc.token());
  }
  
  /**
   * <p>Umieszcza s�owo na specjalnej li�cie, kt�ra na ko�cu parsowania mo�e by� u�yta do ustawienia styli token�w
   * @param keyWord
   * @param styleId
   * @throws ParserException
   * @see updateKeyWordStyles
   */
  protected void putKeyWord(String keyWord, int styleId) throws ParserException {
    keyWordList.put((tc.isIgnoreCase() ? keyWord.toUpperCase() : keyWord), styleId);
    if (isToken(keyWord)) {
      setStyle(styleId);
    }
  }
  
  protected boolean updateStyle(int orygStyleId) {
    return orygStyleId == SyntaxDocument.NONE;
  }
  
  /**
   * <p>Aktualizuje tokeny edytora stylami okre�lonymi na li�cie s��w kluczowych
   * @throws ParserException
   * @see putKeyWord
   */
  protected void updateKeyWordStyles() {
    tc.reset();
    try {
      while (nextToken() != null) {
        Integer styleId = keyWordList.get((tc.isIgnoreCase() ? getToken().token.toUpperCase() : getToken().token));
        if (styleId != null && updateStyle(getToken().styleId)) {
          setStyle(styleId);
        }
      }
    } catch (ParserException e) {
    }
  }

  /**
   * <p>G��wna funkcja do parsowania
   * @return
   * @throws ParserException
   */
  public abstract BlockElement parse() throws ParserException;
  
}
