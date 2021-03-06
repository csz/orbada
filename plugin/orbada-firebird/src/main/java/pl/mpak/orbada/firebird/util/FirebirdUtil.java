/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.firebird.util;

import javax.swing.Icon;

/**
 *
 * @author akaluza
 */
public class FirebirdUtil {

  public static Icon getObjectIcon(String objectType) {
    if ("TRIGGER".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/trigger.gif");
    }
    else if ("FUNCTION".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/function.gif");
    }
    else if ("PROCEDURE".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/procedure.gif");
    }
    else if ("TABLE".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/table.gif");
    }
    else if ("VIEW".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/view.gif");
    }
    else if ("GENERATOR".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/sequence.gif");
    }
    else if ("EXCEPTION".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/exception.gif");
    }
    else if ("DOMAIN".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/domain.gif");
    }
    else if ("INDEX".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/index.gif");
    }
    else if ("COLUMN".equalsIgnoreCase(objectType)) {
      return pl.mpak.sky.gui.swing.ImageManager.getImage("/res/icons/column.gif");
    }
    else {
      return null;
    }
  }

}
