package fr.hattane.ilias.identitymodel.config.files.errors;

public enum Errors {
	
	NOT_AUTHORIZED("not_authorized", Error.TYPE_TECHNICAL),
	EMPTY_RESULT("empty", Error.TYPE_FUNCTIONAL);
	
	private String name;

	private byte type;

	private Errors(String name, byte type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

}
