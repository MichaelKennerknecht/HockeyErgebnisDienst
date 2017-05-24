package de.hdm.ErgebnisDienst.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.hdm.ErgebnisDienst.shared.bo.Team;

public class TeamMapper {

	/*
	 * TO DO Create Update Delete Select all teams, verschiedene
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

	public List <Team> getAllTeams() {
		Connection con = DBConnection.connection();
		List<Team> result = new LinkedList<Team>();
		try {
			// Neues Statement anlegen
			Statement stmt = con.createStatement();
			// Alle Teams der Tabelle werden abgefragt
			ResultSet rs = stmt.executeQuery("SELECT team_Id, name FROM hockeydienst.teams");

			if (rs.next()) {
				Team team = new Team();
				team.setTeamId(rs.getInt("team_Id"));
				team.setName(rs.getString("name"));
				result.add(team);
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			//Teams in der Liste ausgeben
			return result;
		
		}
		
}

