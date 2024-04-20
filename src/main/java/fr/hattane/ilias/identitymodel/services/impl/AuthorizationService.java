package fr.hattane.ilias.identitymodel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.config.files.security.Group;
import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.services.IAuthorizationService;
import fr.hattane.ilias.identitymodel.services.ILoggerService;
import fr.hattane.ilias.identitymodel.services.models.IClientService;

@Service
public class AuthorizationService implements IAuthorizationService {
	
	@Autowired 
	private IClientService clients;
	
	@Autowired 
	private ILoggerService logger;
	
	@Override
	public boolean isAuthorized(BasicToken token, String[] actions) {
		
		Group group = clients.getClientGroup(token.getClientId(), token.getClientSecret());
		if (group == null)
			return false;
		
		try {
			logger.info("SECURITY", "-> Groupe détecté : " + (new ObjectMapper()).writeValueAsString(group));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		boolean ok = true;
		for (int i = 0; i < actions.length; i++) {
			
			if (i == 0 && !group.getAuthorizedActionGroups().contains(actions[0]))
				ok = false;
			else if (i == 1 && !group.getAuthorizedActions().contains(actions[1]))
				ok = false;
			
		}
		
		return ok;
	}

}
