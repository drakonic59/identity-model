package fr.hattane.ilias.identitymodel.services.models;

import fr.hattane.ilias.identitymodel.repositories.ClientSecretRepository;

public interface IClientSecretService {
	
	public ClientSecretRepository getRepository();
	
	public String getSecret(Long id);
	
}
