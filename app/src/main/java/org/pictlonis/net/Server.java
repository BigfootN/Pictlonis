package org.pictlonis.net;

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
		ssocketChannel.socket().bind(addr);
		ssocketChannel.register(selector, ssocketChannel.validOps());
	}

	public Server(int port, int nbClients) throws Exception {
		this.nbClients = nbClients;
		this.port = port;
		initServer();
		sclients = new ArrayList<Socket>();
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
				}

				keyIterator.remove();
			}
		}
	}

	public void sendToAll(String ... messages) throws Exception {
	}

	@Override
	public String getMessage() throws Exception {
		String ret;

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
					ret = readMessage(socket);
				}

				keyIterator.remove();
			}
		}

		return ret;
	}

	@Override
	public void sendMessage(String msg) throws Exception {
		sendToAll(msg);
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.SERVER;
	}
}
