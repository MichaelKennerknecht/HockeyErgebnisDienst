package de.hdm.ErgebnisDienst.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ErgebnisDienst.shared.bo.*;

@RemoteServiceRelativePath("ergebnisdienst")
public interface ErgebnisDienstAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;

	public ArrayList<Matchday> getAllMatchdays() throws IllegalArgumentException;

	public ArrayList<Team> getAllTeams() throws IllegalArgumentException;

	public Team findByName(String name) throws IllegalArgumentException;

	public ArrayList<GameEntry> getAllGameEntrys(Matchday md) throws IllegalArgumentException;

	public boolean saveGameEntry(GameEntry ge) throws IllegalArgumentException;

	public boolean deleteGameEntry(GameEntry ge) throws IllegalArgumentException;

}