package fr.hattane.ilias.identitymodel.services.models.impl;

import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.model.entities.Authorization;
import fr.hattane.ilias.identitymodel.repositories.AuthorizationRepository;
import fr.hattane.ilias.identitymodel.services.models.IClientAuthorizationService;

@Service
public class ClientAuthorizationService implements IClientAuthorizationService {
	
	@Autowired
	private AuthorizationRepository references;
	
	@Override
	public AuthorizationRepository getRepository() {
		return references;
	}

	@Override
	public Authorization getAuthorizationFor(Long id, Long target) {
		
		try {
			
			String encodedId = Base64.encodeBase64String((id.longValue() + "").getBytes());
			String encodedTarget = Base64.encodeBase64String((target.longValue() + "").getBytes());

			ArrayList<Authorization> auth = (ArrayList<Authorization>) references.findByIdReference(encodedId);
			
			for (Authorization authorization : auth)
				if (authorization.getId().getTarget().equals(encodedTarget))
					return authorization;
		
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}

}
