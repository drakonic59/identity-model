package fr.hattane.ilias.identitymodel.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.config.security.listeners.TokenListener;
import fr.hattane.ilias.identitymodel.config.security.types.AccessTypes;
import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.config.security.types.BearerToken;
import fr.hattane.ilias.identitymodel.config.security.types.Token;
import fr.hattane.ilias.identitymodel.services.ITokenService;

@Service
public class TokenService implements ITokenService, TokenListener {

	private static final long TOKEN_DURATION_MS = 60000;
	
	public static HashMap<Long, Token> tokens = new HashMap<>();
	
	@Override
	public BasicToken getBasicToken(String token) {
		
		if (token == null || token.length() <= 6 || !token.startsWith(AccessTypes.BASIC.getStartsWith()))
			return null;
		
		String decoded[];
		try {
			decoded = (new String(Base64.decodeBase64(token.split(" ")[1]))).split(":");
		} catch (Exception e) {
			return null;
		}
		
		String client = decoded[0];
		String secret = decoded[1];
		
		return new BasicToken(client, Base64.encodeBase64String(secret.getBytes()));
	}

	@Override
	public Token generateTokenFromBasic(String authorization) {
		
		long time = new Date().getTime();
		
		Token t = new Token();
		t.setToken(Base64.encodeBase64String(authorization.getBytes()));
		t.setCreation(time);
		t.setExpiration(time + TOKEN_DURATION_MS);
		t.setListener(this);
		t.start();
		
		tokens.put(t.getId(), t);
		
		return t;
	}

	@Override
	public void expiration(Token token) {
		
		tokens.remove(token.getId());
		
	}

	@Override
	public boolean isValidToken(String token) {
		
		try {
			
			return tokens.values().stream().filter(t -> {
				return t.getToken().equals(token);
			}).count() > 0;
		
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public BearerToken getBearerToken(String authorization) {
		
		try {
			
			if (authorization == null || authorization.length() <= 8 || !authorization.startsWith(AccessTypes.BEARER.getStartsWith()))
				return null;
			
			String tokenPart = authorization.split(" ")[1];
			
			Token token = tokens.values().stream().filter(t -> {
				return t.getToken().equals(tokenPart);
			}).collect(Collectors.toList()).get(0);
			
			return new BearerToken(token, tokenPart, getBasicToken(new String(Base64.decodeBase64(tokenPart))));
		
		} catch (Exception e) {
			return null;
		}

	}

}
