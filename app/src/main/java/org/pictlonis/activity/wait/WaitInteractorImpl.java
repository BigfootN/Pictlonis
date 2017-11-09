package org.pictlonis.activity.wait;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.host.Server;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.NodeType;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitInteractorImpl extends Thread implements WaitInteractor {
	NetworkNode node;
	int maxPlayer;
	int nbConn;

	private boolean nodeIsServer() {
		boolean ret;

		ret = false;
		if (node.getNodeType() == NodeType.SERVER)
			ret = true;

		return ret;
	}

	public WaitInteractorImpl() {
		node = GameInformation.getInstance().getNode();
		maxPlayer = GameInformation.getInstance().getNbPlayers();
		nbConn = 0;
	}

	@Override
	public void run() {
		try {
			((Server) node).waitForClients();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void waitPlayers() {
		if (nodeIsServer()) {
			start();
		}
	}

	@Override
	public int getNbConn() {
		return GameInformation.getInstance().getNbConnected();
	}

	@Override
	public int getNbPlayers() {
		return GameInformation.getInstance().getNbPlayers();
	}

	@Override
	public boolean everybodyConnected() {
		nbConn = GameInformation.getInstance().getNbConnected();

		return nbConn == maxPlayer;
	}
}
