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

	public WaitInteractorImpl() {
		node = GameInformation.getInstance().getNode();
		start();
		getNbPlayers();
		nbConn = 0;
	}

	@Override
	public void run() {
		try {
			node.launch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getNbConn() {
		nbConn = GameInformation.getInstance().getNbConnected();
		return nbConn;
	}

	@Override
	public int getNbPlayers() {
		while (maxPlayer == 0)
			maxPlayer = GameInformation.getInstance().getNbPlayers();

		return maxPlayer;
	}

	@Override
	public boolean everybodyConnected() {
		return nbConn == maxPlayer;
	}
}
