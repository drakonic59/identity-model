package fr.hattane.ilias.identitymodel.config.files.security;

import java.util.ArrayList;

public class Group {
	
	private String name;
	private String description;
	
	private byte level;
	
	private ArrayList<String> authorizedActionGroups;
	private ArrayList<String> authorizedActions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public ArrayList<String> getAuthorizedActionGroups() {
		return authorizedActionGroups;
	}

	public void setAuthorizedActionGroups(ArrayList<String> authorizedActionGroups) {
		this.authorizedActionGroups = authorizedActionGroups;
	}

	public ArrayList<String> getAuthorizedActions() {
		return authorizedActions;
	}

	public void setAuthorizedActions(ArrayList<String> authorizedActions) {
		this.authorizedActions = authorizedActions;
	}
	
}
