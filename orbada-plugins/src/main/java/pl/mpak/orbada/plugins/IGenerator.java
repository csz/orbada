/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import java.math.BigInteger;

/**
 * <p>Uniwersalny generator Orbada
 * @author akaluza
 */
public interface IGenerator {
  
  public String getName();
  
  /**
   * <p>Wywo�anie powoduje pobranie nowej warto�ci
   * @return
   */
  public BigInteger getNextValue() throws GeneratorException;
  
  /**
   * <p>Zwraca uprzednio wygenerowan� getNextValue() warto��
   * @return
   */
  public BigInteger getCurrValue() throws GeneratorException;
  
  public BigInteger getMinValue();
  
  public BigInteger getMaxValue();
  
  /**
   * <p>Zwraca warto�� kt�ra b�dzie zwi�ksza�/zmniejsza� warto�� generatora
   * @return
   */
  public BigInteger getIncrement();
  
  public boolean getCycle();
  
}
