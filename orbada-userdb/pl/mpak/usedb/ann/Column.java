package pl.mpak.usedb.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.mpak.util.variant.VariantType;


/**
 * @author akaluza
 * <p>Adnotacja s�urz�ca do definiowania kolumny tabeli dla bean-a 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Column {

  /**
   * <p>Nazwa kolumny tabeli
   * @return
   */
  String name();
  
  /**
   * <p>Typ kolumny, jedna ze sta�ych VariantType
   * @return
   */
  int type() default VariantType.varString;
  
  /**
   * <p>Czy kolumna ma by aktualizowana przy poleceniu update
   * @return
   */
  boolean updatable() default true;
  
  /**
   * <p>Warto�� domy�lna kolumny
   * @return
   */
  String defaultValue() default "";
  
  /**
   * <p>Grupa p�nego odczytu. Je�li wype�niona to b�dzie odczytana wtedy gdy b�dzie potrzebna.
   * <p>Na razie nie stosowa�!
   * @return
   */
  String lazyGroup() default "";
  
}
