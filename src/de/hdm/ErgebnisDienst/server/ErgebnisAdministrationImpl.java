package de.hdm.ErgebnisDienst.server;

import de.hdm.ErgebnisDienst.server.db.*;
import de.hdm.ErgebnisDienst.shared.bo.*;
import de.hdm.ErgebnisDienst.shared.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;


/**
 * Implementierungsklasse des Interface {@link AdministationService}. Diese
 * Klasse ist <em>die</em> Klasse, die saemtliche Applikationslogik (oder engl.
 * Business Logic) aggregiert. Die Applikationslogik findet sich in den Methoden
 * dieser Klasse. Diese Klasse steht mit einer Reihe weiterer Datentypen in
 * Verbindung. Dies sind:
 * <ol>
 * <li>{@link AdministrationService}: Dies ist das <em>lokale</em> - also
 * Server-seitige - Interface, das die im System zur Verfuegung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link AdministrationServiceAsync}:
 * <code>AdministartionServiceImpl</code> und <code>AdministrationService</code>
 * bilden nur die Server-seitige Sicht der Applikationslogik ab. Diese basiert
 * vollstaendig auf synchronen Funktionsaufrufen. Wir muessen jedoch in der Lage
 * sein, Client-seitige asynchrone Aufrufe zu bedienen. Dies bedingt ein
 * weiteres Interface, das in der Regel genauso benannt wird, wie das synchrone
 * Interface, jedoch mit dem zusaetzlichen Suffix "Async". Es steht nur
 * mittelbar mit dieser Klasse in Verbindung. Die Erstellung und Pflege der
 * Async Interfaces wird durch das Google Plugin semiautomatisch unterstuetzt.
 * Weitere Informationen unter {@link AdministrationServiceAsync}.</li>
 * <li>{@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig ueber GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis fuer die Anbindung von <code>AdministrationServiceImpl</code> an die
 * Runtime des GWT RPC-Mechanismus.</li>
 * </ol>
 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehoeren der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab. Beachten Sie, dass saemtliche Methoden, die
 * mittels GWT RPC aufgerufen werden koennen ein
 * <code>throws IllegalArgumentException</code> in der Methodendeklaration
 * aufweisen. Diese Methoden duerfen also Instanzen von
 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions koennen
 * z.B. Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
 */

@SuppressWarnings("serial")
public class ErgebnisAdministrationImpl extends RemoteServiceServlet implements ErgebnisDienstAdministration {


	/**
	 * Eindeutige SerialVersion ID. Wird zum Serialisieren der Klasse benoetigt.
	 */
	private static final long serialVersionUID = 1L;

	// private AppUser currentUser = null;

	/**
	 * Referenz auf das zugehörige Note-Objekt.
	 */

	// private Note note = null;

	private Date date = new Date();

	/**
	 * Referenz auf den DatenbankMapper, der Userobjekte mit der Datenbank
	 * abgleicht.
	 */
	private UserMapper uMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Noteobjekte mit der Datenbank
	 * abgleicht.
	 */
	public GameEntryMapper nMapper;

	/**
	 * Referenz auf den DatenbankMapper, der Permissionobjekte mit der Datenbank
	 * abgleicht.
	 */
	private MatchdayMapper pMapper;

	/**
	 * Referenz auf den DatenbankMapper, der Notebookobjekte mit der Datenbank
	 * abgleicht.
	 */
	private TeamMapper nbMapper;

	/**
	 * Referenz auf den DatenbankMapper, der Duedateobjekte mit der Datenbank
	 * abgleicht.
	 */
	private UserMapper ddMapper;

	public ErgebnisAdministrationImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {

		this.uMapper = UserMapper.userMapper();
		this.nMapper = GameEntryMapper.gameEntryMapper();
		this.pMapper = MatchdayMapper.matchdayMapper();
		this.ddMapper = UserMapper.userMapper();
		this.nbMapper = TeamMapper.teamMapper();

	}

	/**
	 * Auslesen des aktuellen Benutzernamen aus der Google Accounts API, um das
	 * Profil des aktuellen Benutzers aus der Datenbank zu lesen.
	 */
	/**
	 * In dieser Methode kann anhand eines Users nach dessen Notizbuechern
	 * gesucht werden. Diese werden dann in einer ArrayList zurueck gegeben. Ist
	 * noch kein Notizbuch angelegt wird eins angelegt, zusammen mit einer
	 * Permission.
	 */
	public List<Team> getAllTeams() throws IllegalArgumentException {
		List<Team> list = this.nbMapper.getAllTeams();
		
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
			
	}

	
}
