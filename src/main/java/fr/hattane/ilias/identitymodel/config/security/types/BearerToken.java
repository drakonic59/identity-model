package fr.hattane.ilias.identitymodel.config.security.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BearerToken {
	
	private String token;
	
	private Token run;
	private BasicToken identity;
	
	public BearerToken(Token run, String token, BasicToken identity) {
		super();
		this.run = run;
		this.token = token;
		this.identity = identity;
	}

	public BearerToken() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BasicToken getIdentity() {
		return identity;
	}

	public void setIdentity(BasicToken identity) {
		this.identity = identity;
	}
	
	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Token getRun() {
		return run;
	}

	public void setRun(Token run) {
		this.run = run;
	}
	
}
