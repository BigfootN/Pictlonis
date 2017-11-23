package org.pictlonis.chat;

import android.content.Context;
import android.widget.ScrollView;
import android.widget.TextView;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.message.PictlonisMessage;
import org.pictlonis.utils.CommonViews;

/**
 * Created by bigfoot on 04/11/17.
 */

public class ChatViewInteractorImpl implements ChatViewInteractor{
	@Override
	public void sendMessage(String msg) {
		String msgToSend;
		NetworkNode node;

		msgToSend = PictlonisMessage.chatMessage(msg);
		node = GameInformation.getInstance().getNode();

		try {
			node.sendMessage(msgToSend);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
