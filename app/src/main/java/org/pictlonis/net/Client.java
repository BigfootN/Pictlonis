package org.pictlonis.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Client {
	Socket socket;
	InetAddress addr;

	public Client() {}

	public void connectTo(byte[] addr, int port) throws UnknownHostException, IOException {
		this.addr = InetAddress.getByAddress(addr);
		socket = new Socket(this.addr, port);
	}

	public String getMessage() throws IOException {
		InputStreamReader stream;
		BufferedReader reader;

		stream = new InputStreamReader(socket.getInputStream());
		reader = new BufferedReader(stream);

		return reader.readLine();
	}
}
