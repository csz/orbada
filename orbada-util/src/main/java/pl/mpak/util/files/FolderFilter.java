package pl.mpak.util.files;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Klasa ogranicza list� tylko do katalog�w
 * 
 * @author Andrzej Ka�u�a
 *
 */
public class FolderFilter implements FilenameFilter {

  public FolderFilter() {
    super();
  }

  public boolean accept(File dir, String name) {
    return new File(dir.getName() +"/" +name).isDirectory();
  }

}
