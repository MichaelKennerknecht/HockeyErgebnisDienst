package de.hdm.ErgebnisDienst.shared.bo;

import java.io.Serializable;

public class GameEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	private int game_Id;
	private int home_Id;
	private int guest_Id;
	private int goals_Home;
	private int goals_Guest;
	private int matchday;
	private String home_name;
	private String guest_name;

	public int getGameId() {
		return game_Id;
	}

	public void setGameId(int game_Id) {
		this.game_Id = game_Id;
	}

	public int getHomeId() {
		return home_Id;
	}

	public void setHomeId(int home_Id) {
		this.home_Id = home_Id;
	}

	public int getGuestId() {
		return guest_Id;
	}

	public void setGuestId(int guest_Id) {
		this.guest_Id = guest_Id;
	}

	public int getGoalsHome() {
		return goals_Home;
	}

	public void setGoalsHome(int goals_Home) {
		this.goals_Home = goals_Home;
	}

	public int getGoalsGuest() {
		return goals_Guest;
	}

	public void setGoalsGuest(int goals_Guest) {
		this.goals_Guest = goals_Guest;
	}

	public int getMatchday() {
		return matchday;
	}

	public void setMatchday(int matchday) {
		this.matchday = matchday;
	}

	public String getHome_name() {
		return home_name;
	}

	public void setHome_name(String home_name) {
		this.home_name = home_name;
	}

	public String getGuest_name() {
		return guest_name;
	}

	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}

}