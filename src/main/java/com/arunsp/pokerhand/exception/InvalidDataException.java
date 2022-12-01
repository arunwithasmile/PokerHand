package com.arunsp.pokerhand.exception;

public class InvalidDataException extends Exception {

	private static final long serialVersionUID = -9049807080438529807L;

	private long lineNo;

	public InvalidDataException() {
	}

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDataException(Throwable cause, int lineNo) {
		super(cause);
		this.lineNo = lineNo;
	}

	public long getLineNo() {
		return lineNo;
	}
}
