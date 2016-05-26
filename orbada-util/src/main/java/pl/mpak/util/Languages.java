/*
 * Created on 2005-07-11
 *
 */
package pl.mpak.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Andrzej Ka�u�a
 * 
 * Klasa obs�ugi wieloj�zykowo�ci Trzeba j� utworzy� w ka�dym oknie
 */
public class Languages {
  private ResourceBundle resourceBundle = null;

  private String name = "";
  private static Locale locale = Locale.getDefault();

  public Languages(String name) {
    super();
    setName(name);
  }

  public Languages(String name, Locale locale) {
    super();
    setLocale(locale);
    setName(name);
  }

  public Languages(Class<?> clazz) {
    this(clazz.getName());
  }

  public Languages() {
    super();
  }

  /**
   * Pozwala zainicjowa� obiekt tej klasy w�asnym j�zykiem i utworzonym obiektem
   * ResourceBundle
   * 
   * @param ress
   */
  public Languages(ResourceBundle ress) {
    super();
    resourceBundle = ress;
  }

  private ResourceBundle createResourceBundle(String key, Locale locale) {
    try {
      return ResourceBundle.getBundle(key, locale, getClass().getClassLoader());
    }
    catch (MissingResourceException mre) {
      ExceptionUtil.processException(mre);
      return null;
    }
  }

  public Enumeration<String> getKeys() {
    return resourceBundle.getKeys();
  }

  public ResourceBundle getResource() {
    return resourceBundle;
  }

  /**
   * Zwraca ci�g znak�w zwi�zany z tym j�zykiem, je�li nie ma klucza zwraca ci�g
   * pusty
   * 
   * @param key
   * @return
   */
  public String getString(String key) {
    return getString(key, "");
  }

  public String getString(String key, String defValue) {
    if (resourceBundle != null) {
      try {
        return resourceBundle.getString(key);
      }
      catch (MissingResourceException mre) {
        return defValue;
      }
    }
    else {
      return defValue;
    }
  }

  public String getString(String key, Object[] args) {
    return String.format(getString(key, ""), args);
  }

  public void setName(String name) {
    if (!StringUtil.equals(this.name, name)) {
      this.name = name;
      if (this.name != "") {
        resourceBundle = createResourceBundle(name, locale);
      }
      else {
        //
      }
    }
  }

  public String getName() {
    return name;
  }

  public static void setLocale(Locale aLocale) {
    locale = aLocale;
  }

  public static Locale getLocale() {
    return locale;
  }
}
