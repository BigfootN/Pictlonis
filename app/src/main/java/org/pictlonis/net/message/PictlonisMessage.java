package org.pictlonis.net.message;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Message;

import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 05/11/17.
 */

public class PictlonisMessage {
	private static final String NB_CONNECTED = "NB_CONN_";
	private static final String MAX_PLAYER = "MAX_PLY_";
	private static final String POS_POINTN = "POS_PTN_";
	private static final String POS_POINTM = "POS_PTM_";
	private static final String POS_POINTL = "POS_PTL_";
	private static final String CHAT_MSG = "CHAT_MSG_";

	private static String pointToStr(PointF point) {
		String ret;

		ret = Float.toString(point.x);
		ret += ",";
		ret += Float.toString(point.y);

		return ret;
	}

	private static String getChatMessage(String msg) {
		String ret;

		ret = msg.substring(CHAT_MSG.length());

		return ret;
	}

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

		return ret;
	}

	private static PointF curPoint(String msg) {
		return getPoint(POS_POINTM, msg);
	}

	private static PointF newPoint(String msg) {
		return getPoint(POS_POINTN, msg);
	}

	private static PointF lastPoint(String msg) {
		return getPoint(POS_POINTL, msg);
	}

	public static MessageInfo getInfoMessage(String msg) {
		GameInformation gameInfo;
		Object value;
		MessageInfo<?> ret;

		gameInfo = GameInformation.getInstance();
		if (msg.startsWith(NB_CONNECTED)) {
			value = nbConnected(msg);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.NB_CONNECTED, value);
			gameInfo.setNbConnected((int)value);
		} else if (msg.startsWith(MAX_PLAYER)) {
			value = maxPlayer(msg);
			gameInfo.setNbPlayers((int)value);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.NB_PLAYERS, value);
		} else if (msg.startsWith(POS_POINTN)) {
			value = curPoint(msg);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.POINT_NEW, value);
		} else if (msg.startsWith(POS_POINTM)) {
			value = newPoint(msg);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.POINT_MOVE, value);
		} else if (msg.startsWith(POS_POINTL)) {
			value = lastPoint(msg);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.POINT_LAST, value);
		} else if (msg.startsWith(CHAT_MSG)) {
			value = getChatMessage(msg);
			ret = new MessageInfo<>(null, MessageInfo.PictlonisMessageType.CHAT_MSG, value);
		} else {
			ret = null;
		}

		return ret;
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

		ret = POS_POINTM + pointToStr(point);

		return ret;
	}

	public static String startPoint(PointF point) {
		String ret;

		ret = POS_POINTM + pointToStr(point);

		return ret;
	}

	public static String lastPoint(PointF point) {
		String ret;

		ret = POS_POINTL + pointToStr(point);

		return ret;
	}

	public static String chatMessage(String msg) {
		String ret;

		ret = CHAT_MSG + msg;

		return ret;
	}
}
