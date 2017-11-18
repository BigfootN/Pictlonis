package org.pictlonis.activity.client;

import org.pictlonis.net.client.Client;

/**
 * Created by bigfoot on 31/10/17.
 */

public interface ClientSettingInteractor {
	Client connect(String ip, int port) throws Exception;
}
