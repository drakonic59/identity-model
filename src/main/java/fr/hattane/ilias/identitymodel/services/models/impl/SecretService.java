package fr.hattane.ilias.identitymodel.services.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.repositories.SecretRepository;
import fr.hattane.ilias.identitymodel.services.models.ISecretService;

@Service
public class SecretService implements ISecretService {
	
	@Autowired
	private SecretRepository secrets;
	
	@Override
	public SecretRepository getRepository() {
		return secrets;
	}

}
