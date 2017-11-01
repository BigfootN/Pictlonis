package org.pictlonis.activity.host;

/**
 * Created by bigfoot on 31/10/17.
 */

public interface HostSettingInteractor {
	void createServer(int port, int nbClients) throws Exception;
}
