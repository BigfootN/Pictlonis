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

	private static String rmEndMsg(String msg) {
		int firstIdx;
		int lastIdx;
		String ret;

		firstIdx = 0;
		lastIdx = msg.length() - System.lineSeparator().length();
		ret = msg.substring(firstIdx, lastIdx);

		return ret;
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
		buffer = ByteBuffer.allocate(msgToSend.getBytes().length);
		buffer.clear();
		buffer.put(msgToSend.getBytes());
		buffer.flip();

		while (buffer.hasRemaining()) {
			socket.write(buffer);
		}
	}

	public static void sendMessage(String message, ArrayList<SocketChannel> sockets) throws Exception{
		Iterator<SocketChannel> sock;

		sock = sockets.iterator();
		while (sock.hasNext()) {
			sendMessage(message, sock.next());
		}
	}

	public static String readMessage(SocketChannel socket) {
		String ret;
		ByteBuffer buffer;

		ret = "";
		do {
			buffer = ByteBuffer.allocate(1);
			try {
				socket.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ret += new String(buffer.array());
		} while (!isLastMessage(ret));

		ret = rmEndMsg(ret);

		return ret;
	}
}
