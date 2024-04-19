package fr.hattane.ilias.identitymodel.services;

import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;

public interface ITokenService {
	
	public BasicToken getBasicToken(String token);
	
}
