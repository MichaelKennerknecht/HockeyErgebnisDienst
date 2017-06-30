package de.hdm.ErgebnisDienst.client;

import de.hdm.ErgebnisDienst.client.gui.Home;
import de.hdm.ErgebnisDienst.client.gui.Impressum;
import de.hdm.ErgebnisDienst.client.gui.MatchDay;
import de.hdm.ErgebnisDienst.client.gui.Update;

import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
import com.google.gwt.user.client.Window;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HockeyErgebnisDienst implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	/**
	 * This is the entry point method.
	 */

	/**
	 * Proxy-Objekt
	 */
	public static ErgebnisDienstAdministrationAsync adminService = ClientsideSettings.getAdministration();

	/**
	 * Erstellung der Panels
	 */

	private HorizontalPanel topPanel = new HorizontalPanel();
	public static Label userLabel = new Label();
	final Label usernameLabel = new Label("Username");
	final Label passwordLabel = new Label("Password");
	final Button impressumButton = new Button("Impressum");
	final Button startseite = new Button("Startseite");

	de.hdm.ErgebnisDienst.client.gui.LoginServiceAsync loginService = GWT
			.create(de.hdm.ErgebnisDienst.client.gui.LoginService.class);

	/**
	 * Die Instanz von LoginInfo dient als Hilfsklasse fuer das Login und stellt
	 * erforderliche Variablen und Operationen bereit.
	 */

	public void onModuleLoad() {
		Window.alert("Willkommen zum Hockey Ergebnisdienst, viel Spaß!");

		// Add the widgets to the root panel
		startseite.setStyleName("startseiteButton");
		RootPanel.get("Navigator").add(startseite);
		RootPanel.get("Top").add(topPanel);
		RootPanel.get("Details").add(new Home());
		RootPanel.get("Extra").add(impressumButton);
		
		
		/**
		 * MatchDay Tabelle zu Beginn laden
		 */
				MatchDay matchday = new MatchDay();
		RootPanel.get("Navigator").add(matchday);
		matchday.setStyleName("matchdayTable");

		/**
		 * Dem Impressum-Button einen ClickHandler hinzufügen
		 */

		impressumButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * Update instantiieren.
				 */
				Update update = new Impressum();
				RootPanel.get("Extra").clear();
				RootPanel.get("Extra").add(impressumButton);
				RootPanel.get("Extra").add(update);
			}
		});

		/**
		 * Dem Startseite-Button einen ClickHandler hinzufügen
		 */

		startseite.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * Update instantiieren.
				 */
				Update update = new Home();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(update);
			}
		});

	}

	/**
	 * Login nicht umgesetzt, aber vorbereitet
	 */
	
}