package org.pictlonis.activity.draw;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.utils.MessageInfo;
import org.pictlonis.net.utils.NetworkNode;

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
	public MessageInfo getMessage() throws Exception {
		return node.getMessage();
	}
}
