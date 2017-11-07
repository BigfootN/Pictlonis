package org.pictlonis.activity.wait;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitPresenterImpl extends Thread implements WaitPresenter {
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
		nbPlayers = interactor.getNbPlayers();
		interactor.waitPlayers();
	}

	@Override
	public void run() {

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
