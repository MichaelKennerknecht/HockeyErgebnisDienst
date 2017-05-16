package de.hdm.ErgebnisDienst.client.gui;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.ErgebnisDienst.shared.LoginInfo;

/**
 * Das synchrone Gegenstueck des Interface {@link LoginServiceAsync}. Es erfolgt
 * hier keine weitere Dokumentation. Fuer weitere Informationen: siehe die
 * Implementierung des Interface {@link LoginServiceImpl}
 * 
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public LoginInfo login(String requestUri) throws IllegalArgumentException;
}
