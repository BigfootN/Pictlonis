package org.pictlonis.net.client;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageInfo;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.NodeType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Client implements NetworkNode {
	private SocketChannel socket;
	private InetSocketAddress addr;
	private MessageThread msgThread;
	NetworkConnect netConn;

	public Client() {
		netConn = new NetworkConnect();
		GameInformation.getInstance().setNode(GameInformation.NodeType.CLIENT, this);
	}

	public void connectTo(String ip, int port) throws Exception {
		this.addr = new InetSocketAddress(ip, port);
		netConn.execute(addr);
		socket = netConn.get();

		msgThread = new MessageThread(this);
		msgThread.readMessages();
	}

	@Override
	public MessageInfo getMessage() throws IOException {
		MessageInfo ret;
		String msg;

		msg = NetworkMessage.readMessage(socket);
		ret = new MessageInfo(msg, socket);

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
