package org.pictlonis.net.message;

import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 02/11/17.
 */

public class MessageInfo {
	private String message;
	private SocketChannel socket;

	public MessageInfo(String message, SocketChannel from) {
		this.message = message;
		this.socket = from;
	}

	public SocketChannel getFrom() {
		return socket;
	}

	public String getMessage() {
		return message;
	}
}
