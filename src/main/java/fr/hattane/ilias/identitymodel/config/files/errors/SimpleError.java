package fr.hattane.ilias.identitymodel.config.files.errors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.model.api.responses.ApiErrorResponse;

public class SimpleError extends Error {
	
	private String name;
	
	private int codeReturned;
	private int returnCode;
	
	private String code;
	private String message;
	private String informations;
	
	public SimpleError() {
		super(Error.TYPE_FUNCTIONAL);
	}

	public ApiErrorResponse getResponse() {
		
		ApiErrorResponse response = new ApiErrorResponse(getCode(), getMessage(), getInformations());
		
		return response;
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

	public int getCodeReturned() {
		return codeReturned;
	}

	public void setCodeReturned(int codeReturned) {
		this.codeReturned = codeReturned;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
	@Override
	public String toString() {
		try {
			return (new ObjectMapper()).writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ""; 
	}
	
}
