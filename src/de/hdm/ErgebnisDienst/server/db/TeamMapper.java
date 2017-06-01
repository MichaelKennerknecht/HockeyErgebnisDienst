package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.hdm.ErgebnisDienst.server.db.DBConnection;

import de.hdm.ErgebnisDienst.shared.bo.Team;

public class TeamMapper {
	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static TeamMapper teamMapper = null;

	/**
	 * Mithilfe von diesem Konstruktor wird verhindert, dass eine neue Instanz
	 * der Klasse erzeugt wird.
	 */
	protected TeamMapper() {

	}

	/**
	 * Die statische Methode kann somit durch TeamMapper.teamMapper() aufgerufen
	 * werden. Dadurch wird sichergestellt, dass nur eine einzelne Instanz der
	 * Mapper existiert.
	 * 
	 * @return TeamMapper Objekt
	 */
	public static TeamMapper teamMapper() {
		if (teamMapper == null) {
			teamMapper = new TeamMapper();
		}
		return teamMapper;
	}

	/**
	 * Alle Teams aufrufen
	 *
	 */
	public Team findByName(String name) {

		/**
		 * DB-Verbindung holen & Erzeugen eines neuen SQL-Statements.
		 */
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/**
			 * Statement ausf�llen und als Query an die DB schicken
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM teams " + "WHERE name='" + name + "';");
			//ResultSet rs = stmt.executeQuery("UPDATE teams SET name = " + name + "WHERE team_id = 2;");

			/**
			 * Da id Primaerschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {
				System.out.println(rs.toString());
				Team t = new Team();
				t.setName(rs.getString("name"));
				t.setTeamId(rs.getInt("team_id"));
				return t;
			}
		} catch (SQLException e) {
			System.out.println("SQL Abfrage fehlgeschlagen. ");
			e.printStackTrace();

			return null;
		}

		return null;
	}
	/**
	 * public List<Team> getAllTeams() { Connection con =
	 * DBConnection.connection(); List<Team> result = new LinkedList<Team>();
	 * try { // Neues Statement anlegen Statement stmt = con.createStatement();
	 * // Alle Teams der Tabelle werden abgefragt ResultSet rs =
	 * stmt.executeQuery("SELECT team_Id, name FROM hockeydienst.teams");
	 * 
	 * if (rs.next()) { Team team = new Team();
	 * team.setTeamId(rs.getInt("team_Id")); team.setName(rs.getString("name"));
	 * result.add(team); } } catch (SQLException e) { e.printStackTrace(); } //
	 * Teams in der Liste ausgeben return result;
	 * 
	 * }
	 **/

}
