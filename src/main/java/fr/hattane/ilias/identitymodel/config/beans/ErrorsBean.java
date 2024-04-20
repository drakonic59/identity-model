package fr.hattane.ilias.identitymodel.config.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.hattane.ilias.identitymodel.config.files.ErrorsConfig;
import fr.hattane.ilias.identitymodel.config.files.errors.Error;
import fr.hattane.ilias.identitymodel.config.files.errors.SimpleError;

@Configuration
public class ErrorsBean {

	@Autowired 
	private ResourceLoader loader;

	@Bean
	ErrorsConfig errorsConfigBean() {
			    
	    try {
	    
	    	Yaml yaml = new Yaml(new Constructor(ErrorsConfig.class, new LoaderOptions()));
		    
	    	ErrorsConfig config = yaml.load(loader.getResource("classpath:errors.yaml").getInputStream());
		    
	    	if (config.getFunctionals() != null && !config.getFunctionals().isEmpty())
	    		for (SimpleError error : config.getFunctionals().values())
	    			error.setType(Error.TYPE_FUNCTIONAL);
	    	
	    	if (config.getFunctionals() != null && !config.getTechnicals().isEmpty())
	    		for (SimpleError error : config.getTechnicals().values())
	    			error.setType(Error.TYPE_TECHNICAL);
	    	
	    	return config;
	    	
	    } catch (Exception e) { 
	    	
	    	e.printStackTrace();
	    	
	    }
		
		return new ErrorsConfig();
	}
	
}
