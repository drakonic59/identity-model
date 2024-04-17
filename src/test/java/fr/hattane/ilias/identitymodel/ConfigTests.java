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
import fr.hattane.ilias.identitymodel.config.files.errors.Errors;
import fr.hattane.ilias.identitymodel.config.files.errors.FunctionalError;

@SpringBootTest
class ConfigTests {

	@Autowired 
	private ResourceLoader loader;
	
	@Test
	void testErrors() {
		
		Yaml yaml = new Yaml(new Constructor(ErrorsConfig.class, new LoaderOptions()));
	    
	    try {
	    	
		    InputStream inputStream = loader.getResource("classpath:errors.yaml").getInputStream();
		    ErrorsConfig errors = yaml.load(inputStream);
		    
		    assertNotNull(errors);
		    assertNotNull(errors.getFunctionals());
		    assertFalse(errors.getFunctionals().isEmpty());
		    
		    FunctionalError notfound = errors.getFunctionals().get(Errors.NOT_FOUND.getName());
		    
		    assertNotNull(notfound);
		    assertEquals(Errors.NOT_FOUND.name(), notfound.getName());
		    
	    } catch (Exception e) { 
	    	
	    	e.printStackTrace();
		    assertNotNull(null);
	    	
	    }
		
	    
	    
	}

}
