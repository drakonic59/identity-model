package fr.hattane.ilias.identitymodel.services.models.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;
import fr.hattane.ilias.identitymodel.config.files.security.Group;
import fr.hattane.ilias.identitymodel.model.entities.Client;
import fr.hattane.ilias.identitymodel.repositories.ClientRepository;
import fr.hattane.ilias.identitymodel.services.models.IClientSecretService;
import fr.hattane.ilias.identitymodel.services.models.IClientService;

@Service
public class ClientService implements IClientService {

	@Autowired
	private SecurityConfig security;
	
	@Autowired
	private ClientRepository clients;
	
	@Autowired
	private IClientSecretService references;
	
	@Override
	public ClientRepository getRepository() {
		return clients;
	}

	public boolean existsClient(String clientId) {
		return !clients.findByClientIdEquals(clientId).isEmpty();
	}

	public Group getClientGroup(String clientId, String secret) {
		if (existsClient(clientId)) {
			
			Client c = clients.findByClientIdEquals(clientId).get();

			if (!references.getSecret(c.getId()).equals(secret))
				return null;
				
			return security.getGroups().stream().filter(g -> { 
							return g.getName().equals(c.getGroupName()); 
						} ).collect(Collectors.toList()).get(0);
		}
		return null;
	}

}
