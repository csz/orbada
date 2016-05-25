package pl.mpak.sky.gui.swing;

import javax.swing.Icon;

public class AutoCompleteItem implements Comparable<AutoCompleteItem> {

  private String word;
  private String displayText;
  private String description;
  private String type;
  private Icon icon;
  private String returnDataType;
  
  public AutoCompleteItem() {
  }
  
  public AutoCompleteItem(String word) {
    this(word, null, null, null);
  }
  
  public AutoCompleteItem(String word, String displayText) {
    this(word, null, displayText, null);
  }
  
  public AutoCompleteItem(String word, String type, String displayText) {
    this(word, type, displayText, null);
  }
  
  public AutoCompleteItem(String word, String displayText, Icon icon) {
    this(word, null, displayText, icon);
  }

  public AutoCompleteItem(String word, String type, String displayText, Icon icon) {
    super();
    this.displayText = displayText;
    this.icon = icon;
    this.word = word;
    this.type = type;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDisplayText() {
    return displayText;
  }

  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }

  public Icon getIcon() {
    return icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getReturnDataType() {
    return returnDataType;
  }

  public void setReturnDataType(String returnDataType) {
    this.returnDataType = returnDataType;
  }

  /**
   * <p>Ta funkcja mo�e by� przeci��ona w celu utworzenia ci�gu znak�w zast�puj�cy obecny w edytorze
   * @param replacingText zawiera zast�powany ci�g znak�w
   * @param nextChar zawiera nast�pny znak nie b�d�cy bia�ym znakiem
   * @return
   */
  public String selectedText(String replacingText, String nextChar) {
    return word;
  }
  
  public String toString() {
    return word;
  }

  public int compareTo(AutoCompleteItem o) {
    return word.compareTo(o.word);
  }
  
}
