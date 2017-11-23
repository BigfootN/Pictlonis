package org.pictlonis.net.message;

import android.graphics.PointF;

import org.pictlonis.activity.draw.Drawer;
import org.pictlonis.data.GameInformation;
import org.pictlonis.utils.draw.DrawOperation;
import org.pictlonis.utils.draw.DrawOperations;

/**
 * Created by bigfoot on 16/11/17.
 */

public class MessageActionImpl implements MessageAction {

	private DrawOperation buildDrawOp(MessageInfo msgInfo) {
		DrawOperation ret;
		DrawOperation.DrawOperationType drawOpType;
		MessageInfo.PictlonisMessageType msgType;
		PointF point;

		point = null;
		drawOpType = null;
		ret = null;

		msgType = msgInfo.getMessageType();
		switch (msgType) {
			case POINT_NEW:
				point = (PointF) msgInfo.getValue();
				drawOpType = DrawOperation.DrawOperationType.NEW;
				break;
			case POINT_MOVE:
				point = (PointF) msgInfo.getValue();
				drawOpType = DrawOperation.DrawOperationType.MOVE;
				break;
			case POINT_LAST:
				drawOpType = DrawOperation.DrawOperationType.UP;
				break;
		}

		if (drawOpType != null)
			ret = new DrawOperation(drawOpType, point);

		return ret;
	}

	@Override
	public void takeAction(MessageInfo<?> msgInfo) {
		GameInformation gameInfoInst;
		MessageInfo.PictlonisMessageType type;
		DrawOperation drawOp;
		Drawer drawer;

		gameInfoInst = GameInformation.getInstance();
		type = msgInfo.getMessageType();
		drawer = gameInfoInst.getDrawer();

		if (type == MessageInfo.PictlonisMessageType.NB_CONNECTED) {
			gameInfoInst.setNbConnected((Integer)msgInfo.getValue());
		} else if (type == MessageInfo.PictlonisMessageType.CHAT_MSG){
			gameInfoInst.getChatView().addMessage((String)msgInfo.getValue());
		} else {
			drawOp = buildDrawOp(msgInfo);
			if (drawOp != null)
				drawer.draw(drawOp);
		}
	}
}
