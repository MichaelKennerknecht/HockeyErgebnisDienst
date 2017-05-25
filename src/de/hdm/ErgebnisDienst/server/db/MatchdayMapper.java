package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.hdm.ErgebnisDienst.shared.bo.Matchday;

public class MatchdayMapper {
	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static MatchdayMapper matchdayMapper = null;

	/**
	 * Die statische Methode kann somit durch MatchdayMapper.matchdayMapper()
	 * aufgerufen werden. Dadurch wird sichergestellt, dass nur eine einzelne
	 * Instanz der Mapper existiert.
	 * 
	 * @return MatchdayMapper Objekt
	 */
	public static MatchdayMapper matchdayMapper() {
		if (matchdayMapper == null) {
			matchdayMapper = new MatchdayMapper();
		}
		return matchdayMapper;
	}

	/**
	 * Mithilfe von diesem Konstruktor wird verhindert, dass eine neue Instanz
	 * der Klasse erzeugt wird.
	 */
	protected MatchdayMapper() {

	}

	/**
	 * Alle Matchdays aufrufen
	 *
	 */
	public List<Matchday> getAllMatchdays() {
		Connection con = DBConnection.connection();
		List<Matchday> result = new LinkedList<Matchday>();
		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Alle Matchdays der Tabelle werden abgefragt
			ResultSet rs = stmt.executeQuery("SELECT md_Id, name FROM hockeydienst.matchday");

			if (rs.next()) {
				Matchday matchday = new Matchday();
				matchday.setMdId(rs.getInt("md_id"));
				matchday.setName(rs.getString("name"));
				result.add(matchday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Matchdays in der Liste ausgeben
		return result;

	}

	/**
	 * 
	 * @param matchday
	 * @return matchday
	 * @throws Exception
	 */
	public Matchday insert (Matchday matchday) throws Exception {
		//DB-Verbindung aufbauen
		Connection con = DBConnection.connection();
		System.out.println("Matchday insert Mapper");
		try {
			Statement stmt = con.createStatement();
			
		//Check was der höchste Primärschlüssel ist
			
		ResultSet rs = stmt
				.executeQuery("SELECT MAX(md_id) AS maxMd_Id" 
						+ "FROM matchday");
		//Falls was zurückkommt, kann dies nur einzeilig sein
		if (rs.next()) {
		//Primärschlüssel um 1 erhöhen
			matchday.setMdId(rs.getInt("maxMd_Id")+1);
			stmt = con.createStatement();
		
		//Einfügeoption
			stmt.executeUpdate("INSERT INTO matchday (md_id, name, md_count)"
					+ "VALUES ("
					+ matchday.getMdId()
					+ ",'"
					+ matchday.getName()
					+ "','"
					+ matchday.getMdCount()
					+ "',1)");
				}
		} catch (SQLException e) {
			e.printStackTrace();				
		
		}
	return matchday;
		}
	public Matchday deleteMatchday (Matchday m) {
		//DB-Verbindung aufbauen
				Connection con =DBConnection.connection();
				System.out.println("Matchday delete Mapper");
				try {
					Statement stmt = con.createStatement();

		//Delete FROM 'matchday' WHERE 'name' = "Spieltag"
				stmt.executeUpdate("DELETE FROM 'matchday'" + "WHERE 'md_id' ="
						+m.getName());
				}catch (SQLException e2) {
					e2.printStackTrace();
				}
				return m;
	}
}
