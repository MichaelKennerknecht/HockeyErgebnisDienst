package de.hdm.ErgebnisDienst.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hdm.Application.shared.bo.*;
import com.hdm.Application.shared.NoteAdministration;

/**
 * Das asynchrone Gegenstueck des Interface {@link ErgebnisDienstAdministration} fuer
 * RPCs, um die Geschaeftsobjekte zu verwalten. Es wird semiautomatisch durch
 * das Google Plugin erstellt und gepflegt. Daher erfolgt hier keine weitere
 * Dokumentation. Fuer weitere Informationen: siehe das synchrone Interface
 * {@link ErgebnisDienstAdministration}
 * 
 * @author Lorena Esposito
 */

public interface ErgebnisDienstAdministrationAsync {

	public void init(AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void getCurrentUser(AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;

	public void getUserByMail(String mail, AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;
	
	public void getUserByID(int userID, AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;

	public void createUser(AppUser u, AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;

	public void editUser(AppUser u, AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;

	public void deleteUser(AppUser u, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void createNotebook(Notebook nb, AsyncCallback<Notebook> callback)
			throws IllegalArgumentException;

	public void editNotebook(Notebook nb, AsyncCallback<Notebook> callback)
			throws IllegalArgumentException;

	public void deleteNotebook(Notebook nb, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void createNote(Note n, AsyncCallback<Note> callback)
			throws IllegalArgumentException;

	public void editNote(Note n, AsyncCallback<Note> callback)
			throws IllegalArgumentException;

	public void deleteNote(Note n, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void createPermissions(ArrayList<Permission> p, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void editPermissions(ArrayList<Permission> p, AsyncCallback<ArrayList<Permission>> callback)
			throws IllegalArgumentException;

	public void deletePermission(Permission p, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void createDuedate(DueDate dd, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void editDuedate(DueDate dd, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void deleteDuedate(DueDate dd, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	public void searchForUser(String userName,
			AsyncCallback<ArrayList<AppUser>> callback)
			throws IllegalArgumentException;

	public void searchForNotebook(String title,
			AsyncCallback<ArrayList<Notebook>> callback)
			throws IllegalArgumentException;

	public void searchForNote(String title,
			AsyncCallback<ArrayList<Note>> callback)
			throws IllegalArgumentException;

	public void searchForNoteByDD(Date duedate,
			AsyncCallback<ArrayList<Note>> callback)
			throws IllegalArgumentException;
	
	public void getNotebookByID(int nbID,
			AsyncCallback<Notebook> callback)
			throws IllegalArgumentException;

	public void getNotebooksOfUser(AppUser user,
			AsyncCallback<ArrayList<Notebook>> callback)
			throws IllegalArgumentException;

	public void getNotesOfNotebookTitle(String nbTitle, AppUser u,
			AsyncCallback<ArrayList<Note>> callback)
			throws IllegalArgumentException;
	
	public void getNotesOfNotebook(Notebook nb,
			AsyncCallback<ArrayList<Note>> callback)
			throws IllegalArgumentException;

	public void getOwnedNotebookPermissions(AppUser user,
			AsyncCallback<ArrayList<Permission>> callback)
			throws IllegalArgumentException;
	
	public void getOwnedNotebooks(AppUser user,
			AsyncCallback<ArrayList<Notebook>> callback)
			throws IllegalArgumentException;
	
	public void getPermissions(int nID, int nbID,
			AsyncCallback<ArrayList<Permission>> callback)
			throws IllegalArgumentException;
	
	public void getPermission(int uID, int nbID, int nID,
			AsyncCallback<Permission> callback)
			throws IllegalArgumentException;
	
	public void getDuedate(int nID, AsyncCallback<DueDate> callback)
			throws IllegalArgumentException;
	
	public void searchUserByMail(String mail,
			AsyncCallback<AppUser> callback)
			throws IllegalArgumentException;

	public void getUserByEmail(String email, AsyncCallback<AppUser> callback) throws IllegalArgumentException;

	public void findAll(AsyncCallback<ArrayList<Note>> callback) throws IllegalArgumentException;
	
}
