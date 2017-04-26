package de.hdm.ErgebnisDienst.shared.bo;

import java.io.Serializable;

	
public class GameEntry {

	private int gameId;
	private int mdId;
	private int homeId;
	private int guestId;
	private int goalsHome;
	private int goalsGuest;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getMdId() {
		return mdId;
	}
	public void setMdId(int mdId) {
		this.mdId = mdId;
	}
	public int gethomeId() {
		return homeId;
	}
	public void setHomeId (int homeId) {
		this.homeId = homeId;
	}
	public int getGuestId() {
		return guestId;
	}
		public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public int getGoalsHome() {
		return goalsHome;
	}
	public void setGoalsHome (int goalsHome) {
		this.goalsHome = goalsHome;
	}
		
	public int getGoalsGuest () {
		return goalsGuest;
	}
	
	public void setGoalsGuest (int goalsGuest) {
		this.goalsGuest = goalsGuest;
	}
	
	}
