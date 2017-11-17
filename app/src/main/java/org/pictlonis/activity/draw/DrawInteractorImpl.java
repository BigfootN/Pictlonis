package org.pictlonis.activity.draw;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageInfo;
import org.pictlonis.net.NetworkNode;

/**
 * Created by bigfoot on 03/11/17.
 */

public class DrawInteractorImpl implements DrawInteractor {
	NetworkNode node;

	public DrawInteractorImpl() {
		node = GameInformation.getInstance().getNode();

	}

	@Override
	public void sendMessage(String msg) throws Exception {
		node.sendMessage(msg);
	}

	@Override
	public String getMessage() throws Exception {
		return node.getMessage();
	}
}
