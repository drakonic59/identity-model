package fr.hattane.ilias.identitymodel.services.models;

import fr.hattane.ilias.identitymodel.config.files.security.Group;
import fr.hattane.ilias.identitymodel.repositories.ClientRepository;

public interface IClientService {
	
	public ClientRepository getRepository();
	
	public Group getClientGroup(String clientId, String secret);
	
}
