package de.hdm.ErgebnisDienst.client;

import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
import de.hdm.ErgebnisDienst.shared.LoginInfo;
import de.hdm.ErgebnisDienst.shared.bo.Matchday;
import de.hdm.ErgebnisDienst.shared.bo.Team;
import de.hdm.ErgebnisDienst.client.Home;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

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
	private ErgebnisDienstAdministrationAsync adminService = ClientsideSettings.getAdministration();

	private VerticalPanel navigator = new VerticalPanel();
	private VerticalPanel details = new VerticalPanel();
	private VerticalPanel matchdays = new VerticalPanel();
	private HorizontalPanel topPanel = new HorizontalPanel();

	private VerticalPanel loginPanel = new VerticalPanel();
	private VerticalPanel loginTextPanel = new VerticalPanel();
	private Label loginLabel = new Label("Bitte loggen Sie sich mit Ihrem Google-Account ein:");
	public static Label userLabel = new Label();
	final Label usernameLabel = new Label("Username");
	final Label passwordLabel = new Label("Password");
	private Anchor signInLink = new Anchor("Login");
	final Button ergebnis = new Button("Ergebnisse eintragen");
	private LoginInfo loginInfo = null;

	private ArrayList<Team> teams = null;

	// Create a Flex Table
	final FlexTable flexTable = new FlexTable();
	FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

	de.hdm.ErgebnisDienst.client.gui.LoginServiceAsync loginService = GWT
			.create(de.hdm.ErgebnisDienst.client.gui.LoginService.class);

	/**
	 * Die Instanz von LoginInfo dient als Hilfsklasse fuer das Login und stellt
	 * erforderliche Variablen und Operationen bereit.
	 */

	public void onModuleLoad() {
		Window.alert("Willkommen zum Hockey Ergebnisdienst, viel Spaß!");

		// Add the widgets to the root panel
		RootPanel.get("Navigator").add(ergebnis);
		RootPanel.get("Top").add(topPanel);
		RootPanel.get("Details").add(new Home());
		RootPanel.get("Details").add(flexTable);

		// Beginn Flextable für Ergebnisse

		flexTable.addStyleName("flexTable");
		flexTable.setWidth("32em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);

		// Add some text
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setHTML(0, 0, "Ergebnisse");
		cellFormatter.setColSpan(0, 0, 5);

		// Add a button that will add more rows to the table
		Button addRowButton = new Button("Ergebnis hinzufügen");
		addRowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addRow(flexTable);
			}
		});

		addRowButton.addStyleName("fixedWidthButton");

		// Add a button that will add more rows to the table
		Button removeRowButton = new Button("Ergebnis löschen");
		removeRowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeRow(flexTable);
			}
		});

		removeRowButton.addStyleName("fixedWidthButton");

		VerticalPanel buttonPanel = new VerticalPanel();
		buttonPanel.setStyleName("flexTable-buttonPanel");
		buttonPanel.add(addRowButton);
		buttonPanel.add(removeRowButton);
		flexTable.setWidget(0, 1, buttonPanel);
		cellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);

		// Example rows for the beginning
		addRow(flexTable);

		getMatchdayTable();

	}

	/**
	 * Add a row to the flex table.
	 */

	public void getMatchdayTable() {

		// Datensätze aus der Datenbank in List schreiben.
		adminService.getAllMatchdays(new AsyncCallback<ArrayList<Matchday>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("FEHLER get allMatchdays: " + caught);

			}

			@Override
			public void onSuccess(ArrayList<Matchday> result) {
				Window.alert("OnSuccess");
				ArrayList<Matchday> MATCHDAYS = result;
				CellTable<Matchday> table = new CellTable<Matchday>();
				table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

				// Add a text column to show the name.
				TextColumn<Matchday> nameColumn = new TextColumn<Matchday>() {

					@Override
					public String getValue(Matchday object) {
						return object.getName();

					}
				};
				table.addColumn(nameColumn, "Spieltage");

				// Add a selection model to handle user selection.
				final SingleSelectionModel<Matchday> selectionModel = new SingleSelectionModel<Matchday>();
				table.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Matchday selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("You selected: " + selected.getName());
						}
					}
				});

				// Set the total row count. This isn't strictly necessary,
				// but it affects paging calculations, so its good habit to
				// keep the row count up to date.
				table.setRowCount(MATCHDAYS.size(), true);

				// Push the data into the widget.

				// table.setRowData(0, MATCHDAYS);
				table.setRowData(MATCHDAYS);

				VerticalPanel panel = new VerticalPanel();
				panel.setBorderWidth(1);
				panel.setWidth("105");
				panel.add(table);

				// Add the widgets to the root panel.
				RootPanel.get("Navigator").add(panel);
			}
		});

		// Create a CellTable.

	}

//	private AsyncCallback<ArrayList<Team>> getTeamsCallback() {
//		AsyncCallback<ArrayList<Team>> asyncCallback = new AsyncCallback<ArrayList<Team>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("FEHLER get allMatchdays: " + caught);
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Team> result) {
//
//				@Override
//				public String getValue(Team object) {
//					return object.getName();
//
//				}
//				// Window.alert(result.get(0).getName());
//				// teams = result;
//
//			}
//		};
//		return asyncCallback;
//	}
	
	

	private void loadTeams() {
		adminService.getAllTeams(new AsyncCallback<ArrayList<Team>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("FEHLER ahahahahah: " + caught);
			}

			@Override
			public void onSuccess(ArrayList<Team> result) {
				
				Window.alert("klappt: " );

			}
		});
	}

private ListBox loadListBoxTeams(){
	loadTeams();
	ListBox lb = new ListBox();	
	for(Team te: teams){
		lb.addItem(te.getName());
	}
	return lb;
}
	

	// adminService.getAllTeams(new AsyncCallback<ArrayList<Team>>() {
	// @Override
	// public void onFailure(Throwable caught) {
	// Window.alert("FEHLER get allMatchdays: " + caught);
	//
	// }
	//
	// public void onSuccess(ArrayList<Team> result) {
	// ListBox lb = new ListBox();
	// for (Team te : result) {
	// lb.addItem(te.getName());
	// lbx = lb;
	// }
	// }
	// });

	private void addRow(FlexTable flexTable) {
		int numRows = flexTable.getRowCount();
		flexTable.setWidget(numRows, 0, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
		flexTable.setWidget(numRows, 1, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
		flexTable.setWidget(numRows, 2, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
		
		//flexTable.setWidget(numRows, 3, loadListBoxTeams());
		flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
	}

	/**
	 * Remove a row from the flex table.
	 */
	private void removeRow(FlexTable flexTable) {
		int numRows = flexTable.getRowCount();
		if (numRows > 1) {
			flexTable.removeRow(numRows - 1);
			flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows - 1);
		}

		// Navigator-Tabelle

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
	};
}

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