package org.pictlonis.net;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by bigfoot on 30/10/17.
 */

public class Server {
	private ServerSocket socket;
	private ArrayList<Socket> clients;
	private int nbClients;
	private int port;

	public Server(int port, int nbClients) throws Exception {
		this.nbClients = nbClients;
		clients = new ArrayList<Socket>();
		this.port = port;
		socket = new ServerSocket(port, nbClients);
	}

	public void voidWaitForClients() throws Exception {
		int curNbConn;
		Socket curSock;

		curNbConn = 0;
		while (curNbConn < nbClients) {
			curSock = socket.accept();
			clients.add(curSock);
			curNbConn--;
		}
	}

	public void sendToAll(String ... messages) throws Exception{
		PrintWriter writer;

		for (Socket client : clients) {
			writer = new PrintWriter(client.getOutputStream(), true);
			for (String msg : messages) {
				writer.write(msg);
				writer.flush();
			}
			writer.close();
		}
	}
}
