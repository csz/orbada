/*
 * IApplication.java
 * 
 * Created on 2007-10-07, 14:55:22
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins;

import java.math.BigInteger;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.sky.gui.swing.Action;
import pl.mpak.usedb.core.Database;
import pl.mpak.util.id.VersionID;

/**
 * <p>Ten interfejs otrzymuje ka�da z zarejestrowanych wtyczek
 * @author akaluza
 */
public interface IApplication {
  
  public String[] getArguments();
  
  public String getConfigPath();
  public VersionID getLastVersion();
  
  public void registerDriverType(String driverType);
  
  public void addApplicationListener(ApplicationListener listener);
  public void removeApplicationListener(ApplicationListener listener);
  
  public void addAction(Action action);
  public void removeAction(Action action);
  
  /**
   * <p>Tworzy ustawienia dla wybranej nazwy/grupy
   * @param groupName 
   * @return 
   */
  public ISettings getSettings(String groupName);
  /**
   * <p>Tworzy ustawienia dla wybranej nazwy/grupy oraz identyfikatora schematu
   * @param schemaId 
   * @param groupName 
   * @return 
   */
  public ISettings getSettings(String schemaId, String groupName);

  /**
   * <p>Pozwala pobra� warto�� z pliku konfiguracyjnego Orbada
   * @param name
   * @return
   */
  public String getProperty(String name);

  /**
   * <p>Pozwala pobra� warto�� z pliku konfiguracyjnego Orbada
   * @param name
   * @param defaultValue
   * @return
   */
  public String getProperty(String name, String defaultValue);
  
  public <T extends IPluginProvider> T[] getServiceArray(Class<T> t);
  
  public void postPluginMessage(PluginMessage message);
  public void registerRequestMessager(IProcessMessagable processMessagable);
  public void unregisterRequestMessager(IProcessMessagable processMessagable);
  
  public void execTool(String command, Object[] args);
  
  /**
   * <p>Unikalny identyfikator u�ytkownika
   * @return
   */
  public String getUserId();
  
  /**
   * <p>Nazwa u�ytkownika ORBADA
   * @return
   */
  public String getUserName();
  
  /**
   * <p>Czy u�ytkownik jest administratorem
   * @return
   */
  public boolean isUserAdmin();

  /**
   * <p>Zwraca informacj� czy orbada uruchomiona jest w trybie multi u�ytkownika, wielu u�ytkownik�w.
   * @return
   */
  public boolean isMultiUserApp();

  /**
   * <p>Informacja o tym, �e aplikacja zosta�a uruchomiona pierwszy raz
   * @return
   */
  public boolean isFirstRun();
  
  /**
   * <p>Inicjuje i je�li to potrzebne tworzy generator warto�ci
   * @param name
   * @param startValue je�li null wtedy przyjmie warto�� domy�ln� (1)
   * @param minValue je�li null wtedy przyjmie warto�� domy�ln� (1)
   * @param maxValue je�li null wtedy przyjmie warto�� domy�ln� (99999999999999999999)
   * @param increment je�li null wtedy przyjmie warto�� domy�ln� (1)
   * @param cycle je�li null wtedy przyjmie warto�� domy�ln� (false)
   * @return
   */
  public IGenerator initGenerator(String name, BigInteger startValue, BigInteger minValue, BigInteger maxValue, BigInteger increment, Boolean cycle) throws GeneratorException;
  
  public IGenerator getGenerator(String name);
  
  public Database getOrbadaDatabase();
  
  /**
   * <p>Pozwala pobra� specjalny ci�g znak�w z wewn�trznej struktury programu<br>
   * np. "version", "application-name", "copyright", "last-version"<br>
   * Og�lnie wszystko to co znajduje si� w tabeli <code>ORBADA</code>
   * @param name
   * @return
   */
  public String getOrbadaString(String name);
  
  /**
   * <p>Zwraca unikalne id sesji programu
   * @return
   */
  public String getOrbadaSessionId();
  
  public IWebAppAccessibilities getWebAppAccessibilities();

  public PleaseWait startPleaseWait(PleaseWait wait);
  public void stopPleaseWait(PleaseWait wait);

  public void updateLAF();

  public IPerspectiveAccesibilities getActivePerspective();
  
}
