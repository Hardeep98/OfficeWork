package com.niit.carManifacture.exception;

public class UnexpectedError extends RuntimeException {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	public UnexpectedError() {

	}

	public UnexpectedError(String mesg) {
		super(mesg);
	}

}
