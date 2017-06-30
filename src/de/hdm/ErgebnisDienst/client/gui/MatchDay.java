package de.hdm.ErgebnisDienst.client.gui;

import java.util.ArrayList;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ErgebnisDienst.client.HockeyErgebnisDienst;
import de.hdm.ErgebnisDienst.shared.bo.Matchday;

public class MatchDay extends Update {
	
	private Update currentShowcase;

	/**
	 * Überschrift des Showcase (graue Überschrift)
	 */
	private String headlineText;

	/**
	 * StyleSheet Klasse für die Überschrift des Showcase
	 */
	private String headlineTextStyle;

	/**
	 * Ergebnis anlegen
	 */
	public MatchDay() {
		this.headlineText = " ";
		this.headlineTextStyle = "formTitle";
		this.currentShowcase = this;
	
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

	
	
	public void run() {

		
		
		// Datensätze aus der Datenbank in List schreiben.
		HockeyErgebnisDienst.adminService.getAllMatchdays(new AsyncCallback<ArrayList<Matchday>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("FEHLER get allMatchdays: " + caught);

			}

			@Override
			public void onSuccess(ArrayList<Matchday> result) {
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


				// Zugriff auf andere Tabelle
				table.addColumn(nameColumn, "Spieltagsübersicht");

				// Add a selection model to handle user selection.
				final SingleSelectionModel<Matchday> selectionModel = new SingleSelectionModel<Matchday>();
				table.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Matchday selected = selectionModel.getSelectedObject();
						if (selected != null) {
							// Hier
							Window.alert("You selected: " + selected.getName());
							Update update = new CreateGameEntry(selected);
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(update);
						}
					}
				});

				// Set the total row count. This isn't strictly necessary,
				// but it affects paging calculations, so its good habit to
				// keep the row count up to date.
				
				
				// GLEICHES mit Matchdays von oben
				table.setRowCount(MATCHDAYS.size(), true);

				// Push the data into the widget.

				// table.setRowData(0, MATCHDAYS);
				table.setRowData(MATCHDAYS);
				
				currentShowcase.add(table);
				
			}
			
			
		});
	

	};
}
