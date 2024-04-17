package fr.hattane.ilias.identitymodel.config.files.errors;

public abstract class Error {
	
	public static final byte TYPE_FUNCTIONAL = 0;
	
	private byte type;
	
	public Error(byte type) {
		this.type = type;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
	
}
