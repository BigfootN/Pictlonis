package org.pictlonis.activity.host;

import org.pictlonis.net.host.Server;

/**
 * Created by bigfoot on 31/10/17.
 */

public class HostSettingInteractorImpl implements HostSettingInteractor {
	private Server server;

	@Override
	public void createServer(int port, int nbClients) throws Exception {
		server = new Server(port, nbClients);
	}
}
