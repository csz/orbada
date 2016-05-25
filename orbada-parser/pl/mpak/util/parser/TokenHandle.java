package pl.mpak.util.parser;

import java.io.IOException;

/**
 * @author Andrzej Ka�u�a
 * 
 * Interfejs do tworzenia tokenu
 * W nim powinna nast�pi� analiza jednego tokenu i wstawienie go do tokenBuffer
 * 
 * Przyk�ad: SimpleTokenHandle
 *
 */
public interface TokenHandle {

  public final static int NONE = 0;
  
  public int readToken(Tokenizer parser, StringBuffer tokenBuffer) throws IOException;
  
}
