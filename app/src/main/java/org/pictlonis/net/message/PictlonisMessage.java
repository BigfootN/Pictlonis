package org.pictlonis.net.message;

import android.graphics.PointF;

import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 05/11/17.
 */

public class PictlonisMessage {
	private static final String NB_CONNECTED = "NB_CONN_";
	private static final String MAX_PLAYER = "MAX_PLY_";
	private static final String POS_POINTN = "POS_PTN_";
	private static final String POS_POINTM = "POS_PTM_";

	private static PointF getPoint(String prefix, String msg) {
		PointF ret;
		Float x;
		Float y;
		String pointStr;

		pointStr = msg.substring(prefix.length());
		x = new Float(pointStr.split(",")[0]);
		y = new Float(pointStr.split(",")[1]);
		ret = new PointF(x, y);

		return ret;
	}

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

	private static PointF curPoint(String msg) {
		return getPoint(POS_POINTM, msg);
	}

	private static PointF newPoint(String msg) {
		return getPoint(POS_POINTN, msg);
	}

	public static void saveInfoMessage(String msg) {
		GameInformation gameInfo;
		int value;
		PointF point;

		gameInfo = GameInformation.getInstance();
		if (msg.startsWith(NB_CONNECTED)) {
			value = nbConnected(msg);
			gameInfo.setNbConnected(value);
		} else if (msg.startsWith(MAX_PLAYER)) {
			value = maxPlayer(msg);
			gameInfo.setNbPlayers(value);
		} else if (msg.startsWith(POS_POINTN)) {
			point = curPoint(msg);

		} else if (msg.startsWith(POS_POINTM)) {
			point = curPoint(msg);
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

	public static String curPoint(PointF point) {
		String ret;
		Float x;
		Float y;

		x = new Float(point.x);
		y = new Float(point.y);
		ret = POS_POINTM + x.toString() + "," + y.toString();

		return ret;
	}
}
