package pl.mpak.game;

import java.awt.image.BufferedImage;

public interface Collisionable {
  
  /**
   * <p>Wsp��dne po�o�enia X
   * @return
   */
  public int getCollisionX();
  
  /**
   * <p>Wsp��dne po�o�enia Y
   * @return
   */
  public int getCollisionY();
  
  /**
   * <p>Obrazek do sprawdzenia kolizji
   * @return
   */
  public BufferedImage getCollisionImage();
  
  /**
   * <p>Wyzwalany w momencie wyst�pienia kolizji
   * @param object
   */
  public void collidedWith(Collisionable object);
  
}
