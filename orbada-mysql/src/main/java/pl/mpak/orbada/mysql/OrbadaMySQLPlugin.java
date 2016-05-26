package pl.mpak.orbada.mysql;

import java.util.ArrayList;
import java.util.List;
import pl.mpak.orbada.Consts;
import pl.mpak.orbada.core.Application;
import pl.mpak.orbada.db.InternalDatabase;
import pl.mpak.orbada.db.Template;
import pl.mpak.orbada.mysql.cm.FindObjectFromEditorAction;
import pl.mpak.orbada.mysql.services.ExplainPlanProvider;
import pl.mpak.orbada.mysql.services.MySQLAutotraceProvider;
import pl.mpak.orbada.mysql.services.MySQLDatabaseProvider;
import pl.mpak.orbada.mysql.services.MySQLDbInfoProvider;
import pl.mpak.orbada.mysql.services.MySQLFocusProvider;
import pl.mpak.orbada.mysql.services.MySQLFunctionsView;
import pl.mpak.orbada.mysql.services.MySQLHelpView;
import pl.mpak.orbada.mysql.services.MySQLPerspectiveProvider;
import pl.mpak.orbada.mysql.services.MySQLProceduresView;
import pl.mpak.orbada.mysql.services.MySQLSearchObjectView;
import pl.mpak.orbada.mysql.services.MySQLSessionsView;
import pl.mpak.orbada.mysql.services.MySQLTablesView;
import pl.mpak.orbada.mysql.services.MySQLTemplatesSettingsProvider;
import pl.mpak.orbada.mysql.services.MySQLTriggersView;
import pl.mpak.orbada.mysql.services.MySQLViewsView;
import pl.mpak.orbada.mysql.services.UniversalColumnProvider;
import pl.mpak.orbada.mysql.services.actions.FunctionActionsService;
import pl.mpak.orbada.mysql.services.actions.ObjectSearchActionsService;
import pl.mpak.orbada.mysql.services.actions.ProcedureActionsService;
import pl.mpak.orbada.mysql.services.actions.SessionViewActionsService;
import pl.mpak.orbada.mysql.services.actions.TableActionsService;
import pl.mpak.orbada.mysql.services.actions.TableColumnActionsService;
import pl.mpak.orbada.mysql.services.actions.TableConstraintActionsService;
import pl.mpak.orbada.mysql.services.actions.TableIndexActionsService;
import pl.mpak.orbada.mysql.services.actions.TableTriggerActionsService;
import pl.mpak.orbada.mysql.services.actions.TableUtilsActionsService;
import pl.mpak.orbada.mysql.services.actions.TriggerActionsService;
import pl.mpak.orbada.mysql.services.actions.ViewActionsService;
import pl.mpak.orbada.mysql.services.actions.ViewColumnActionsService;
import pl.mpak.orbada.mysql.services.actions.ViewUtilsActionsService;
import pl.mpak.orbada.plugins.ISettings;
import pl.mpak.orbada.plugins.OrbadaPlugin;
import pl.mpak.orbada.plugins.queue.PluginMessage;
import pl.mpak.plugins.spi.IPlugin;
import pl.mpak.plugins.spi.IPluginProvider;
import pl.mpak.usedb.core.Query;
import pl.mpak.util.ExceptionUtil;
import pl.mpak.util.StringManager;
import pl.mpak.util.StringManagerFactory;
import pl.mpak.util.id.VersionID;

/**
 *
 * @author akaluza
 */
public class OrbadaMySQLPlugin extends OrbadaPlugin {
  
  private final static StringManager stringManager = StringManagerFactory.getStringManager(OrbadaMySQLPlugin.class);

  public final static String driverType = "MySQL";
  public final static String adminGroup = stringManager.getString("OrbadaMySQLPlugin-admin-group");
  public final static String specjalMySQLActions = "mysql-actions";

  private static pl.mpak.util.timer.TimerQueue refreshQueue;
  public final static ArrayList<Class<? extends IPluginProvider>> classList = new ArrayList<Class<? extends IPluginProvider>>();

  public static FindObjectFromEditorAction findObjectAction;

  public static pl.mpak.util.timer.TimerQueue getRefreshQueue() {
    if (refreshQueue == null) {
      refreshQueue = pl.mpak.util.timer.TimerManager.getTimer("orbada-mysql-refresh");
    }
    return refreshQueue;
  }

  /**
   * Funkcja powinna zwraca� nazw� wewn�trzn� wtyczki
   * @return
   */
  public String getInternalName() {
    return "OrbadaMySQLPlugin";
  }
  
