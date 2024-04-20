package fr.hattane.ilias.identitymodel.services.models.impl;

import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.model.entities.ClientSecret;
import fr.hattane.ilias.identitymodel.model.entities.Secret;
import fr.hattane.ilias.identitymodel.repositories.ClientSecretRepository;
import fr.hattane.ilias.identitymodel.services.models.IClientSecretService;
import fr.hattane.ilias.identitymodel.services.models.ISecretService;

@Service
public class ClientSecretService implements IClientSecretService {
	
	@Autowired
	private ClientSecretRepository references;

	@Autowired
	private ISecretService secrets;
	
	@Override
	public ClientSecretRepository getRepository() {
		return references;
	}

	@Override
	public String getSecret(Long id) {
		
		String encoded = Base64.encodeBase64String((id.longValue() + "").getBytes());
		
		Optional<ClientSecret> reference = references.findById(encoded);
		if (reference.isEmpty())
			return null;
		
		Optional<Secret> secret = secrets.getRepository().findById(Long.parseLong(new String(Base64.decodeBase64(reference.get().getReference()))));
		if (secret.isEmpty())
			return null;
		
		return secret.get().getSecret();
		
	}

}
