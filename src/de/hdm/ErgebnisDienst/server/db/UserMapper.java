package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hdm.ErgebnisDienst.shared.bo.User;

public class UserMapper {
	/**
	 * Hier wird die Klasse instanziiert.
	 */
	private static UserMapper userMapper = null;

	/**
	 * Die statische Methode kann somit durch UserMapper.userMapper() aufgerufen
	 * werden. Dadurch wird sichergestellt, dass nur eine einzelne Instanz der
	 * Mapper existiert.
	 * 
	 * @return UserMapper Objekt
	 */
	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}

	/**
	 * Mithilfe von folgenden Konstruktor wird verhindert, dass eine neue Instanz
	 * der Klasse erzeugt wird.
	 */
	protected UserMapper() {
	}

	public User insert(User user) {
		// Datenbankverbindung holen
		Connection con = DBConnection.connection();

		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Der höchste Primärschlüssel wird in der Datenbank gesucht
			ResultSet rs = stmt.executeQuery("SELECT MAX(userId) AS maxid " + "FROM hockeydienst.user ");

			if (rs.next()) {
				// Die userId wird nun anhand der aktuell höchsten userId um
				// eins erhöht.
				user.setUserId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				// Statement ausfüllen und als Query an die Datenbank schicken
				stmt.executeUpdate("INSERT INTO hockeydienst.user (user_id, name, googleid, is_admin)" + "VALUES ("
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