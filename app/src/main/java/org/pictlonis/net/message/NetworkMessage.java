package org.pictlonis.net.message;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bigfoot on 02/11/17.
 */

public class NetworkMessage {
	private static boolean isLastMessage(String str) {
		return str.endsWith(System.lineSeparator());
	}

	public static void sendMessage(String message, Socket socket) throws Exception {
		OutputStream os;
		OutputStreamWriter osw;
		BufferedWriter bw;

		os = socket.getOutputStream();
		osw = new OutputStreamWriter(os);
		bw = new BufferedWriter(osw);

		bw.write(message);
		bw.flush();
	}

	public static void sendMessage(String message, SocketChannel socket) throws Exception {
		ByteBuffer buffer;
		String msgToSend;

		msgToSend = message + System.lineSeparator();
		buffer = ByteBuffer.wrap(msgToSend.getBytes());
		socket.write(buffer);
	}

	public static void sendMessage(String message, ArrayList<SocketChannel> sockets) throws Exception{
		Iterator<SocketChannel> sock;

		sock = sockets.iterator();
		while (sock.hasNext()) {
			sendMessage(message, sock.next());
		}
	}

	public static String readMessage(SocketChannel socket) throws IOException {
		String ret;
		ByteBuffer buffer;

		ret = "";
		do {
			buffer = ByteBuffer.allocate(1);
			socket.read(buffer);

			ret.concat(new String(buffer.array()));
		} while (!isLastMessage(ret));

		return ret;
	}
}
