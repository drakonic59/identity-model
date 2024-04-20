package fr.hattane.ilias.identitymodel;

import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import fr.hattane.ilias.identitymodel.model.entities.Authorization;
import fr.hattane.ilias.identitymodel.model.entities.AuthorizationId;
import fr.hattane.ilias.identitymodel.model.entities.Client;
import fr.hattane.ilias.identitymodel.model.entities.ClientSecret;
import fr.hattane.ilias.identitymodel.model.entities.Secret;
import fr.hattane.ilias.identitymodel.services.models.IClientAuthorizationService;
import fr.hattane.ilias.identitymodel.services.models.IClientSecretService;
import fr.hattane.ilias.identitymodel.services.models.IClientService;
import fr.hattane.ilias.identitymodel.services.models.ISecretService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IdentityModelApplication implements CommandLineRunner {

	@Autowired
	private IClientService clients;

	@Autowired
	private IClientSecretService clientsSecret;

	@Autowired
	private ISecretService secrets;
	
	@Autowired
	private IClientAuthorizationService clientAuthorizations;
	
	public static void main(String[] args) {
		SpringApplication.run(IdentityModelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Client c = new Client();
		c.setClientId("test");
		c.setClientName("test");
		c.setClientKey("test_key");
		c.setGroupName("Administrateur");
		Client cc = new Client();
		cc.setClientId("auth");
		cc.setClientName("auth");
		cc.setClientKey("auth_key");
		cc.setGroupName("Authentificateur");
		
		c = clients.getRepository().save(c);
		cc = clients.getRepository().save(cc);
		
		Secret s = new Secret();
		s.setSecret(Base64.encodeBase64String("test".getBytes()));
		Secret ss = new Secret();
		ss.setSecret(Base64.encodeBase64String("auth".getBytes()));
		
		s = secrets.getRepository().save(s);
		ss = secrets.getRepository().save(ss);
		
		ClientSecret clientSecret = new ClientSecret();
		clientSecret.setId(Base64.encodeBase64String((c.getId().longValue() + "").getBytes()));
		clientSecret.setReference(Base64.encodeBase64String((s.getId().longValue() + "").getBytes()));
		ClientSecret cclientSecret = new ClientSecret();
		cclientSecret.setId(Base64.encodeBase64String((c.getId().longValue() + "").getBytes()));
		cclientSecret.setReference(Base64.encodeBase64String((s.getId().longValue() + "").getBytes()));
		
		clientsSecret.getRepository().save(clientSecret);
		clientsSecret.getRepository().save(cclientSecret);
		
		AuthorizationId authId = new AuthorizationId();
		authId.setReference(Base64.encodeBase64String((c.getId().longValue() + "").getBytes()));
		authId.setTarget(Base64.encodeBase64String((cc.getId().longValue() + "").getBytes()));
		
		Authorization auth = new Authorization();
		auth.setId(authId);
		
		ArrayList<String> authorizations = new ArrayList<>();
		
		auth.setAuthorizations(authorizations);

		clientAuthorizations.getRepository().save(auth);
		
	}

}
