package org.pictlonis.net.host;

import org.pictlonis.net.message.MessageInfo;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.NodeType;
import org.pictlonis.net.message.PictlonisMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Server implements NetworkNode {
	private int nbClients;
	private int port;
	private Selector selector;
	private ServerSocketChannel ssocketChannel;
	private InetSocketAddress addr;
	private ArrayList<Socket> sclients;
	private MessageThread msgThread;

	private void sendMaxPlayers(Socket socket) throws Exception {
		sendMessageTo(PictlonisMessage.maxPlayer(nbClients), socket);
	}

	private void sendNbConn() throws Exception {
		NetworkMessage.sendMessage(PictlonisMessage.nbConnected(sclients.size()), sclients);
	}

	private String readMessage(Socket socket) throws Exception {
		BufferedReader br;
		InputStreamReader isr;
		String ret;

		isr = new InputStreamReader(socket.getInputStream());
		br = new BufferedReader(isr);
		ret = br.readLine();

		br.close();
		return ret;
	}

	private void initServer() throws Exception {
		selector = Selector.open();
		addr = new InetSocketAddress(port);

		ssocketChannel = ServerSocketChannel.open();
		ssocketChannel.configureBlocking(false);
		ssocketChannel.socket().bind(addr);
		ssocketChannel.register(selector, ssocketChannel.validOps());
	}

	public Server(int port, int nbClients) throws Exception {
		this.nbClients = nbClients;
		this.port = port;
		initServer();
		sclients = new ArrayList<Socket>();

		msgThread = new MessageThread(this);
		msgThread.readMessages();
	}

	public void waitForClients() throws Exception {
		int nb_conn;

		nb_conn = 0;
		while (nb_conn < nbClients) {
			selector.select();

			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
			ssocketChannel.configureBlocking(true);

			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();

				if (key.isAcceptable()) {
					SocketChannel sock_client;

					sock_client = ssocketChannel.accept();
					sock_client.configureBlocking(false);
					sock_client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					nb_conn++;

					sclients.add(sock_client.socket());
					sendMaxPlayers(sock_client.socket());
					sendNbConn();
				}

				keyIterator.remove();
			}
		}
	}

	@Override
	public MessageInfo getMessage() throws Exception {
		MessageInfo ret;
		String msg;

		ret = null;
		while (ret == null) {
			selector.select();

			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

			while (ret == null && keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();

				if (key.isReadable()) {
					SocketChannel sock_client;
					Socket socket;

					sock_client = (SocketChannel) key.channel();
					socket = sock_client.socket();
					msg = readMessage(socket);
					ret = new MessageInfo(msg, socket);
				}

				keyIterator.remove();
			}
		}

		return ret;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.SERVER;
	}

	@Override
	public void close() throws Exception {
		ssocketChannel.close();
	}

	public void sendMessageTo(String msg, Socket sock) throws Exception {
		NetworkMessage.sendMessage(msg, sock);
	}

	public void sendMessage(String msg) throws Exception {
		NetworkMessage.sendMessage(msg, sclients);
	}

	public void sendMessageFrom(String msg, Socket sock) throws Exception {
		ArrayList<Socket> tmp_list;

		tmp_list = new ArrayList<Socket>(sclients);
		tmp_list.remove(sock);

		NetworkMessage.sendMessage(msg, tmp_list);
	}
}
