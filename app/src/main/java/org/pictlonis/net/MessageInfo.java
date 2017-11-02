package org.pictlonis.net;

import java.net.Socket;

/**
 * Created by bigfoot on 02/11/17.
 */

public class MessageInfo {
	private String message;
	private Socket socket;

	public MessageInfo(String message, Socket from) {
		this.message = message;
		this.socket = from;
	}

	public Socket getFrom() {
		return socket;
	}

	public String getMessage() {
		return message;
	}
}
