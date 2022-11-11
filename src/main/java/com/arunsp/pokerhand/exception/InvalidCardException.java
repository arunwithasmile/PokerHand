/**
 * 
 */
package com.arunsp.pokerhand.exception;

/**
 * @author Arun S P
 *
 */
public class InvalidCardException extends Exception {

	private static final long serialVersionUID = 4484151458705410224L;

	public InvalidCardException() {
	}

	public InvalidCardException(String message) {
		super(message);
	}

	public InvalidCardException(Throwable cause) {
		super(cause);
	}

	public InvalidCardException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
