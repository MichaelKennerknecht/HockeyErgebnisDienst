package de.hdm.ErgebnisDienst.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HockeyErgebnisDienst implements EntryPoint {
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stocksFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private Button addResultButton = new Button("Add");
	private Label lastUpdatedLabel = new Label();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// TODO Create table for stock data.
		stocksFlexTable.setText(0, 0, "Symbol");
		stocksFlexTable.setText(0, 1, "Price");
		stocksFlexTable.setText(0, 2, "Change");
		stocksFlexTable.setText(0, 3, "Remove");

		// TODO Assemble Add Stock panel.
		addPanel.add(newSymbolTextBox);
		addPanel.add(addResultButton);

		// TODO Assemble Main panel.
		mainPanel.add(stocksFlexTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);

		// TODO Associate the Main panel with the HTML host page.
		RootPanel.get("stockList").add(mainPanel);
		// TODO Move cursor focus to the input box.
		newSymbolTextBox.setFocus(true);
	}
}