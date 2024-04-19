package fr.hattane.ilias.identitymodel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;
import fr.hattane.ilias.identitymodel.config.files.security.Group;
import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.services.IAuthorizationService;

@Service
public class AuthorizationService implements IAuthorizationService {

	@Autowired 
	private SecurityConfig securityConfig;
	
	@Override
	public boolean isAuthorized(BasicToken token, String[] actions) {
		
		Group group = securityConfig.getGroups().get(0);
		
		try {
			System.out.println((new ObjectMapper()).writeValueAsString(group));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
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
