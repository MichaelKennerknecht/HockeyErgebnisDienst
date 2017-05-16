package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hdm.ErgebnisDienst.shared.bo.Team;

public class TeamMapper {

	/*TO DO
	 * Create
	 * Update
	 * Delete
	 * Read
	 */
	
	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static TeamMapper teamMapper = null;

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
	 * Mithilfe von diesem Konstruktor wird verhindert, dass eine neue Instanz
	 * der Klasse erzeugt wird.
	 */
	protected TeamMapper() {

	}

	public TeamMapper read(Team teamHome) {
		Connection con = DBConnection.connection();

		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Der höchste Primärschlüssel wird in der Datenbank gesucht
			ResultSet rs = stmt.executeQuery("READ MAX(userId) AS maxid " + "FROM hockeydienst.user ");

			if (rs.next()) {
				// Die userId wird nun anhand der aktuell höchsten userId um
				// eins erhöht.
				user.setUserId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				// Statement ausfüllen und als Query an die Datenbank schicken
				stmt.executeUpdate("SELECT INTO hockeydienst.user (user_id, name, googleid, is_admin)" + "VALUES ("
						+ user.getUserId() + ", " + "'" + user.getName() + "'" + ", " + "'" + user.getGoogleId() + "'"
						+ ", " + "'" + user.getIsAdmin() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// User-Objekt zurückgeben
		return user;
	}
}