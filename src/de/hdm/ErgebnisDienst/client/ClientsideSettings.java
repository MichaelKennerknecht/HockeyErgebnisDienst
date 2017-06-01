package de.hdm.ErgebnisDienst.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
import de.hdm.ErgebnisDienst.shared.CommonSettings;
import de.hdm.ErgebnisDienst.shared.LoginInfo;

/**
 * Klasse, die alle Eigenschaften und Methoden/Dienste zur Verfuegung stellt,
 * die fuer alle Client-seitigen Klassen relevant sind.
 * 
 * @author Lorena Esposito
 */
public class ClientsideSettings extends CommonSettings {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
	 * Dienst namens <code>AdministrationService</code>.
	 */
	private static ErgebnisDienstAdministrationAsync administration = null;

	//private static ReportGeneratorAsync report = null;
	/**
	 * Name des Client-seitigen Loggers.
	 */
	private static final String LOGGER_NAME = "Tngly Web Client";

	/**
	 * Instanz des Client-seitigen Loggers.
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 * Instanz der applikationsweit eindeutigen Anmeldeinformationen des
	 * aktuellen Benutzers.
	 */
	private static LoginInfo loginInfo = null;

	/**
	 * Gibt die applikationsweit eindeutigen Anmeldeinformationen des aktuellen
	 * Benutzers zurueck.
	 * 
	 * @return eindeutige Instanz des Typs {@link LoginInfo}
	 */
	public static LoginInfo getLoginInfo() {
		return loginInfo;
	}

	/**
	 * Legt die applikationsweit eindeutigen Anmeldeinformationen des aktuellen
	 * Benutzers fest.
	 * 
	 * @param loginInfo
	 *            Anmeldeinformationen aus Login ueber Google Accounts API
	 */
	public static void setLoginInfo(LoginInfo loginInfo) {
		ClientsideSettings.loginInfo = loginInfo;
	}

	/**
	 * Gibt den applikationsweiten, (Client-seitig!) zentralen Logger zurueck.
	 * Weitere Infos: siehe Dokumentation zu Java Logging.
	 * 
	 * @return die Logger-Instanz fuer die Server-Seite
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * Gibt die applikationsweit eindeutige AdministrationService zurueck. Diese
	 * Methode erstellt die AdministrationService, sofern sie noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * zuvor angelegte Objekt zurueckgegeben.
	 * 
	 * @return eindeutige Instanz des Typs
	 *         <code>AdministrationServiceAsync</code>
	 * @author Peter Thies
	 */
	
	
	/**
	 * hier wird das Proxy Objekt erstellt, Verbindung zum Client
	 * @return
	 */
	
	public static ErgebnisDienstAdministrationAsync getAdministration() {
		if (administration == null) {
			administration = GWT.create(ErgebnisDienstAdministration.class);

			final AsyncCallback<Void> initAdministrationCallback = new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe(
							"Der Administration konnte nicht initialisiert werden!"
									+ caught);
				}

				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info(
							"Der Administration wurde initialisiert.");
				}
			};
			administration.init(initAdministrationCallback);
		}
		return administration;
	}
	
	/*public static ReportGeneratorAsync getReportGenerator() {
		if (report == null) {
			report = GWT.create(ReportGenerator.class);

			final AsyncCallback<Void> initAdministrationCallback = new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe(
							"Der Administration konnte nicht initialisiert werden!"
									+ caught);
				}

				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info(
							"Der Administration wurde initialisiert.");
				}
			};
			report.init(initAdministrationCallback);
		}
		return report;
	}
	*/
}

