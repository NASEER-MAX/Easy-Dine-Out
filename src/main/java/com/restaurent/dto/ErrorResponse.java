package com.restaurent.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

	int statusCode;
	String message;
	long timestamp;

	public ErrorResponse(String message, int statusCode) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}

}
