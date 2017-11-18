package org.pictlonis.activity.wait;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.NodeType;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitPresenterImpl implements WaitPresenter {
	private WaitInteractor interactor;
	private WaitView view;
	private int nbPlayers;

	private String nbConnToString(int nbConn) {
		String ret;

		ret = (new Integer(nbConn)).toString();
		ret += " joueurs connectes sur ";
		ret += (new Integer(nbPlayers)).toString();

		return ret;
	}

	public WaitPresenterImpl(WaitView view) {
		interactor = new WaitInteractorImpl();
		this.view = view;
		interactor.launchNode();
		nbPlayers = interactor.getNbPlayers();
	}

	@Override
	public void informNbPlayer() {
		int nbConn;
		String text;

		nbConn = interactor.getNbConn();
		text = nbConnToString(nbConn);
		view.setNbPlayer(text);
	}

	@Override
	public boolean allPlayersConnected() {
		return interactor.everybodyConnected();
	}
}
