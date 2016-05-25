package pl.mpak.util;

import java.util.ArrayList;

/**
 * Klasa pozwala pobra� z ci�gu znak�w elementy rozdzielone od siebie
 * znakami rozdzielaj�cymi (domy�lnie ,;).
 * Je�li ci�g znak�w zostanie umieszczony w cudzys�owie wtedy w ci�gu
 * mog� wyst�pi� znaki oddzielaj�ce.
 * Cudzys��w uzyskuje sie poprzez jego podw�jne wyst�pienie w ci�gu znak�w.
 *
 * Przyk�ad
 *   CommaDelimiter cd = new CommaDelimiter("Andrzej Ka�u�a,\"Warszawski park le�ny\"");
 *   while (cd.moreTokens()) {
 *     System.out.println(cd.nextToken());
 *   }
 * 
 * @author Administrator
 *
 */
public class CommaDelimiter {
  
  public final static String DEFAULT_DELIMS = ",; ";
  
  private String text;
  private String delimiters;
  private int index;

  public CommaDelimiter() {
    super();
    setText("");
    setDelimiters(",; ");
    index = 0;
  }

  public CommaDelimiter(String text) {
    this();
    setText(text);
  }

  public CommaDelimiter(String text, String delim) {
    this(text);
    setDelimiters(delim);
  }

  /**
   * Przetwarza "text" i zwraca element z pozycji "index"
   * "index" rozpoczyna si� od warto�ci 1
   * 
   * @param text
   * @param index rozpoczyna si� od warto�ci 1 (pierwszy element z listy)
   * @return
   */
  public static String getCommaString(String text, int index, String delim) {
    CommaDelimiter cd = new CommaDelimiter(text, delim);
    String token;
    
    while (cd.moreTokens()) {
      index--;
      token = cd.nextToken();
      if (index == 0) {
        return token;
      }
    }
    return "";
  }
  
  public static String getCommaString(String text, int index) {
    return getCommaString(text, index, DEFAULT_DELIMS);
  }

  public static String[] getCommaStrings(String text) {
    return getCommaStrings(text, DEFAULT_DELIMS);
  }
  
  public static String[] getCommaStrings(String text, String delim) {
    CommaDelimiter cd = new CommaDelimiter(text, delim);
    ArrayList<String> result = new ArrayList<String>();
    
    while (cd.moreTokens()) {
      result.add(cd.nextToken());
    }
    
    return result.toArray(new String[result.size()]);
  }
  
  public static String createCommaString(String[] strings) {
    String result = "";
    for (int i=0; i<strings.length; i++) {
      String temp = strings[i];
      temp = StringUtil.replaceString(strings[i], "\"", "\"\"");
      temp = StringUtil.replaceString(temp, "\n", "\\n");
      result = StringUtil.addStringChar(result, "\"" +temp +"\"", ",");
    }
    return result;
  }
  
  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setDelimiters(String delimiters) {
    this.delimiters = delimiters;
  }

  public String getDelimiters() {
    return delimiters;
  }

  /**
   * Czy jest wi�cej element�w na li�cie
   * 
   * @return
   */
  public boolean moreTokens() {
    return index < (text == null ? 0 : text.length());
  }
  
  /**
   * Pobiera kolejny element z listy
   * 
   * @return
   */
  public String nextToken() {
    StringBuilder sb = new StringBuilder();
    
    while (index < text.length()) {
      if (text.charAt(index) == '"') {
        index++;
        while (index < text.length()) {
          if (text.charAt(index) == '"') {
            if (index +1 < text.length() && text.charAt(index +1) == '"') {
              index+=2;
              sb.append('"');
            }
            else {
              index++;
              break;
            }
          }
          else if (text.charAt(index) == '\\') {
            if (index +1 < text.length() && text.charAt(index +1) == 'n') {
              index+=2;
              sb.append('\n');
            }
            else {
              sb.append(text.charAt(index));
              index++;
              break;
            }
          }
          else {
            sb.append(text.charAt(index));
            index++;
          }
        }
      }
      else if (delimiters.indexOf(text.charAt(index)) >= 0) {
        index++;
        break;
      }
      else {
        sb.append(text.charAt(index));
        index++;
      }
    }
    
    return sb.toString();
  }
}
