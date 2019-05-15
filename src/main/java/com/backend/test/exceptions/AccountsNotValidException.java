package com.backend.test.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Exception in case that an account is not valid
 * 
 * @author Vashete
 * @version 20190515
 */
@RequiredArgsConstructor
public class AccountsNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226297162414148863L;
	@Getter
	@Setter
	private @NonNull String message;

	public AccountsNotValidException(String message, Throwable e) {
		super(message, e);
	}

}
