package pl.mpak.usedb.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author akaluza
 * <p>Adnotacja s�urz�ca do definiowania w�a�ciwo�ci tabeli dla bean-a 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

  /**
   * <p>Nazwa tabeli
   * @return
   */
  String name();
  
  /**
   * <p>Kolumny klucza g��wnego
   * @return
   */
  String[] primaryKey() default {};
  
}
