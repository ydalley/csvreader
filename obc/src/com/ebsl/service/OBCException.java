package com.ebsl.service;

import java.util.Stack;

public class OBCException extends Exception {
	private Stack<String> messages = new Stack<String>();

	public OBCException() {
	}

	public OBCException(String message) {
		super(message);
		add(message);
	}

	public OBCException(Throwable cause) {
		super(cause);
	}

	public OBCException(String message, Throwable cause) {
		super(message, cause);
		add(message);
	}
	
	public void add(String message){
		messages.push(message);
	}

	public Stack<String> getMessages() {
		return messages;
	}

}
