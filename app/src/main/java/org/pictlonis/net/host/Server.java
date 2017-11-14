package org.pictlonis.net.host;

import org.pictlonis.data.GameInformation;
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
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Server implements NetworkNode {
	private int nbClients;
	private int port;
	private Selector selector;
	private ServerSocketChannel ssocketChannel;
	private InetSocketAddress addr;
	private ArrayList<SocketChannel> sclients;
	private MessageThread msgThread;

	private void sendMaxPlayers(SocketChannel socket) throws Exception {
		sendMessageTo(PictlonisMessage.maxPlayer(nbClients), socket);
	}

	private void sendNbConn() throws Exception {
		NetworkMessage.sendMessage(PictlonisMessage.nbConnected(sclients.size()), sclients);
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
		sclients = new ArrayList<SocketChannel>();

		msgThread = new MessageThread(this);
		msgThread.readMessages();
	}

	public void waitForClients() throws Exception {
		int nb_conn;
		SocketChannel schannel;
		Set<SelectionKey> keys;
		Iterator<SelectionKey> it;
		SelectionKey key;


		nb_conn = 0;

		while (nb_conn < nbClients) {
			selector.select();
			keys = selector.selectedKeys();
			it = keys.iterator();

			while (nb_conn < nbClients && it.hasNext()) {
				key = it.next();

				if (!key.isValid()) {
					it.remove();
					continue;
				}

				if (key.isAcceptable()) {
					schannel = ssocketChannel.accept();
					schannel.configureBlocking(false);

					selector.wakeup();
					schannel.register(selector, SelectionKey.OP_READ);

					sclients.add(schannel);
					sendMaxPlayers(schannel);
					sendNbConn();
					nb_conn++;
					GameInformation.getInstance().setNbConnected(nb_conn);
				}

				synchronized (this) {
					it.remove();
				}
			}

			this.wait(500);
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

					sock_client = (SocketChannel) key.channel();
					msg = NetworkMessage.readMessage(sock_client);
					ret = new MessageInfo(msg, sock_client);
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
		Iterator<SocketChannel> itSock;
		SocketChannel curSock;

		itSock = sclients.iterator();
		while (itSock.hasNext()) {
			curSock = itSock.next();
			curSock.close();
		}

		ssocketChannel.close();
	}

	public void sendMessageTo(String msg, Socket sock) throws Exception {
		NetworkMessage.sendMessage(msg, sock);
	}

	public void sendMessageTo(String msg, SocketChannel sock) throws Exception {
		NetworkMessage.sendMessage(msg, sock);
	}

	public void sendMessage(String msg) throws Exception {
		NetworkMessage.sendMessage(msg, sclients);
	}

	public void sendMessageFrom(String msg, SocketChannel sock) throws Exception {
		ArrayList<SocketChannel> tmp_list;

		tmp_list = new ArrayList<SocketChannel>(sclients);
		tmp_list.remove(sock);

		NetworkMessage.sendMessage(msg, tmp_list);
	}
}
