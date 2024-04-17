package fr.hattane.ilias.identitymodel.config.files;

import java.util.Map;

import fr.hattane.ilias.identitymodel.config.files.errors.FunctionalError;

public class ErrorsConfig {
 
	private Map<String, FunctionalError> functionals;

	public ErrorsConfig() {
		super();
	}

	public Map<String, FunctionalError> getFunctionals() {
		return functionals;
	}

	public void setFunctionals(Map<String, FunctionalError> functionals) {
		this.functionals = functionals;
	}
	
}
