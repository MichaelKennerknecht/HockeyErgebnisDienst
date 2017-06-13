package de.hdm.ErgebnisDienst.client;

import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
import de.hdm.ErgebnisDienst.shared.LoginInfo;
import de.hdm.ErgebnisDienst.shared.bo.Matchday;
import de.hdm.ErgebnisDienst.shared.bo.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

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
	private ErgebnisDienstAdministrationAsync adminService = ClientsideSettings.getAdministration();

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
	private LoginInfo loginInfo = null;

	de.hdm.ErgebnisDienst.client.gui.LoginServiceAsync loginService = GWT
			.create(de.hdm.ErgebnisDienst.client.gui.LoginService.class);

	/**
	 * Die Instanz von LoginInfo dient als Hilfsklasse fuer das Login und stellt
	 * erforderliche Variablen und Operationen bereit.
	 */

	/**
	 * A simple data type that represents a matchday.
	 */
//	private static class Matchday {
//		private final String name;
//
//		public Matchday(String name) {
//			this.name = name;
//		}
//	}

	/**
	   * The list of data to display.
	   */
//	   private static final List<Matchday> MATCHDAYS = Arrays.asList(
//			      new Matchday("Spieltag 01"), 
//			      new Matchday("Spieltag 02"),
//			      new Matchday("Spieltag 03"),
//			      new Matchday("Spieltag 04"),
//			      new Matchday("Spieltag 05"),
//			      new Matchday("Spieltag 06"),
//			      new Matchday("Spieltag 07"),
//			      new Matchday("Spieltag 08"),
//			      new Matchday("Spieltag 09"),
//			      new Matchday("Spieltag 10"),
//			      new Matchday("Spieltag 11"),
//			      new Matchday("Spieltag 12"),
//			      new Matchday("Spieltag 13"),
//			      new Matchday("Spieltag 14"),
//			      new Matchday("Spieltag 15"),
//			      new Matchday("Spieltag 16"),
//			      new Matchday("Spieltag 17"),
//			      new Matchday("Spieltag 18"));
	   
	   private static final List<Matchday> MATCHDAYS = Arrays.asList


	public void onModuleLoad() {
		Window.alert("Willkommen zum Hockey Ergebnisdienst, viel Spaß!");

		topPanel.add(ergebnis);
		RootPanel.get("Top").add(topPanel);

		// Create a CellTable.
		CellTable<Matchday> table = new CellTable<Matchday>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<Matchday> nameColumn = new TextColumn<Matchday>() {
			@Override
			public String getValue(Matchday object) {
				 adminService.getAllTeams(new AsyncCallback<ArrayList<Team>>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Hier funktioniert nichts");
						// TODO Auto-generated method stub
						
					}

					
								

					@Override
					public void onSuccess(ArrayList<Team> result) {
						if( result.size() > 0 ){
							Window.alert("result:" + result.size());
						
					}
				 }
				 }
			
				 );
		table.addColumn(nameColumn, "Spieltag");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Matchday> selectionModel = new SingleSelectionModel<Matchday>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Matchday selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.name);
				}
			}
		});

		// Set the total row count. This isn't strictly necessary,
		// but it affects paging calculations, so its good habit to
		// keep the row count up to date.
		table.setRowCount(MATCHDAYS.size(), false);

		// Push the data into the widget.
		table.setRowData(0, MATCHDAYS);

		VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(1);
		panel.setWidth("100");
		panel.add(table);

		// Add the widgets to the root panel.
		RootPanel.get("Navigator").add(panel);
	}

	private void loadLogin() {

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
		};}}

/*
 * protected void loadAllTeams() { ergebnis.addClickHandler(new ClickHandler() {
 * private void onClick(ClickEvent event) {
 * 
 * System.out.println();
 * 
 * } }); }
 */
// add style names to widgets
// ergebnis.setStylePrimaryName("ergebnisButton");

// Image startImage = new Image("/images/HdM_Logo.jpg");
// startImage.setStylePrimaryName("image");
// navigator.add(startImage);
