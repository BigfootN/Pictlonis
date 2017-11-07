package org.pictlonis.activity.wait;

/**
 * Created by bigfoot on 05/11/17.
 */

public interface WaitInteractor {
	void waitPlayers();
	int getNbConn();
	int getNbPlayers();
	boolean everybodyConnected();
}
