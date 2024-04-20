package fr.hattane.ilias.identitymodel.config.security.types;

public enum AccessTypes {
	
	BASIC("Basic "),
	BEARER("Bearer ");
	
	private String startsWith;

	private AccessTypes(String startsWith) {
		this.startsWith = startsWith;
	}

	public String getStartsWith() {
		return startsWith;
	}

	public void setStartsWith(String startsWith) {
		this.startsWith = startsWith;
	}
	
}
