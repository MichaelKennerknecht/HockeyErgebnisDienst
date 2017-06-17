package de.hdm.ErgebnisDienst.client;

import com.google.gwt.user.client.ui.HTML;


/**
 * Home bietet ein Showcase um die Begrüßungsfläche
 * darstellen zu können.
**/
public class Home extends Showcase {
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
		this.headlineText = "Herzlich Willkommen beim Ergebnisdienst";
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
	 * Aufbau des Willkommen-Panels (rechte Fläche), 
	 * in welcher eine kurze Beschreibung der Appplikation steht.
	 */
	protected void run() {
		HTML welcomeText = new HTML(
				"Dies ist ein System zum Eintragen von Ergebnissen. "
						+ "Das System ist von den Funktionen bewusst klein gehalten, um die nötigen Anforderungen für die Wiederholungsprüfung "
						+ "im Modul <br>IT Projekt</br> im Studiengang Wirtschaftsinformatik und digitale Medien"
						+ " an der Hochschule der Medien Stuttgart zu erfüllen. "
						+ "Das System soll den Studenten Michael Kennerknecht bei Anwendung des theoretischen"
						+ "Wissens unterstützen und als praktische Übung dienen.");
		this.add(welcomeText);

	}

}