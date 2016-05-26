/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.mpak.orbada.plugins.providers;

import pl.mpak.orbada.plugins.IApplication;
import pl.mpak.orbada.plugins.providers.abs.IOrbadaPluginProvider;
import pl.mpak.sky.gui.swing.AutoCompleteListener;
import pl.mpak.sky.gui.swing.syntax.SyntaxTextArea;

/**
 * <p>Provider s�u�y do obs�ugi mechanizmu auto uzupe�niania w edytorze SQL/Java<br>
 * Dla edytor�w OrbadaSyntaxTextArea i OrbadaJavaSyntaxTextArea dopiero w funkcji populate mo�na
 * sprawdzi� czy obs�ugiwana jest konkretna baza danych.
 * @author akaluza
 */
public abstract class SyntaxEditorAutoCompleteProvider implements IOrbadaPluginProvider, AutoCompleteListener {

  protected IApplication application;

  public void setApplication(IApplication application) {
    this.application = application;
  }

  public boolean isSharedProvider() {
    return false;
  }

  /**
   * <p>Funkcja powinna zwr�ci� true je�li serwis jest przeznaczony dla tego edytora.
   * @param syntaxTextArea
   * @return
   */
  public abstract boolean isForEditor(SyntaxTextArea syntaxTextArea);

}