  /**
   * Funkcja powinna zwraca� nazw� opisow� wtyczki
   * @return
   */
  public String getDescriptiveName() {
    return String.format(stringManager.getString("OrbadaMySQLPlugin-descriptive-name"), new Object[] {getVersion()});
  }
  
  /**
   * <p>Funkcja powinna zwraca� rozszerzone informacje opisowe dotycz�ce wtyczki.
   * @return
   */
  public String getDescription() {
    return "";
  }
  
  /**
   * <p>Kategorie wtyczki, np:
   * <li>Database, HSQLDB</li>
   * <li>Developers</li>
   * @return
   */
  public String getCategory() {
    return "IDE,Database,MySQL";
  }
  
  /**
   * Funkcja powinna zwraca� informacje o autorach wtyczki
   * @return
   */
  public String getAuthor() {
    return "Andrzej Ka�u�a";
  }
  
  public String getCopyrights() {
    return "";
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony www
   * @return
   */
  public String getWebSite() {
    return null;
  }
  
  /**
   * Funkcja powinna zwraca� adres swtrony aktualizacji
   * @return
   */
  public String getUpdateSite() {
    return null;
  }
  
  /**
   * Funckja powinna zwr�ci� wersj� najlepiej w postaci:
   * major.minor.release.build
   * @return
   */
  public String getVersion() {
    return new VersionID(1, 1, 0, 37).toString();
  }
  
  /**
   * Mo�e zwr�ci� tre�� licencji
   * @return
   */
  public String getLicence() {
    return null;
  }
  
  /**
   * <p>Funkcja musi zwraca� unikalny identyfikator wtyczki
   * <p>W tym miejscu mo�na sko�ysta� z kasy pl.mpak.sky.utils.UniqueID
   * Identyfikator identyfikuje jednoznacznie za�adowan� wtyczk�.
   * <p>Mo�e te� by� to unikalna nazwa wtyczki.
   * @return
   */
  public String getUniqueID() {
    return Consts.orbadaMySQLPluginId;
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz po za�adowaniu wtyczki.
   * ManOra jest ju� utworzona, konfiguracja programu za�adowana
   */
  public void load() {
  }
  
  /**
   * Funkcja wywo�ywana jest zaraz przed zamkni�ciem programu
   */
  public void unload() {
  }
  
  /**
   * Funkcja wywo�ywana jest po za�adowaniu wszystkich wtyczek i pokazaniu okna g��wnego.
   * W tym miejscu mo�e by� sprawdzone czy s� wszystkie wtyczki potrzebne
   * do prawid�owego dzia�ania tej wtyczki.
   * R�wnie� w tym miejscu mo�na podpi�� listenery gdzie tylko si� chce.
   * Mo�e podpi�� si� w odpowiednie miejsca menu, toolbar-a, list� po��cze�
   * skonfigurowanych i nawi�zanych. Mo�e uruchomi� jakie� zadania (Task), wpisa�
   * co� do log-a (pl.mpak.sky.utils.logging.Logger), etc
   */
  public void initialize() {
    application.registerDriverType(driverType);
    findObjectAction = new FindObjectFromEditorAction();
    
    classList.add(MySQLDatabaseProvider.class);
    classList.add(MySQLDbInfoProvider.class);
    classList.add(MySQLHelpView.class);
    classList.add(MySQLTablesView.class);
    classList.add(MySQLViewsView.class);
    classList.add(MySQLProceduresView.class);
    classList.add(MySQLFunctionsView.class);
    classList.add(TableActionsService.class);
    classList.add(TableColumnActionsService.class);
    classList.add(TableIndexActionsService.class);
    classList.add(TableConstraintActionsService.class);
    classList.add(TableTriggerActionsService.class);
    classList.add(ViewActionsService.class);
    classList.add(ViewColumnActionsService.class);
    classList.add(ProcedureActionsService.class);
    classList.add(FunctionActionsService.class);
    classList.add(UniversalColumnProvider.class);
    classList.add(MySQLPerspectiveProvider.class);
    classList.add(MySQLTriggersView.class);
    classList.add(TriggerActionsService.class);
    classList.add(MySQLTemplatesSettingsProvider.class);
    classList.add(MySQLAutotraceProvider.class);
    classList.add(MySQLSessionsView.class);
    classList.add(MySQLSearchObjectView.class);
    classList.add(MySQLFocusProvider.class);
    classList.add(SessionViewActionsService.class);
    classList.add(TableUtilsActionsService.class);
    classList.add(ViewUtilsActionsService.class);
    classList.add(ObjectSearchActionsService.class);
    initTemplates();
  }
  
  /**
   * <p>Funkcja powinna sprawdzi� list� potrzebnych innych wtyczek
   * return informacje czy mo�na warunki s� spe�nione i czy mo�na u�ywa� tej wtyczki
   * <p>Wywo�ane przed initialize()
   * @param loadedPlugins
   * @return
   */
  public boolean requires(List<IPlugin> loadedPlugins) {
    addDepend(Consts.orbadaUniversalPluginId);
    for (IPlugin plugin : loadedPlugins) {
      if (Consts.orbadaUniversalPluginId.equals(plugin.getUniqueID())) {
        classList.add(ExplainPlanProvider.class);
      }
    }
    return true;
  }
  
  /**
   * <p>Wywo�ywane jest po initialize()
   */
  public Class<IPluginProvider>[] getProviderArray() {
    return classList.toArray(new Class[classList.size()]);
  }

  @Override
  public void processMessage(PluginMessage message) {
  }

  private void initTemplates() {
    if (InternalDatabase.get() == null) {
      return;
    }
    Query query = InternalDatabase.get().createQuery();
    try {
      query.open("select count( 0 ) cnt from templates where tpl_name like 'mysql-%'");
      if (query.fieldByName("cnt").getInteger() == 0) {
        Template template = new Template(application.getOrbadaDatabase());
        template.setName("mysql-function");
        template.setDescription("MySQL Function Template");
        template.setUsrId("");
        template.setBody(
          "CREATE FUNCTION $(&name)($(&parameters)) RETURNS $(&return)\n" +
          "  $(pr&operties)\n" +
          "BEGIN\n" +
          "  -- ############################################################################\n" +
          "  -- # ORBADA   : To change this template, choose Tools | Templates\n" +
          "  -- # Author   : $(orbada.user.name)\n" +
          "  -- # Created  : $(orbada.current.date) $(orbada.current.time)\n" +
          "  -- ############################################################################\n" +
          "  --\n" +
          "  /* TODO function implementation */\n" +
          "$(&body)\n" +
          "END");
        template.applyInsert();

        template = new Template(application.getOrbadaDatabase());
        template.setName("mysql-procedure");
        template.setDescription("MySQL Procedure Template");
        template.setUsrId("");
        template.setBody(
          "CREATE PROCEDURE $(&name)($(&parameters))\n" +
          "  $(pr&operties)\n" +
          "BEGIN\n" +
          "  -- ############################################################################\n" +
          "  -- # ORBADA   : To change this template, choose Tools | Templates\n" +
          "  -- # Author   : $(orbada.user.name)\n" +
          "  -- # Created  : $(orbada.current.date) $(orbada.current.time)\n" +
          "  -- ############################################################################\n" +
          "  --\n" +
          "  /* TODO procedure implementation */\n" +
          "$(&body)\n" +
          "END");
        template.applyInsert();

        template = new Template(application.getOrbadaDatabase());
        template.setName("mysql-trigger");
        template.setDescription("MySQL Trigger Template");
        template.setUsrId("");
        template.setBody(
          "CREATE TRIGGER $(&name)\n" +
          "$(&type)\n" +
          "BEGIN\n" +
          "  -- ############################################################################\n" +
          "  -- # ORBADA   : To change this template, choose Tools | Templates\n" +
          "  -- # Author   : $(orbada.user.name)\n" +
          "  -- # Created  : $(orbada.current.date) $(orbada.current.time)\n" +
          "  -- ############################################################################\n" +
          "  --\n" +
          "  /* TODO trigger implementation */\n" +
          "$(&body)\n" +
          "END");
        template.applyInsert();
      }
      
      ISettings settings = Application.get().getSettings(MySQLTemplatesSettingsProvider.settingsName);
      if (settings.getValue(MySQLTemplatesSettingsProvider.setFunction).isNull()) {
        settings.setValue(MySQLTemplatesSettingsProvider.setFunction, "mysql-function");
      }
      if (settings.getValue(MySQLTemplatesSettingsProvider.setProcedure).isNull()) {
        settings.setValue(MySQLTemplatesSettingsProvider.setProcedure, "mysql-procedure");
      }
      if (settings.getValue(MySQLTemplatesSettingsProvider.setTrigger).isNull()) {
        settings.setValue(MySQLTemplatesSettingsProvider.setTrigger, "mysql-trigger");
      }
      settings.store();
    }
    catch (Exception ex) {
      ExceptionUtil.processException(ex);
    }
    finally {
      query.close();
    }
  }
  
}
