package de.hdm.ErgebnisDienst.shared.bo;

import java.io.Serializable;

public class Matchday {
	
	private int md_Id;
	private String name;
	private int md_count;

	
	public Matchday(String string) {
		this.name  = string;
	}

	public int getMdId() {
		return md_Id;
	}

	public void setMdId(int md_Id) {
		this.md_Id = md_Id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	public int getMdCount() {
		return md_count;
	}
	public void setMdCount(int md_count) {
		this.md_count = md_count;
	}
	
}
