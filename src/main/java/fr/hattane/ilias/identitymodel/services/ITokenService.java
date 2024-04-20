package fr.hattane.ilias.identitymodel.services;

import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.config.security.types.BearerToken;
import fr.hattane.ilias.identitymodel.config.security.types.Token;

public interface ITokenService {
	
	public BasicToken getBasicToken(String token);

	public Token generateTokenFromBasic(String authorization);

	public boolean isValidToken(String authorization);
	public BearerToken getBearerToken(String authorization);
	
}
