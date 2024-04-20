package fr.hattane.ilias.identitymodel.model.api.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.hattane.ilias.identitymodel.config.files.errors.SimpleError;

public class ApiErrorResponse {
	
	private ApiErrorResponseData errorResponse;
	
	public ApiErrorResponse(String code, String message, String informations) {
		
		ApiErrorResponseData data = new ApiErrorResponseData();
		data.setCode(code);
		data.setMessage(message);
		data.setInformations(informations);
		
		setErrorResponse(data);
		
	}
	
	public ApiErrorResponse(SimpleError error) {
		
		ApiErrorResponseData data = new ApiErrorResponseData();
		data.setCode(error.getCode());
		data.setMessage(error.getMessage());
		data.setInformations(error.getInformations());
		
		setErrorResponse(data);
		
	}
	
	public ApiErrorResponseData getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ApiErrorResponseData errorResponse) {
		this.errorResponse = errorResponse;
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

	public class ApiErrorResponseData {
		
		private String code;
		private String message;
		private String informations;
		
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

}
