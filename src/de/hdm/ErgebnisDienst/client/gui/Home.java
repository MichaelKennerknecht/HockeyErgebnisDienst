package de.hdm.ErgebnisDienst.client.gui;

import com.google.gwt.user.client.ui.HTML;


/**
 * Home bietet ein Showcase um die Begrüßungsfläche
 * darstellen zu können.
**/
public class Home extends Update {
	/**
	 * Überschrift des Showcase (graue Überschrift)
	 */
	private String headlineText;
	
	/**
	 * StyleSheet Klasse für die Überschrift des Showcase
	 */
	private String headlineTextStyle;

	/**
	 * Konstruktor der Klasse Home
	 * erzeugt die Willkommens-Fläche in der, 
	 * der eingeloggt Benutzer mit dem von Google 
	 * übergebenen Username begrüßt wird.
	 */
	public Home() {
		
		HTML welcomeHeader = new HTML(
		"Herzlich Willkommen beim Ergebnisdienst");
		this.add(welcomeHeader);
		welcomeHeader.setStyleName("h1");
		
		
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
	 * Aufbau des Willkommen-Panels (rechte Fläche), 
	 * in welcher eine kurze Beschreibung der Appplikation steht.
	 */
	protected void run() {
		
		HTML welcomeText = new HTML(
				
				
				"Dies ist ein System zum Eintragen von Ergebnissen. "
						+ "Das System ist von den Funktionen bewusst klein gehalten, um die nötigen Anforderungen für die Wiederholungsprüfung "
						+ "im Modul IT Projekt im Studiengang Wirtschaftsinformatik und digitale Medien an der Hochschule der Medien Stuttgart zu erfüllen. "
						+ "Das System soll den Studenten <b>Michael Kennerknecht</b> bei Anwendung des theoretischen Wissens unterstützen und als praktische Übung dienen."
						+ " Um Zugriff auf die Ergebnisse zu erhalten, klicken Sie bitte auf der linken Navigationsleiste auf einen beliebigen Spieltag. ");
		this.add(welcomeText);
		welcomeText.setStyleName("bodyStyle");

	}

}