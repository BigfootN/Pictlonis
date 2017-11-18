package org.pictlonis.activity.client;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.client.Client;

/**
 * Created by bigfoot on 31/10/17.
 */

public class ClientSettingInteractorImpl implements ClientSettingInteractor{
	@Override
	public Client connect(String ip, int port) throws Exception {
		Client ret;

		ret = new Client();
		ret.connectTo(ip, port);

		GameInformation.getInstance().setNode(GameInformation.NodeType.CLIENT, ret);

		return ret;
	}
}
