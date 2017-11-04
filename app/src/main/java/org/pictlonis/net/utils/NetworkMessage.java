package org.pictlonis.net.utils;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bigfoot on 02/11/17.
 */

public class NetworkMessage {
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

	public static void sendMessage(String message, ArrayList<Socket> sockets) throws Exception{
		Iterator<Socket> sock;

		sock = sockets.iterator();
		while (sock.hasNext()) {
			sendMessage(message, sock.next());
		}
	}
}
