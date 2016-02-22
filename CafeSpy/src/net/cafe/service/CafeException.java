package net.cafe.service;

import java.util.Stack;

public class CafeException extends Exception {
	private Stack<String> messages = new Stack<String>();

	public CafeException() {
	}

	public CafeException(String message) {
		super(message);
		add(message);
	}

	public CafeException(Throwable cause) {
		super(cause);
	}

	public CafeException(String message, Throwable cause) {
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
