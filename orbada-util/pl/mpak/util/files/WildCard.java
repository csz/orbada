package pl.mpak.util.files;

import pl.mpak.util.StringUtil;

public class WildCard {

  /**
   * Funkcja konwertuje wild card (dos/windows) na wyra�enie regularne "regex"
   * dla potrzeb Pattern i Match
   * * dowolny ci�g znak�w
   * ? dowolny znak
   * ; lub
   * np *.jar|*.zip
   * 
   * @param wildcard
   * @return ci�g znak�w dla Pattern
   */
  public static String getRegex(String wildcard) {    
    String result = StringUtil.replaceString(wildcard, ".", "\\.");
    result = StringUtil.replaceString(result, "*", ".*");
    result = StringUtil.replaceString(result, "?", ".");
    result = StringUtil.replaceString(result, ";", "|");
    return result;
  }

}
