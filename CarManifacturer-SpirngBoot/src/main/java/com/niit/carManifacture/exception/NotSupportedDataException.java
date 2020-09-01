package com.niit.carManifacture.exception;

public class NotSupportedDataException extends RuntimeException {
	
//	private static final long serialVersionUID = 1L;

	public NotSupportedDataException(String mesg) {
			super(mesg);
	}
	public NotSupportedDataException() {
	}

}
