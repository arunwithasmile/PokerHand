/**
 * 
 */
package com.arunsp.pokerhand.exception;

/**
 * @author Arun S P
 *
 */
public class InvalidDealException extends Exception {

	private static final long serialVersionUID = 868514771599172907L;

	public InvalidDealException() {
	}

	public InvalidDealException(String message) {
		super(message);
	}

	public InvalidDealException(Throwable cause) {
		super(cause);
	}

	public InvalidDealException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDealException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
