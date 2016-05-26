package pl.mpak.util.files;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Klasa pozwala ograniczy� list� plik�w i katalog�w do podanego wzorca nazwy
 * 
 * @author Andrzej Ka�u�a
 *
 */
public class PatternFilenameFilter implements FilenameFilter {

  private Pattern pattern;

  public PatternFilenameFilter(String regex) {
    super();
    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
  }

  public boolean accept(File dir, String name) {
    return pattern.matcher(name).matches();
  }
  
}
