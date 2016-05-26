/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import java.awt.Image;
import javax.swing.ImageIcon;
import pl.mpak.sky.gui.swing.ImageManager;

/**
 * <p>Klasa, kt�rej obiekt s�u�y do okre�lania w�a�ciwo�ci dla mechanizmu oznaczania oczekiwania.
 * <p>Parametry<br>
 * - Image/ImageIcon image - obrazek do wy�wietlenia<br>
 * - String message - wiadomo�� precyzuj�ca operacj� (nie d�uga)<br>
 * - int waitMilis - czas w milisekundach, okre�la po jakim czasie oczekiwanie ma si� pojawi�<br>
 * &nbsp;    W przypadku tego parametru obiekt musi by� tworzony dla ka�dej operacji osobno.<br>
 * &nbsp;    W konstruktorze jest pobierany czas systemowy w celu opu�nienia wy�wietlania na ekranie.
 * @author akaluza
 * @see IApplication.startPleaseWait
 * @see IApplication.stopPleaseWait
 */
public class PleaseWait {

  /**
   * <p>Pusty PleaseWait, wy�wietli si� tylko "kr�cio�ek"
   */
  public final static PleaseWait EMPTY = new PleaseWait();

  private volatile Image image;
  private volatile String message;
  private long waitTime;
  private int waitMilis;
  private int progress;

  public PleaseWait() {
    this.waitTime = System.currentTimeMillis();
    this.progress = 100;
  }

  public PleaseWait(int waitMilis) {
    this();
    this.waitMilis = waitMilis;
  }

  public PleaseWait(Image image, String message) {
    this(image, message, 0);
  }

  public PleaseWait(ImageIcon icon, String message) {
    this((icon == null ? null : icon.getImage()), message);
  }

  public PleaseWait(Image image, String message, int waitMilis) {
    this(waitMilis);
    this.image = image;
    this.message = message;
  }

  public PleaseWait(ImageIcon icon, String message, int waitMilis) {
    this((icon == null ? null : icon.getImage()), message, waitMilis);
  }

  public static PleaseWait createSqlWait() {
    return new PleaseWait(ImageManager.getImage("/res/wait-sql.gif"), null, 500);
  }

  public static PleaseWait createSqlWait(String message, int waitMilis) {
    return new PleaseWait(ImageManager.getImage("/res/wait-sql.gif"), message, waitMilis);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getProgress() {
    return progress;
  }

  /**
   * <p>Pozwala okre�li� post�p procesu
   * @param progress - z zakresu od 1 do 100, domy�lnie 100
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }

  public boolean isShowTime() {
    return System.currentTimeMillis() > waitTime +waitMilis;
  }

}
