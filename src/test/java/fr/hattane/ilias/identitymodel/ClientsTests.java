package fr.hattane.ilias.identitymodel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;
import fr.hattane.ilias.identitymodel.config.files.security.Group;
import fr.hattane.ilias.identitymodel.model.entities.Client;
import fr.hattane.ilias.identitymodel.services.models.IClientService;

@SpringBootTest
class ClientsTests {

	@Autowired 
	private IClientService clients;

	@Autowired 
	private SecurityConfig security;
	
	@Test
	void testClientsGroup() {
		
		ArrayList<Client> list = (ArrayList<Client>) clients.getRepository().findAll();
		
	    assertNotNull(list);
	    
	    if (!list.isEmpty()) {
	    	
	    	for (Client c : list) {
	    		
	    		assertNotNull(c);
	    		assertNotNull(c.getId());
	    		assertNotNull(c.getClientId());
	    		assertNotNull(c.getClientKey());
	    		assertNotNull(c.getClientName());
	    		assertNotNull(c.getGroupName());
	    		
	    		assertTrue(security.getGroups().stream().filter(group -> {
	    			return group.getName().equals(c.getGroupName());
	    		}).count() > 0);
	    		
	    		Group g = security.getGroups().stream().filter(group -> {
	    			return group.getName().equals(c.getGroupName());
	    		}).collect(Collectors.toList()).get(0);
	    		
	    		assertNotNull(g);
	    		
	    	}
	    	
	    }
	    
	}

}
