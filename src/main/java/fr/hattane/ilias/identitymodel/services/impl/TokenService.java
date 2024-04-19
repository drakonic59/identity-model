package fr.hattane.ilias.identitymodel.services.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.services.ITokenService;

@Service
public class TokenService implements ITokenService {

	@Override
	public BasicToken getBasicToken(String token) {
		
		if (token == null || token.length() <= 6 || !token.startsWith("Bearer "))
			return null;
		
		String decoded[] = (new String(Base64.decodeBase64(token.split(" ")[1]))).split(":");
		
		String client = decoded[0];
		String secret = decoded[1];
		
		return new BasicToken(client, secret);
	}

}
