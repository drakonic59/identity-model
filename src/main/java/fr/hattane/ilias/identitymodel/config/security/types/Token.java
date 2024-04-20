package fr.hattane.ilias.identitymodel.config.security.types;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.config.security.listeners.TokenListener;

public class Token extends Thread {
	
	public static long ID = 0;
	
	private final long id;
	
	private String token;
	
	private long creation;
	private long expiration;
	
	private TokenListener listener;
	
	public Token() {
		this.id = ++ID;
	}
	
	public void run() {
		while ((new Date()).getTime() < expiration)
			try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
		listener.expiration(this);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
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

	public long getId() {
		return id;
	}

	public long getCreation() {
		return creation;
	}

	public void setCreation(long creation) {
		this.creation = creation;
	}

	public TokenListener getListener() {
		return listener;
	}

	public void setListener(TokenListener listener) {
		this.listener = listener;
	}
	
}
