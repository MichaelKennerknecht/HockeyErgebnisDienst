package de.hdm.ErgebnisDienst.shared.bo;

import java.io.Serializable;

public class Matchday {
	
	private int mdId;
	private String name;
	
	public int getMdId() {
		return mdId;
	}

	public void setMdId(int mdId) {
		this.mdId = mdId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}
}
