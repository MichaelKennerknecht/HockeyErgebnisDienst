package de.hdm.ErgebnisDienst.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hdm.Application.shared.bo.*;

@RemoteServiceRelativePath("noteadministration")
public interface ErgebnisDienstAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;

	public AppUser getCurrentUser() throws IllegalArgumentException;

	public AppUser getUserByMail(String mail) throws IllegalArgumentException;
	
	public AppUser getUserByID(int userID) throws IllegalArgumentException;

	public AppUser createUser(AppUser u) throws IllegalArgumentException;

	public AppUser editUser(AppUser u) throws IllegalArgumentException;

	public void deleteUser(AppUser u) throws IllegalArgumentException;

	public Notebook createNotebook(Notebook nb) throws IllegalArgumentException;

	public Notebook editNotebook(Notebook nb) throws IllegalArgumentException;

	public void deleteNotebook(Notebook nb) throws IllegalArgumentException;

	public Note createNote(Note n) throws IllegalArgumentException;

	public Note editNote(Note n) throws IllegalArgumentException;

	public void deleteNote(Note n) throws IllegalArgumentException;

	public void createPermissions(ArrayList<Permission> p) throws IllegalArgumentException;

	public ArrayList<Permission> editPermissions(ArrayList<Permission> p) throws IllegalArgumentException;

	public void deletePermission(Permission p) throws IllegalArgumentException;

	public void createDuedate(DueDate dd) throws IllegalArgumentException;

	public void editDuedate(DueDate dd) throws IllegalArgumentException;

	public void deleteDuedate(DueDate dd) throws IllegalArgumentException;

	public ArrayList<AppUser> searchForUser(String userName) throws IllegalArgumentException;

	public ArrayList<Notebook> searchForNotebook(String title) throws IllegalArgumentException;

	public ArrayList<Note> searchForNote(String title) throws IllegalArgumentException;

	public ArrayList<Note> searchForNoteByDD(Date duedate) throws IllegalArgumentException;
	
	public Notebook getNotebookByID(int nbID) throws IllegalArgumentException;

	public ArrayList<Notebook> getNotebooksOfUser(AppUser user) throws IllegalArgumentException;

	public ArrayList<Note> getNotesOfNotebookTitle(String nbTitle, AppUser u) throws IllegalArgumentException;
	
	public ArrayList<Note> getNotesOfNotebook(Notebook nb) throws IllegalArgumentException;

	public ArrayList<Permission> getOwnedNotebookPermissions(AppUser user) throws IllegalArgumentException;
	
	public ArrayList<Notebook> getOwnedNotebooks(AppUser user) throws IllegalArgumentException;
	
	public ArrayList<Permission> getPermissions(int nID, int nbID) throws IllegalArgumentException;
	
	public Permission getPermission(int uID, int nbID, int nID) throws IllegalArgumentException;

	public DueDate getDuedate(int nID) throws IllegalArgumentException;
	
	public AppUser searchUserByMail(String mail) throws IllegalArgumentException;

	public AppUser getUserByEmail(String email) throws IllegalArgumentException;
	
	public ArrayList<Note> findAll() throws IllegalArgumentException;

}