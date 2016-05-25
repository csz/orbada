package pl.mpak.util.variant;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;

/**
 * 
 * @author Andrzej Ka�u�a
 * 
 * <p>Interfejs kt�ry s�u�y do utworzenia po��czenia pomi�dzy Variant,
 * a dowolnym innym, ��o�onym typem, obiektem javy.
 * 
 * <p>Interfejs ten pozwala utworzy� Variant zawieraj�cy dan� klas� i umo�lija
 * u�y� funkcji Variant.write, read, compareTo i innych odwo�uj�c si� do Variant
 * 
 * <p>Uwaga: Ka�da klasa kt�ra implementuje ten interfejs musi si�
 * zarejestrowa� w Variant przy pomocy.
 * <pre>
 * static {
 *   Variant.registerVariantClass(serialVersionUID, Klasa.class);
 * }
 * </pre>
 * Je�li tego nie zrobi to Variant.read odczyta j� jako obiekt byte[]
 * 
 */
public interface VariantConnectable extends Serializable {

  /**
   * powinna zapisywa� do raf dane tego obiektu
   * @param raf
   */
  public void write(DataOutput raf);
  
  /**
   * powinna odczytywa� z raf dane tego obiektu
   * @param raf
   */
  public void read(DataInput raf);
  
  /**
   * Funkcja powinna zwraca� uwag� na Variant.isIgnoreCase()
   * @param variant
   * @return
   */
  public int compareTo(Variant variant);
  
  /**
   * Powinna zwraca� rozmiar (w bajtach) tego obiektu zapisywanego w pliku 
   * @return
   */
  public int getSize();
  
  /**
   * Powinna konwertowa� typ do podanego typu Variant i zwraca� odpowiedni obiekt
   * Domy�lnie mo�e to by� poprostu toString()
   * @param valueType jeden z VariantType
   * @return
   */
  public Object castTo(int valueType);
  
}
