package org.pictlonis.net.message;

import android.graphics.PointF;

import org.pictlonis.activity.draw.Drawer;
import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 16/11/17.
 */

public class MessageActionImpl implements MessageAction {

	@Override
	public void takeAction(MessageInfo<?> msgInfo) {
		GameInformation gameInfoInst;
		MessageInfo.PictlonisMessageType type;
		Drawer drawer;
		PointF point;

		gameInfoInst = GameInformation.getInstance();
		type = msgInfo.getMessageType();
		drawer = gameInfoInst.getDrawer();

		if (type == MessageInfo.PictlonisMessageType.NB_CONNECTED) {
			gameInfoInst.setNbConnected((Integer)msgInfo.getValue());
		} else if (type == MessageInfo.PictlonisMessageType.NB_PLAYERS) {
			gameInfoInst.setNbPlayers((Integer)msgInfo.getValue());
		} else if (type == MessageInfo.PictlonisMessageType.POINT_LAST) {
			drawer.touch_up();
		} else if (type == MessageInfo.PictlonisMessageType.POINT_MOVE) {
			point = (PointF) msgInfo.getValue();
			drawer.touch_move(point.x, point.y);
		} else if (type == MessageInfo.PictlonisMessageType.POINT_NEW) {
			point = (PointF) msgInfo.getValue();
			drawer.touch_start(point.x, point.y);
		}
	}
}
