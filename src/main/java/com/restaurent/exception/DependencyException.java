package com.restaurent.exception;

import lombok.Getter;

@Getter
public class DependencyException extends RuntimeException {

	private static final long serialVersionUID = -958072782405334328L;
	String message;

	public DependencyException(String message) {
		super();
		this.message = message;
	}

}
