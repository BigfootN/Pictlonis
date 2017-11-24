package org.pictlonis.net.message;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;

import org.pictlonis.net.operations.AsyncTaskResult;
import org.pictlonis.net.operations.SocketOperation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketOption;
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

	private enum OperationType {
		WRITE,
		READ
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

	public static void sendMessage(String message, SocketChannel socket) throws Exception{
		ByteBuffer buffer;
		String msgToSend;
		SocketOperation write;
		AsyncTaskResult<ByteBuffer> result;
		Thread writeThread;

		msgToSend = message + System.lineSeparator();
		buffer = ByteBuffer.allocate(msgToSend.getBytes().length);
		buffer.clear();
		buffer.put(msgToSend.getBytes());
		buffer.flip();

		write = new SocketOperation(SocketOperation.OperationType.WRITE, buffer, socket);
		//write.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
		writeThread = new Thread(write);
		writeThread.start();

		write.getResult();

		//result = write.get();
		/*if(result.getException() != null)
			throw result.getException();*/
	}

	public static void sendMessage(String message, ArrayList<SocketChannel> sockets) throws Exception {
		Iterator<SocketChannel> sock;

		sock = sockets.iterator();
		while (sock.hasNext()) {
			sendMessage(message, sock.next());
		}
	}

	public static String readMessage(SocketChannel socket) throws Exception {
		String ret;
		ByteBuffer buffer;
		SocketOperation read;
		AsyncTaskResult<ByteBuffer> result;
		Thread readThread;

		ret = "";

		do {
			buffer = ByteBuffer.allocate(1);
			read = new SocketOperation(SocketOperation.OperationType.READ, buffer, socket);
			readThread = new Thread(read);
			readThread.start();

			read.getResult();

			ret += new String(buffer.array());
		} while (!isLastMessage(ret));

		ret = rmEndMsg(ret);

		return ret;
	}
}
