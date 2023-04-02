package com.bruno.apiconsultaFrete.service.exceptions;

public class InvalidResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidResourceException(String msg) {
		super(msg);
	}
}
