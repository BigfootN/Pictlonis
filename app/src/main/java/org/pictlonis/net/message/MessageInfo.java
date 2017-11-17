package org.pictlonis.net.message;

import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 02/11/17.
 */

public class MessageInfo<T> {
	private SocketChannel socket;
	private PictlonisMessageType type;
	private T value;

	public enum PictlonisMessageType {
		NB_CONNECTED,
		NB_PLAYERS,
		POINT_NEW,
		POINT_MOVE,
		POINT_LAST
	};

	public MessageInfo(SocketChannel from, PictlonisMessageType type, T value) {
		this.socket = from;
		this.type = type;
		this.value = value;
	}

	public PictlonisMessageType getMessageType() {
		return type;
	}

	public SocketChannel getFrom() {
		return socket;
	}

	public T getValue() {
		return value;
	}
}
