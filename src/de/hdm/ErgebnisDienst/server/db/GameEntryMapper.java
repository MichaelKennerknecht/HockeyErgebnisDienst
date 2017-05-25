package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.thirdparty.javascript.jscomp.FunctionInformationMap.Entry;

import de.hdm.ErgebnisDienst.shared.bo.GameEntry;

public class GameEntryMapper {
	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static GameEntryMapper gameEntryMapper = null;
	/**
	 * Die statische Methode kann somit durch GameEntryMapper.gameEntryMapper() aufgerufen
	 * werden. Dadurch wird sichergestellt, dass nur eine einzelne Instanz der
	 * Mapper existiert.
	 * 
	 * @return GameEntryMapper Objekt
	 */
	public static GameEntryMapper gameEntryMapper() {
		if (gameEntryMapper == null) {
			gameEntryMapper = new GameEntryMapper();
		}
		return gameEntryMapper;
	}

	/**
	 * Mithilfe von diesem Konstruktor wird verhindert, dass eine neue Instanz
	 * der Klasse erzeugt wird.
	 */
	protected GameEntryMapper() {

	}
	/**
	*Alle Ergebnisse aufrufen
	*
	*/
	public List<GameEntry> getAllGameEntrys() {
		Connection con = DBConnection.connection();
		List<GameEntry> result = new LinkedList<GameEntry>();
		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Alle GameEntrys der Tabelle werden abgefragt
			ResultSet rs = stmt.executeQuery("SELECT game_id, home_id, guest_id, goals_guest, goals_home, FROM hockeydienst.gameentry");

			if (rs.next()) {
				GameEntry entry = new GameEntry();
				entry.setGameId(rs.getInt("game_id"));
				entry.setHomeId(rs.getInt("home_id"));
				entry.setGuestId(rs.getInt("guest_id"));
				entry.setGoalsGuest(rs.getInt("goals_guest"));
				entry.setGoalsHome(rs.getInt("goals_home"));
				result.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Entrys in der Liste ausgeben
		return result;

	}
	/**
	 * 
	 * @param GameEntry
	 * @return GameEntry
	 * @throws Exception
	 */
	public GameEntry insert (GameEntry entry) throws Exception {
		//DB-Verbindung aufbauen
		Connection con = DBConnection.connection();
		System.out.println("GameEntry insert Mapper");
		try {
			Statement stmt = con.createStatement();
			
		//Check was der höchste Primärschlüssel ist
			
		ResultSet rs = stmt
				.executeQuery("SELECT MAX(game_id) AS maxGame_Id" 
						+ "FROM gameentry");
		//Falls was zurückkommt, kann dies nur einzeilig sein
		if (rs.next()) {
		//Primärschlüssel um 1 erhöhen
			entry.setGameId(rs.getInt("maxGame_Id")+1);
			stmt = con.createStatement();
		
		//Einfügeoption
			stmt.executeUpdate("INSERT INTO gameentry (game_id, home_id, guest_id, goals_guest, goals_home)"
					+ "VALUES ("
					+ entry.getGameId()
					+ ",'"
					+ entry.getHomeId()
					+ "','"
					+ entry.getGuestId()
					+ "','"
					+ entry.getGoalsGuest()
					+ "','"
					+ entry.getGoalsHome()
					+ "',1)");
					
				}
		} catch (SQLException e) {
			e.printStackTrace();				
		
		}
	return entry;
		}
	public Entry deleteGameEntry (Entry ge) {
		//DB-Verbindung aufbauen
				Connection con =DBConnection.connection();
				System.out.println("GameEntry delete Mapper");
				try {
					Statement stmt = con.createStatement();

		//Delete FROM 'gameentry' WHERE 'game_id' = ""
				stmt.executeUpdate("DELETE FROM 'gameentry'" + "WHERE 'game_id' ="
						+ge.getId());
				}catch (SQLException e2) {
					e2.printStackTrace();
				}
				return ge;
	}
}