package org.pictlonis.data;

import org.pictlonis.net.Client;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.Server;

/**
 * Created by bigfoot on 01/11/17.
 */

public class GameInformation {
	private static final GameInformation ourInstance = new GameInformation();

	public enum NodeType {
		SERVER,
		CLIENT
	};

	private Server server;
	private Client client;
	private NodeType type;

	private GameInformation() {
		server = null;
		client = null;
	}

	public static GameInformation getInstance() {
		return ourInstance;
	}

	public void setInfo(NodeType type, NetworkNode node) {
		if (type == NodeType.SERVER)
			server = (Server) node;
		else if (type == NodeType.CLIENT)
			client = (Client) node;

		this.type = type;
	}

	public NodeType getGameType() {
		return type;
	}

	public NetworkNode getNode() {
		if (type == NodeType.CLIENT)
			return client;
		else if (type == NodeType.SERVER)
			return server;

		return null;
	}
}
