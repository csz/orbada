/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.PleaseWait;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;

/**
 *
 * @author akaluza
 */
public abstract class PleaseWaitRendererProvider implements IOrbadaPluginProvider {

  protected IApplication application;
  protected JComponent component;

  public void setApplication(IApplication application) {
    this.application = application;
  }

  public void setComponent(JComponent component) {
    this.component = component;
  }

  public boolean isSharedProvider() {
    return true;
  }

  public void beginProcess() {

  }
  
  public void endProcess() {

  }

  /**
   * <p>Funkcja jest wywo�ywana raz na 100 milisekund, s�u�y do np p�ynnej animacji.
   * <p>Jej wywo�anie nie oznacza, �e b�dzie wywo�ana funkcja render
   */
  public abstract void control();

  /**
   * <p>Wywo�ywane jest wtedy gdy wymagane jest odrysowanie procesu oczekiwania
   * @param g2
   * @param waitArr - lista oczekiwa�, nigdy null
   */
  public abstract void render(Graphics2D g2, PleaseWait[] waitArr);

  /**
   * <p>Powinna zwr�ci� unikalny identyfikator rysowacza w celu ustalenia kt�ry ma dzia�a�
   * @return
   */
  public abstract String getRendererId();

  public abstract Rectangle getRenderBounds();

}
