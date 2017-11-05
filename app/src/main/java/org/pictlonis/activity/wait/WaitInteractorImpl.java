package org.pictlonis.activity.wait;

import android.widget.TextView;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.NetworkNode;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitInteractorImpl extends Thread implements WaitInteractor {
	NetworkNode node;
	int maxPlayer;
	int nbConn;

	private boolean everybodyConn() {
		nbConn = GameInformation.getInstance().getNbConnected();

		return nbConn < maxPlayer;
	}

	public WaitInteractorImpl() {
		node = GameInformation.getInstance().getNode();
		maxPlayer = GameInformation.getInstance().getNbPlayers();
		nbConn = 0;
	}

	@Override
	public void updateNbPlayer(TextView view) throws Exception {
		String msg;

		while (!everybodyConn()) {
			msg = (new Integer(nbConn)).toString();
			msg += " joueurs connectes sur ";
			msg += (new Integer(maxPlayer)).toString();

			view.setText(msg);
		}
	}
}
