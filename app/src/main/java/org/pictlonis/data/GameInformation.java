package org.pictlonis.data;

import org.pictlonis.net.client.Client;
import org.pictlonis.net.utils.NetworkNode;
import org.pictlonis.net.host.Server;

/**
 * Created by bigfoot on 01/11/17.
 */

public class GameInformation {
	private static final GameInformation mInstance = new GameInformation();
	private User mUser;

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

	public void setUser(User user) {
		mUser = user;
	}

	public static GameInformation getInstance() {
		return mInstance;
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

	public User getUser() {
		return mUser;
	}
}
