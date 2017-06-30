package de.hdm.ErgebnisDienst.client;

//import de.hdm.ErgebnisDienst.client.gui.CreateGameEntry;
import de.hdm.ErgebnisDienst.client.gui.Home;
import de.hdm.ErgebnisDienst.client.gui.Impressum;
import de.hdm.ErgebnisDienst.client.gui.MatchDay;
import de.hdm.ErgebnisDienst.client.gui.Update;
//import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
//import de.hdm.ErgebnisDienst.shared.LoginInfo;
//import de.hdm.ErgebnisDienst.shared.bo.Matchday;
//import de.hdm.ErgebnisDienst.shared.bo.Team;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Arrays;

//import com.google.gwt.user.cellview.client.CellTable;
//import com.google.gwt.user.cellview.client.TextColumn;
//import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
//import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
//import com.google.gwt.view.client.SelectionChangeEvent;
//import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

//import com.google.gwt.user.client.ui.Image;

public class HockeyErgebnisDienst implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	//private static final String SERVER_ERROR = "An error occurred while "
	//		+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	
	/**
	  *Proxy-Objekt
	  */
	public static ErgebnisDienstAdministrationAsync adminService = ClientsideSettings.getAdministration();

	
	 /**
	  * Erstellung der Panels
	  */
	//private VerticalPanel navigator = new VerticalPanel();
	//private VerticalPanel details = new VerticalPanel();
	private HorizontalPanel topPanel = new HorizontalPanel();
	//private VerticalPanel loginPanel = new VerticalPanel();
	//private VerticalPanel loginTextPanel = new VerticalPanel();
	
	//private Label loginLabel = new Label("Bitte loggen Sie sich mit Ihrem Google-Account ein:");
	public static Label userLabel = new Label();
	final Label usernameLabel = new Label("Username");
	final Label passwordLabel = new Label("Password");
	//private Anchor signInLink = new Anchor("Login");
	final Button impressumButton = new Button("Impressum");
	final Button startseite = new Button ("Startseite");
	
	//private LoginInfo loginInfo = null;
	//private ArrayList<Team> teams = null;



	de.hdm.ErgebnisDienst.client.gui.LoginServiceAsync loginService = GWT
			.create(de.hdm.ErgebnisDienst.client.gui.LoginService.class);

	/**
	 * Die Instanz von LoginInfo dient als Hilfsklasse fuer das Login und stellt
	 * erforderliche Variablen und Operationen bereit.
	 */

	public void onModuleLoad() {
		Window.alert("Willkommen zum Hockey Ergebnisdienst, viel Spaß!");	
		
		// Add the widgets to the root panel
		RootPanel.get("Top").add(startseite);
		RootPanel.get("Top").add(topPanel);
		RootPanel.get("Details").add(new Home());
		//RootPanel.get("Details").add(flexTable);
		RootPanel.get("Extra").add(impressumButton);
		
		
		//MatchDay Tabelle zu Beginn laden
		
		MatchDay matchday = new MatchDay();
		RootPanel.get("Navigator").add(matchday);
		
		//Dem Impressum-Button einen ClickHandler hinzufügen  

	    impressumButton.addClickHandler(new ClickHandler() {
		  	public void onClick(ClickEvent event) {
		          /*
		           * Showcase instantiieren.
		           */
		          Update update = new Impressum();
		          RootPanel.get("Extra").clear();
		          RootPanel.get("Extra").add(impressumButton);
		          RootPanel.get("Extra").add(update);
		    }
		    });	 

	  //Dem Startseite-Button einen ClickHandler hinzufügen  
	    
	    startseite.addClickHandler(new ClickHandler() {
		  	public void onClick(ClickEvent event) {
		          /*
		           * Showcase instantiieren.
		           */
		          Update update = new Home();
		          RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(update);
		    }
		    });	 
	
	}


//	private void loadTeams() {
//		adminService.getAllTeams(new AsyncCallback<ArrayList<Team>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("FEHLER ahahahahah: " + caught);
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Team> result) {
//				
//				Window.alert("klappt: " );
//
//			}
//		});
//	}

	/**
	  * Login nicht umgesetzt, aber vorbereitet
	  */
//		private void loadLogin() {
//
//		Cookies.setCookie("usermail", null);
//		RootPanel.get("Top").add(topPanel);
//		loginTextPanel.add(loginLabel);
//		loginTextPanel.add(signInLink);
//		loginTextPanel.setStyleName("loginTextPanel");
//
//		// loginTextPanel.add(usernameLabel);
//		// loginTextPanel.add(usernameBox);
//		// loginTextPanel.add(passwordLabel);
//		// loginTextPanel.add(passwordBox);
//		loginPanel.add(loginTextPanel);
//
//		Cookies.setCookie("userMail", null);
//		Cookies.setCookie("userID", null);
//		RootPanel.get("Details").clear();
//		RootPanel.get("Details").add(loginPanel);
//	};
}