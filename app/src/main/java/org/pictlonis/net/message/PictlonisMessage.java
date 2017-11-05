package org.pictlonis.net.message;

import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 05/11/17.
 */

public class PictlonisMessage {
	private static final String NB_CONNECTED = "NB_CONN_";
	private static final String MAX_PLAYER = "MAX_PLY_";

	private static int maxPlayer(String msg) {
		int ret;

		ret = Integer.parseInt(msg.substring(MAX_PLAYER.length()));

		return ret;
	}

	private static int nbConnected(String msg) {
		int ret;

		ret = Integer.parseInt(msg.substring(NB_CONNECTED.length()));

		return -1;
	}

	public static void saveInfoMessage(String msg) {
		GameInformation gameInfo;
		int value;

		gameInfo = GameInformation.getInstance();
		if (msg.startsWith(NB_CONNECTED)) {
			value = nbConnected(msg);
			gameInfo.setNbConnected(value);
		} else if (msg.startsWith(MAX_PLAYER)) {
			value = maxPlayer(msg);
			gameInfo.setNbPlayers(value);
		}
	}

	public static String nbConnected(int nbConn) {
		String ret;

		ret = NB_CONNECTED + (new Integer(nbConn)).toString();

		return ret;
	}



	public static String maxPlayer(int nbPlayer) {
		String ret;

		ret = MAX_PLAYER + (new Integer(nbPlayer)).toString();

		return ret;
	}
}
