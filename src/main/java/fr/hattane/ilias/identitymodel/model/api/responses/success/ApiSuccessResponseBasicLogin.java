package fr.hattane.ilias.identitymodel.model.api.responses.success;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiSuccessResponseBasicLogin {
	
	private String accessType;

	private String accessToken;
	private long expirationTime;
	
	public String getAccessType() {
		return accessType;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public long getExpirationTime() {
		return expirationTime;
	}
	
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
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
