package org.pictlonis.net.client;

import org.pictlonis.net.utils.MessageInfo;
import org.pictlonis.net.utils.NetworkMessage;
import org.pictlonis.net.utils.NetworkNode;
import org.pictlonis.net.utils.NodeType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public MessageInfo getMessage() throws IOException {
		MessageInfo ret;
		String msg;

		InputStreamReader stream;
		BufferedReader reader;

		stream = new InputStreamReader(socket.getInputStream());
		reader = new BufferedReader(stream);


		msg = reader.readLine();
		ret = new MessageInfo(msg, null);

		return ret;
	}

	public void sendMessage(String msg) throws Exception {
		NetworkMessage.sendMessage(msg, socket);
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.CLIENT;
	}

	@Override
	public void close() throws Exception {
		socket.close();
	}
}
