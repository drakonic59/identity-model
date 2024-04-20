package fr.hattane.ilias.identitymodel.services.models;

import fr.hattane.ilias.identitymodel.model.entities.Authorization;
import fr.hattane.ilias.identitymodel.repositories.AuthorizationRepository;

public interface IClientAuthorizationService {
	
	public AuthorizationRepository getRepository();
	
	public Authorization getAuthorizationFor(Long id, Long reference);
	
}
