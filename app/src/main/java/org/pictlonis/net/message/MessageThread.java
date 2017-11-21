package org.pictlonis.net.message;

import android.app.Activity;

import org.pictlonis.net.NetworkNode;

/**
 * Created by bigfoot on 05/11/17.
 */

public class MessageThread extends Thread implements Runnable {
	private NetworkNode node;
	private MessageAction msgAction;

	public MessageThread(NetworkNode node) {
		this.node = node;
		msgAction = new MessageActionImpl();
	}

	public void readMessages() {
		start();
	}

	@Override
	public void run() {
		String msg;
		MessageInfo<?> msgInfo;
		boolean run;

		run = true;
		while (run) {
			try {
				msg = node.getMessage();
				msgInfo = PictlonisMessage.getInfoMessage(msg);
				msgAction.takeAction(msgInfo);

				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
				run = false;
			}
		}
	}
}
