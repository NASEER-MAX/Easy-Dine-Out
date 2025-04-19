package com.restaurent.exception;

import lombok.Getter;

@Getter
public class DuplicateEntityFoundException extends RuntimeException {

	private static final long serialVersionUID = 1524066063711209278L;

	String message;

	public DuplicateEntityFoundException(String message) {
		super();
		this.message = message;
	}

}
