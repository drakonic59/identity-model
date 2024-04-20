package fr.hattane.ilias.identitymodel.config.security.listeners;

import fr.hattane.ilias.identitymodel.config.security.types.Token;

public interface TokenListener {
	
	public void expiration(Token token);
	
}
