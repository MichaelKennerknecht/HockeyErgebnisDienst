package de.hdm.ErgebnisDienst.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ErgebnisDienst.client.ClientsideSettings;
import de.hdm.ErgebnisDienst.shared.ErgebnisDienstAdministrationAsync;
import de.hdm.ErgebnisDienst.shared.bo.User;

public class Impressum extends Update{

	/**
	 * Die AdministrationService ermoeglicht die asynchrone Kommunikation mit der
	 * Applikationslogik.
	 */
	private ErgebnisDienstAdministrationAsync adminService = ClientsideSettings.getAdministration();
	
	User user = new User();

	protected String getHeadlineText(){
		return "";
	}
	/**
	 * Erstellung aller Panels
	 */
	
	HorizontalPanel headlinePanel = new HorizontalPanel();
	VerticalPanel impressumPanel = new VerticalPanel();
	VerticalPanel textPanel = new VerticalPanel();
	
	/**
	 * Erstellung aller Widgets
	 */
	
	
	Label mainheadline = new Label("Impressum");
    Label logoImpressum = new Label("Hockey Ergebnisdienst");
    Label adress = new Label("Hochschule der Medien, Stuttgart");
    Label names1 = new Label("Michael Kennerknecht, 27975"); 
	
	
	protected void run() {
		this.append("");
		
		
//		logoImpressum.setStyleName("logoImpressum");
//		impressumPanel.setStyleName("detailsPanel");
//		headlinePanel.setStyleName("headlinePanel");
//		
		headlinePanel.add(mainheadline);
		textPanel.add(logoImpressum);
		textPanel.add(adress);
		textPanel.add(names1);
		impressumPanel.add(textPanel);
		
		RootPanel.get("Extra").add(headlinePanel);
		RootPanel.get("Extra").add(impressumPanel);
		
    }
}
