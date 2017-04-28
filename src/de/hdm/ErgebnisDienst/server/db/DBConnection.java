package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Mithilfe der Klasse DBConnection verwalten wir die Verbindung zu einer
 * Datenbank für den Hockey Ergebnis Dienst.
 * 
 */
public class DBConnection {

	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static Connection con = null;

	/**
	 * Wir verwenden zwei unterschiedliche URL's um uns mit der Datenbank zu
	 * verbinden. Wenn das Projekt lokal ausgeführt wird und eine Verbindung zur
	 * Datenbank hergestellt werden soll, wird über die localUrl eine Connection
	 * hergestellt. Das deployte Projekt ruft den googleUrl String auf um sich
	 * mit der Datenbank zu verbinden.
	 * 
	 */

	//private static String googleUrl = "jdbc:google:mysql://hockeyErgebnisDienst:textydb?user=root&password=root";
	private static String localUrl = "jdbc:mysql://localhost:3306/?user=root&password=root";

	public static Connection connection() {
		// Wenn es noch keine Verbindung zur Datenbank gibt, wird versucht diese
		// herzustellen.
		if (con == null) {
			String url = null;
			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
					// Wenn über die Verbindung über die App Engine geschieht
					// dann:
					// Connecting from App Engine.
					// Load the class that provides the "jdbc:google:mysql://"
					// prefix.
					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
				}

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}

		// Die Verbindung wird zurückgegeben.
		return con;
	}

}