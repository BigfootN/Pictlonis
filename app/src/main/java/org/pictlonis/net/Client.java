package org.pictlonis.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Client implements NetworkNode {
	Socket socket;
	InetAddress addr;

	public Client() {}

	public void connectTo(String ip, int port) throws UnknownHostException, IOException {
		this.addr = InetAddress.getByName(ip);
		socket = new Socket(this.addr, port);
	}

	@Override
	public String getMessage() throws IOException {
		InputStreamReader stream;
		BufferedReader reader;

		stream = new InputStreamReader(socket.getInputStream());
		reader = new BufferedReader(stream);

		return reader.readLine();
	}

	@Override
	public void sendMessage(String msg) throws Exception {
		BufferedWriter bw;
		OutputStream os;
		OutputStreamWriter osw;

		os = socket.getOutputStream();
		osw = new OutputStreamWriter(os);
		bw = new BufferedWriter(osw);

		bw.write(msg);
		bw.flush();
		bw.close();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.CLIENT;
	}
}
