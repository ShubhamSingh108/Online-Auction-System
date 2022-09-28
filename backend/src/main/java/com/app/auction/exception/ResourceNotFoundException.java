package com.app.auction.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
String resourceName;
String fieldName;
long fieldValue;
public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
	super(String.format("%s not found with %s: %s", resourceName,fieldName,fieldValue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
}
public ResourceNotFoundException(String string, String string2) {
	// TODO Auto-generated constructor stub
}
public ResourceNotFoundException(String resourceName2, String fieldName2, String category) {
	// TODO Auto-generated constructor stub
}


}	
