package de.hdm.ErgebnisDienst.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hdm.Application.shared.bo.Note;
import com.hdm.Application.shared.bo.Notebook;

import de.hdm.ErgebnisDienst.shared.bo.*;

@RemoteServiceRelativePath("xxx")
public interface ErgebnisDienstAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;

	
	public List<Team> getAllTeams() throws IllegalArgumentException;


	//public AppUser getCurrentUser() throws IllegalArgumentException;

	

}