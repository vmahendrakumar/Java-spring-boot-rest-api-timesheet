package com.thinkfect.timesheet.endpoints;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Generic Response Builder adds custom Headers to outgoing response and wraps given Resource in a ResponseEntity
 */
@Component
public class ResponseBuilder {

	public <T> ResponseEntity<T> buildResponse(T response, HttpStatus status) {
		return new ResponseEntity<T>(response, buildHeaders(), status);
	}

	private HttpHeaders buildHeaders() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("correlation-id", "12345");

		return responseHeaders;
	}
}
