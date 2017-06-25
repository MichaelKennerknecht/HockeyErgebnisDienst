package de.hdm.ErgebnisDienst.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable;

import de.hdm.ErgebnisDienst.client.HockeyErgebnisDienst;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;
import de.hdm.ErgebnisDienst.shared.bo.GameEntry;
import de.hdm.ErgebnisDienst.shared.bo.Matchday;
import de.hdm.ErgebnisDienst.shared.bo.Team;

public class CreateGameEntry extends Update {

	/**
	 * Überschrift des Showcase (graue Überschrift)
	 */
	private String headlineText;

	/**
	 * StyleSheet Klasse für die Überschrift des Showcase
	 */
	private String headlineTextStyle;
	
	private Matchday matchday;
	
	private ListBox team1 = new ListBox();	
	private ListBox team2 = new ListBox();	
	
	private ArrayList<GameEntry> gameEntries;
	
	private final FlexTable flexTable;

	/**
	 * Ergebnis anlegen
	 */
	public CreateGameEntry(Matchday md) {
		this.headlineText = md.getName();
		this.flexTable = new FlexTable();
		this.headlineTextStyle = "formTitle";
		this.matchday = md;
		this.gameEntries = new ArrayList<>();
		this.loadListBoxTeams();
		this.loadSpielErgebnisse();
	}

	/**
	 * Auslesen des Titels von jeweiligen Showcase (graue Überschrift)
	 */
	protected String getHeadlineText() {
		return this.headlineText;
	}

	/**
	 * Auslesen der Formatierung des Titels
	 */
	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
	}

	/**
	 * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie
	 * ist eine "Einschubmethode", die von einer Methode der Basisklasse
	 * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
	 */
	protected void run() {

			
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		
		// Flextable für Ergebnisse

		flexTable.addStyleName("flexTable");
		flexTable.setWidth("32em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);

		// FlexTable dem RootPanel hinzufügen
		RootPanel.get("Details").add(flexTable);
		
		// Add some text
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setHTML(0, 0, "Ergebnisse");
		cellFormatter.setColSpan(0, 0, 5);

		// Add a button that will add more rows to the table
		Button addRowButton = new Button("Ergebnis hinzufügen");
		addRowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// neues Erebnis hinzufügen
				
				//TODO
				Widget wTeam1 = flexTable.getWidget(1, 0);
				Widget wTeam2 = flexTable.getWidget(1, 1);
				Widget wErgebnis = flexTable.getWidget(1, 2);
				
				
				if (wTeam1 instanceof ListBox && wTeam2 instanceof ListBox && wErgebnis instanceof TextBox) {
					ListBox lbTeam1 = (ListBox)wTeam1;
					ListBox lbTeam2 = (ListBox)wTeam2;
					TextBox tbErgebnis = (TextBox)wErgebnis;
					
					String sTeam1 = lbTeam1.getSelectedItemText();
					String sTeam2 = lbTeam2.getSelectedItemText();
					String sErgebnis = tbErgebnis.getText().trim();
					if (sErgebnis.contains(":")) {
						String sGoalHome = sErgebnis.substring(0,sErgebnis.indexOf(":"));
						String sGoalGuest = sErgebnis.substring(sErgebnis.indexOf(":")+1); 
						
						// TODO sGoal... in Int umwandeln für DB
						int numRows = flexTable.getRowCount();
						
						flexTable.setWidget(numRows, 0, new HTML(sTeam1));
						flexTable.setWidget(numRows, 1, new HTML(sTeam2));
						flexTable.setWidget(numRows, 2, new HTML(sGoalHome + ":" + sGoalGuest));
						
					}
					
					//TODO Speichere Ergebnisse in DB
				//	GameEntry ge = new GameEntry();
					//ge.setHomeName()
					//HockeyErgebnisDienst.adminService.saveGameEntry(ge)
					// in save MEthode zuerst SQL Abfrage für Team Id SELECT team_id FROM teams WHERE name LIKE '"+ge.getHomeName()+"'
					// int team1 = rs.getInt("team_id")
					// SQL Befehl "INSERT INTO gameentry (team1, team2, ergebnis..)
				}
			}
		});
		// Ergebnislöschen-Button
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

		

		Button createEntryBtn = new Button();
		createEntryBtn.setStylePrimaryName("btn btn-default createBtn btn32");
		createEntryBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();

			}
		});

		this.add(createEntryBtn);

	}
	private void loadListBoxTeams(){
		
		HockeyErgebnisDienst.adminService.getAllTeams(new AsyncCallback<ArrayList<Team>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("FEHLER: " + caught);
			}

			@Override
			public void onSuccess(ArrayList<Team> result) {
				
				for(Team te: result){
					team1.addItem(te.getName());
					team2.addItem(te.getName());
				}
				flexTable.setWidget(1, 0, team1);
				flexTable.setWidget(1, 1, team2);
				flexTable.setWidget(1, 2, new TextBox());
			}
		});
		
		
	}
	
	private void loadSpielErgebnisse(){
		
		HockeyErgebnisDienst.adminService.getAllGameEntrys(matchday, new AsyncCallback<ArrayList<GameEntry>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("FEHLER: " + caught);
			}

			@Override
			public void onSuccess(ArrayList<GameEntry> result) {
				
				gameEntries = result;
				for (GameEntry ge : gameEntries) {
					int numRows = flexTable.getRowCount();
					if (numRows < 2) {
						numRows = 2; 
					}
					flexTable.setWidget(numRows, 0, new HTML(""+ge.getHomeName()));
					flexTable.setWidget(numRows, 1, new HTML(""+ge.getGuestName()));
					flexTable.setWidget(numRows, 2, new HTML(ge.getGoalsHome() + ":" +ge.getGoalsGuest()));
				}
			}
		});
		
		
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

	}
	
}
