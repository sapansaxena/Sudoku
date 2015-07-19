package com.sudoku.impl.rest.validation.error.handler;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public final class ValidationError {
 
 
 
private String invalidValue;
 
private String message;
 
private String path;
 
 
 
public ValidationError() {
 
}
 
 
 
public ValidationError(final String invalidValue, final String message, final String path) {
 
this.invalidValue = invalidValue;
 
this.message = message;
 
this.path = path;
 
}



public String getInvalidValue() {
	return invalidValue;
}



public void setInvalidValue(String invalidValue) {
	this.invalidValue = invalidValue;
}



public String getMessage() {
	return message;
}



public void setMessage(String message) {
	this.message = message;
}


public String getPath() {
	return path;
}



public void setPath(String path) {
	this.path = path;
}
 
 
 
// Getters and Setters...
 
}