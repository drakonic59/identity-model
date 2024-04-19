package fr.hattane.ilias.identitymodel.config.files;

import java.util.ArrayList;

import fr.hattane.ilias.identitymodel.config.files.security.Group;

public class SecurityConfig {
 
	private ArrayList<Group> groups;

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	
}
