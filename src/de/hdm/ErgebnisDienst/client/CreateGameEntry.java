package de.hdm.ErgebnisDienst.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.ErgebnisDienst.shared.bo.GameEntry;

public class CreateGameEntry extends Showcase {
		
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
		public CreateGameEntry() {
			this.headlineText = "Bitte wählen Sie aus, was Sie anlegen möchten!";
			this.headlineTextStyle = "formTitle";
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
		   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
		   * eine "Einschubmethode", die von einer Methode der Basisklasse
		   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
		   */
		protected void run() {		
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

	}

