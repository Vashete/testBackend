package com.backend.test.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Exception in case that an account does not have balance to do the operation
 * 
 * @author Vashete
 * @version 20190515
 */
@RequiredArgsConstructor
public class NoBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226297162414148863L;
	@Getter
	@Setter
	private @NonNull String message;

	public NoBalanceException(String message, Throwable e) {
		super(message, e);
	}

}
