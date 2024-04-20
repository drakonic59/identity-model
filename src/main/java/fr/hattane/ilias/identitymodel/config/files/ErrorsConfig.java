package fr.hattane.ilias.identitymodel.config.files;

import java.util.Map;

import fr.hattane.ilias.identitymodel.config.files.errors.SimpleError;

public class ErrorsConfig {
 
	private Map<String, SimpleError> technicals;
	private Map<String, SimpleError> functionals;

	public Map<String, SimpleError> getFunctionals() {
		return functionals;
	}

	public void setFunctionals(Map<String, SimpleError> functionals) {
		this.functionals = functionals;
	}

	public Map<String, SimpleError> getTechnicals() {
		return technicals;
	}

	public void setTechnicals(Map<String, SimpleError> technicals) {
		this.technicals = technicals;
	}
	
}
