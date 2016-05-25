package pl.mpak.plugins;

import java.util.EventListener;

import pl.mpak.plugins.spi.IPlugin;

public interface PluginManagerListener extends EventListener {
  
  public enum ManageProcess {
    FOUND,
    LOAD,
    UNLOAD,
    INITIALIZE
  }

  /**
   * <p>Pocz�tek bloku operuj�cego na wszystkich plugin-ach
   * <p>FOUND - count = -1<br>
   * LOAD, UNLOAD, INITIALIZE - count = ilo�� plugin�w
   * @param type
   * @param count
   */
  public void beginProcess(ManageProcess type, int count);
  
  /**
   * <p>Wykonanie operacji na jednym pluginie
   * <p>FOUND - plugin = null<br>
   * LOAD, UNLOAD, INITIALIZE - plugin = plugin na kt�rym wykonana zosta�a operacja
   * @param type
   * @param plugin
   */
  public void process(ManageProcess type, IPlugin plugin);
  
  /**
   * <p>Koniec bloku operuj�cego na wszystkich plugin-ach
   * @param type
   * @param count
   */
  public void endProcess(ManageProcess type);
  
}
