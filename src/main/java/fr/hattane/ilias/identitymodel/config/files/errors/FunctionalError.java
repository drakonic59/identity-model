package fr.hattane.ilias.identitymodel.config.files.errors;

public class FunctionalError extends Error {
	
	private String name;
	
	private String code;
	private String message;
	private String informations;
	
	public FunctionalError() {
		super(Error.TYPE_FUNCTIONAL);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}
	
}
