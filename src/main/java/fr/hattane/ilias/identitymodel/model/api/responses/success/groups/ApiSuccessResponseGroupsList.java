package fr.hattane.ilias.identitymodel.model.api.responses.success.groups;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.config.files.security.Group;

public class ApiSuccessResponseGroupsList {
	
	private ArrayList<Group> groups;

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	
	@Override
	public String toString() {
		try {
			return (new ObjectMapper()).writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ""; 
	}
	
}
