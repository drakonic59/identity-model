package fr.hattane.ilias.identitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.hattane.ilias.identitymodel.config.files.ErrorsConfig;
import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;
import fr.hattane.ilias.identitymodel.config.files.errors.Errors;
import fr.hattane.ilias.identitymodel.config.files.errors.SimpleError;
import fr.hattane.ilias.identitymodel.config.files.security.Group;

@SpringBootTest
class ConfigTests {

	@Autowired 
	private ResourceLoader loader;

	@Autowired 
	private SecurityConfig securityConfig;

	@Autowired 
	private ErrorsConfig errorsConfig;
	
	@Test
	void testErrorsBean() {
		
	    assertNotNull(errorsConfig);
	    assertNotNull(errorsConfig.getTechnicals());
	    assertFalse(errorsConfig.getTechnicals().isEmpty());
	    
	    SimpleError notAuthorized = errorsConfig.getTechnicals().get(Errors.NOT_AUTHORIZED.getName());
	    
	    assertNotNull(notAuthorized);
	    assertEquals(Errors.NOT_AUTHORIZED.name(), notAuthorized.getName());

	    SimpleError empty = errorsConfig.getFunctionals().get(Errors.EMPTY_RESULT.getName());
	    
	    assertNotNull(empty);
	    assertEquals(Errors.EMPTY_RESULT.name(), empty.getName());
	    
	}
	
	@Test
	void testErrors() {
		
		Yaml yaml = new Yaml(new Constructor(ErrorsConfig.class, new LoaderOptions()));
	    
	    try {
	    	
		    InputStream inputStream = loader.getResource("classpath:errors.yaml").getInputStream();
		    ErrorsConfig errors = yaml.load(inputStream);
		    
		    assertNotNull(errors);
		    assertNotNull(errors.getTechnicals());
		    assertFalse(errors.getTechnicals().isEmpty());
		    
		    SimpleError notAuthorized = errors.getTechnicals().get(Errors.NOT_AUTHORIZED.getName());
		    
		    assertNotNull(notAuthorized);
		    assertEquals(Errors.NOT_AUTHORIZED.name(), notAuthorized.getName());
		    
		    SimpleError empty = errors.getFunctionals().get(Errors.EMPTY_RESULT.getName());
		    
		    assertNotNull(empty);
		    assertEquals(Errors.EMPTY_RESULT.name(), empty.getName());
		    
	    } catch (Exception e) { 
	    	
	    	e.printStackTrace();
		    assertNotNull(null);
	    	
	    }
	    
	}
	
	@Test
	void testSecurityBean() {
		    
	    assertNotNull(securityConfig);
	    assertNotNull(securityConfig.getGroups());
	    
	    for (Group g : securityConfig.getGroups()) {
	    	
	    	assertNotNull(g);
	
	    	assertNotNull(g.getName());
	    	assertNotNull(g.getDescription());
	    	assertNotNull(g.getLevel());
	    	
	    	assertNotNull(g.getAuthorizedActionGroups());
		    for (String ag : g.getAuthorizedActionGroups())
		    	assertNotNull(ag);
	
		    assertNotNull(g.getAuthorizedActions());
		    for (String s : g.getAuthorizedActions())
		    	assertNotNull(s);
		    
	    }
	    
	}
	
	@Test
	void testSecurity() {
		
		Yaml yaml = new Yaml(new Constructor(SecurityConfig.class, new LoaderOptions()));
	    
	    try {
	    	
		    InputStream inputStream = loader.getResource("classpath:security.yaml").getInputStream();
		    SecurityConfig security = yaml.load(inputStream);
		    
		    assertNotNull(security);
		    assertNotNull(security.getGroups());
		    
		    for (Group g : security.getGroups()) {
		    	
		    	assertNotNull(g);

		    	assertNotNull(g.getName());
		    	assertNotNull(g.getDescription());
		    	assertNotNull(g.getLevel());
		    	
		    	assertNotNull(g.getAuthorizedActionGroups());
			    for (String ag : g.getAuthorizedActionGroups())
			    	assertNotNull(ag);

			    assertNotNull(g.getAuthorizedActions());
			    for (String s : g.getAuthorizedActions())
			    	assertNotNull(s);

		    }
		    
	    } catch (Exception e) { 
	    	
	    	e.printStackTrace();
		    assertNotNull(null);
	    	
	    }
	    
	}

}
