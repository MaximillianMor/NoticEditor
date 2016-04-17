package com.temporaryteam.noticeditor.io.format;

/**
 * @author Max Balushkin
 */
public class FormatException extends Exception {
	
	public FormatException() { }
	
	public FormatException(String message) {
		super(message);
	}
	
	public FormatException(Throwable cause) {
		super(cause);
	}
	
	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
