package org.pictlonis.activity.client;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.Client;

/**
 * Created by bigfoot on 31/10/17.
 */

public class ClientSettingInteractorImpl implements ClientSettingInteractor{
	@Override
	public void connect(String ip, int port) throws Exception {
		Client client;

		client = new Client();
		client.connectTo(ip, port);

		GameInformation.getInstance().setInfo(GameInformation.NodeType.CLIENT, client);
	}
}
