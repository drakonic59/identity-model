package fr.hattane.ilias.identitymodel.services;

import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;

public interface IAuthorizationService {
	
	public boolean isAuthorized(BasicToken token, String[] actions);
	
}
