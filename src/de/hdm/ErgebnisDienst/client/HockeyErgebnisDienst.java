package de.hdm.ErgebnisDienst.client;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.ErgebnisDienst.shared.LoginInfo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Image;

public class HockeyErgebnisDienst implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	// Proxy Objekt 
	private ErgebnisDienstAdministration adminService = ClientsideSettings.
	
	
	private VerticalPanel navigator = new VerticalPanel();
	private VerticalPanel details = new VerticalPanel();
	private VerticalPanel matchdays = new VerticalPanel();
	private HorizontalPanel topPanel = new HorizontalPanel();
	private TextBox text = new TextBox();

	private VerticalPanel loginPanel = new VerticalPanel();
	private VerticalPanel loginTextPanel = new VerticalPanel();
	private Label loginLabel = new Label("Bitte loggen Sie sich mit Ihrem Google-Account ein:");
	public static Label userLabel = new Label();
	final Label usernameLabel = new Label("Username");
	final Label passwordLabel = new Label("Password");
	private Anchor signInLink = new Anchor("Login");
	final Button ergebnis = new Button("Ergebnisse eintragen");

	de.hdm.ErgebnisDienst.client.gui.LoginServiceAsync loginService = GWT
			.create(de.hdm.ErgebnisDienst.client.gui.LoginService.class);

	/**
	 * Die Instanz von LoginInfo dient als Hilfsklasse fuer das Login und stellt
	 * erforderliche Variablen und Operationen bereit.
	 */
	private LoginInfo loginInfo = null;

	/**
	 * Entry point method.
	 */

	/*
	 * protected void matchdayPanel() {
	 * 
	 * // Erstellen von SpieltagsÃ¼bersicht
	 * 
	 * final Grid matchday = new Grid(1, 0); matchday.setText(0, 0,
	 * "Spieltag 1");
	 * 
	 * 
	 * // Inhalt einfÃ¼gen int numRows = matchday.getRowCount(); int numColumns
	 * = matchday.getColumnCount(); for (int row = 0; row < numRows; row++) {
	 * for (int col = 0; col < numColumns; col++) { matchday.setText(row, col,
	 * "Spieltag 1");
	 * 
	 * }
	 * 
	 * } matchdays.add(matchday); navigator.add(matchdays); //
	 * matchday.setWidget(row, col, new Image(Showcase.images.gwtLogo()));
	 * 
	 * }
	 */

	public void onModuleLoad() {
		Window.alert("Willkommen zum Hockey Ergebnisdienst, viel Spaß beim austesten!");
		loadLogin();

		topPanel.add(ergebnis);
		
		
	}

	private void loadLogin() {

		// Label headertext = new Label();
		// headertext.setText("Hier ist der HEADER");
		// topPanel.add(headertext);

		

		Cookies.setCookie("usermail", null);
		RootPanel.get("Top").add(topPanel);
		loginTextPanel.add(loginLabel);
		loginTextPanel.add(signInLink);
		loginTextPanel.setStyleName("loginTextPanel");

		// loginTextPanel.add(usernameLabel);
		// loginTextPanel.add(usernameBox);
		// loginTextPanel.add(passwordLabel);
		// loginTextPanel.add(passwordBox);
		loginPanel.add(loginTextPanel);

		Cookies.setCookie("userMail", null);
		Cookies.setCookie("userID", null);
		RootPanel.get("Details").clear();
		RootPanel.get("Details").add(loginPanel);

	}
	protected void loadAllTeams() {
		ergebnis.addClickHandler(new ClickHandler() {
			private void onClickEvent(ClickEvent event) {
				
				System.out.println();

			}
		});
		}
	// add style names to widgets
	ergebnis.setStylePrimaryName("ergebnisButton");
	}
//	Image startImage = new Image("/images/HdM_Logo.jpg");
	//startImage.setStylePrimaryName("image");
	//navigator.add(startImage);
}