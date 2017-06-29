package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.thirdparty.javascript.jscomp.FunctionInformationMap.Entry;
import com.google.gwt.user.client.Window;

import de.hdm.ErgebnisDienst.shared.bo.GameEntry;
import de.hdm.ErgebnisDienst.shared.bo.Matchday;

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
	public ArrayList<GameEntry> getAllGameEntrys(Matchday md) {
		Connection con = DBConnection.connection();
		ArrayList<GameEntry> result = new ArrayList<GameEntry>();
		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Alle GameEntrys der Tabelle werden abgefragt
			ResultSet rs = stmt.executeQuery("SELECT game_id, home_id, (SELECT name FROM hockeydienst.teams WHERE team_id = home_id) AS home_name, guest_id, (SELECT name FROM hockeydienst.teams WHERE team_id = guest_id) AS guest_name, goals_guest, goals_home, matchday_id FROM hockeydienst.gameentry WHERE matchday_id = " + md.getMdId());
			
			//TODO
			//home,guest name anpassen
			
			while (rs.next()) {
				GameEntry entry = new GameEntry();
				entry.setGameId(rs.getInt("game_id"));
				entry.setHomeId(rs.getInt("home_id"));
				entry.setGuestId(rs.getInt("guest_id"));
				entry.setHome_name(rs.getString("home_name"));
				entry.setGuest_name(rs.getString("guest_name"));
				entry.setGoalsGuest(rs.getInt("goals_guest"));
				entry.setGoalsHome(rs.getInt("goals_home"));
				entry.setMatchday(rs.getInt("matchday_id"));
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
	
	public boolean saveGameEntry (GameEntry ge) {
		Connection con =DBConnection.connection();
		System.out.println("GameEntry save Mapper");
		boolean result = false;
		try {
			
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT team_id FROM teams WHERE name LIKE '"+ge.getHome_name()+"'");
			ResultSet rs2 = stmt2.executeQuery("SELECT team_id FROM teams WHERE name LIKE '"+ge.getGuest_name()+"'");
			
			if (rs1.next() && rs2.next()) {
				int homeTeamId = rs1.getInt("team_id");
				int guestTeamId = rs2.getInt("team_id");
			
				ge.setHomeId(homeTeamId);
				ge.setGuestId(guestTeamId);
				
				Statement stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO gameentry (matchday_id,home_id,guest_id,goals_guest,goals_home) VALUES ("+ge.getMatchday()+","+ge.getHomeId()+","+ge.getGuestId()+","+ge.getGoalsGuest()+","+ge.getGoalsHome()+")");
				result = true;
			}
		} catch (SQLException e2) {
			result = false;
			e2.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteGameEntry (GameEntry ge) {
		boolean result = false;
		//DB-Verbindung aufbauen
				Connection con =DBConnection.connection();
				System.out.println("GameEntry delete Mapper");
				try {
					Statement stmt = con.createStatement();
					Statement stmt1 = con.createStatement();
					Statement stmt2 = con.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT team_id FROM teams WHERE name LIKE '"+ge.getHome_name()+"'");
					ResultSet rs2 = stmt2.executeQuery("SELECT team_id FROM teams WHERE name LIKE '"+ge.getGuest_name()+"'");
					
					if (rs1.next() && rs2.next()) {
						int homeTeamId = rs1.getInt("team_id");
						int guestTeamId = rs2.getInt("team_id");
					
						ge.setHomeId(homeTeamId);
						ge.setGuestId(guestTeamId);
						

						stmt.executeUpdate("DELETE FROM gameentry WHERE home_id = "+ge.getHomeId()+" AND guest_id = "+ge.getGuestId());
					
						result = true;
					}
				}catch (SQLException e2) {
					e2.printStackTrace();
				}
				return result;
	}
}