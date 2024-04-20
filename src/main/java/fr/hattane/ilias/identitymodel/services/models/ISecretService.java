package fr.hattane.ilias.identitymodel.services.models;

import fr.hattane.ilias.identitymodel.repositories.SecretRepository;

public interface ISecretService {
	
	public SecretRepository getRepository();
	
}
