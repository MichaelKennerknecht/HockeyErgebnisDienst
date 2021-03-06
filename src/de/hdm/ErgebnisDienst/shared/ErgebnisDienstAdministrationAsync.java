package de.hdm.ErgebnisDienst.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.ErgebnisDienst.shared.bo.*;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministration;

/**
 * Das asynchrone Gegenstueck des Interface {@link ErgebnisDienstAdministration} fuer
 * RPCs, um die Geschaeftsobjekte zu verwalten. Es wird semiautomatisch durch
 * das Google Plugin erstellt und gepflegt. Daher erfolgt hier keine weitere
 * Dokumentation. Fuer weitere Informationen: siehe das synchrone Interface
 * {@link ErgebnisDienstAdministration}
 * 
 */

public interface ErgebnisDienstAdministrationAsync {

	public void init(AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	
	public void findByName(String name, AsyncCallback<Team> callback);

	void getAllTeams(AsyncCallback<ArrayList<Team>> callback);

	void getAllMatchdays(AsyncCallback<ArrayList<Matchday>> callback);
		
	void getAllGameEntrys(Matchday md, AsyncCallback<ArrayList<GameEntry>> callback);
	
	void saveGameEntry(GameEntry ge, AsyncCallback<Boolean> callback);
	
	void deleteGameEntry(GameEntry ge, AsyncCallback<Boolean> callback);
	
}