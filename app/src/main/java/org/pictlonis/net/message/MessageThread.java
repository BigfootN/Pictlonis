package org.pictlonis.net.message;

import org.pictlonis.net.NetworkNode;

/**
 * Created by bigfoot on 05/11/17.
 */

public class MessageThread extends Thread {
	private NetworkNode node;

	public MessageThread(NetworkNode node) {
		this.node = node;
	}

	public void readMessages() {
		start();
	}

	@Override
	public void run() {
		MessageInfo msg;
		boolean run;

		run = true;
		while (run) {
			try {
				msg = node.getMessage();
				PictlonisMessage.saveInfoMessage(msg.getMessage());

				sleep(50);
			} catch (Exception e) {
				run = false;
			}
		}
	}
}
