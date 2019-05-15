package com.backend.test.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Exception in case that the service returns an unexpected error
 * 
 * @author Vashete
 * @version 20190515
 */
@RequiredArgsConstructor
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226297162414148863L;
	@Getter
	@Setter
	private @NonNull String message;

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

}
