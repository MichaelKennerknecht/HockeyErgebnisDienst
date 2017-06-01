package de.hdm.ErgebnisDienst.shared.bo;

import java.io.Serializable;

public class Team implements Serializable { 
	private static final long serialVersionUID = 1L;

	private int teamId;
	private String name;
	
	public int getTeamId () {
		return teamId;
	}
	public void setTeamId (int teamId) {
		this.teamId = teamId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
