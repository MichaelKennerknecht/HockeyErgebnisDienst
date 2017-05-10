package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hdm.ErgebnisDienst.shared.bo.Team;

public class TeamMapper {

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

}