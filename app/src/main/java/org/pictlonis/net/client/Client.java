package org.pictlonis.net.client;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageInfo;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.message.NetworkNode;
import org.pictlonis.net.message.NodeType;

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
	private Socket socket;
	private InetAddress addr;
	private MessageThread msgThread;

	public Client() {
		GameInformation.getInstance().setNode(GameInformation.NodeType.CLIENT, this);
	}

	public void connectTo(String ip, int port) throws UnknownHostException, IOException {
		this.addr = InetAddress.getByName(ip);
		socket = new Socket(this.addr, port);

		msgThread = new MessageThread(this);
		msgThread.readMessages();
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
