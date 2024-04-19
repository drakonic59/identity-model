package fr.hattane.ilias.identitymodel.config.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;

@Configuration
public class SecurityBean {

	@Autowired 
	private ResourceLoader loader;

	@Bean
	SecurityConfig securityConfigBean() {
			    
	    try {
	    
	    	Yaml yaml = new Yaml(new Constructor(SecurityConfig.class, new LoaderOptions()));
		    return yaml.load(loader.getResource("classpath:security.yaml").getInputStream());
		    
	    } catch (Exception e) { 
	    	
	    	e.printStackTrace();
	    	
	    }
		
		return new SecurityConfig();
	}
	
}
