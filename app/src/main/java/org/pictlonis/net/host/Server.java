package org.pictlonis.net.host;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageInfo;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.NodeType;
import org.pictlonis.net.message.PictlonisMessage;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
	private ArrayList<SocketChannel> sclients;
	private MessageThread msgThread;
	private boolean isLaunched;

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

	private void waitForClients() throws Exception {
		int nb_conn;
		SocketChannel schannel;
		Set<SelectionKey> keys;
		Iterator<SelectionKey> it;
		SelectionKey key;


		nb_conn = 0;
		while (nb_conn < nbClients) {
			selector.select();
			keys = Collections.synchronizedSet(selector.selectedKeys());

			synchronized (keys) {
				it = keys.iterator();
			}

			synchronized (keys) {
				while (nb_conn < nbClients && it.hasNext()) {
					synchronized (keys) {
						key = it.next();
					}

					if (!key.isValid()) {
						it.remove();
						continue;
					}

					if (key.isAcceptable()) {
						schannel = ssocketChannel.accept();
						schannel.configureBlocking(false);

						System.out.println("Client trouve");
						selector.wakeup();
						schannel.register(selector, SelectionKey.OP_READ);
						System.out.println("Client enregistre");

						sclients.add(schannel);
						sendMaxPlayers(schannel);
						sendNbConn();
						nb_conn++;
						GameInformation.getInstance().setNbConnected(nb_conn);
					}

					synchronized (keys) {
						it.remove();
					}
				}

				synchronized (this) {
					this.wait(500);
				}
			}

		}

	}

	public Server(int port, int nbClients) throws Exception {
		this.nbClients = nbClients;
		this.port = port;
		initServer();
		sclients = new ArrayList<SocketChannel>();
		isLaunched = false;

		msgThread = new MessageThread(this);
	}

	public void launch() throws Exception {
		if (!isLaunched) {
			waitForClients();
			msgThread.readMessages();
			isLaunched = true;
		}
	}

	@Override
	public String getMessage() throws Exception {
		String ret;
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> keyIterator;
		SelectionKey key;

		ret = null;
		while (ret == null) {
			selector.select();
			selectionKeys = Collections.synchronizedSet(selector.selectedKeys());

			synchronized (selectionKeys) {
				keyIterator = selectionKeys.iterator();
			}

			synchronized (selectionKeys) {
				while (ret == null && keyIterator.hasNext()) {

					synchronized (selectionKeys) {
						key = keyIterator.next();
					}

					if (key.isReadable()) {
						SocketChannel sock_client;

						sock_client = (SocketChannel) key.channel();
						ret = NetworkMessage.readMessage(sock_client);
					}

					synchronized (selectionKeys) {
						keyIterator.remove();
					}
				}
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

	@Override
	public void sendMessage(String msg) throws Exception {
		NetworkMessage.sendMessage(msg, sclients);
	}

	public void sendMessageTo(String msg, SocketChannel sock) throws Exception {
		NetworkMessage.sendMessage(msg, sock);
	}

	public void sendMessageFrom(String msg, SocketChannel sock) throws Exception {
		ArrayList<SocketChannel> tmp_list;

		tmp_list = new ArrayList<SocketChannel>(sclients);
		tmp_list.remove(sock);

		NetworkMessage.sendMessage(msg, tmp_list);
	}
}
