package com.org.concordia.photoapi.model;

public class ResponseForUserCreation {

	private String type;
	private String message;
	private String errorStatusCode;
	
	public ResponseForUserCreation() {}

	public ResponseForUserCreation(String type, String message) {
		this.type = type;
		this.message = message;
//		this.errorStatusCode=errorStatusCode;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
//	public String getErrorStatusCode() {
//		return errorStatusCode;
//	}
//
//	public void setErrorStatusCode(String errorStatusCode) {
//		this.errorStatusCode = errorStatusCode;
//	}

}
