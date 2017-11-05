package org.pictlonis.activity.draw;

import org.pictlonis.net.message.MessageInfo;

/**
 * Created by bigfoot on 03/11/17.
 */

public interface DrawInteractor {
	void sendMessage(String msg) throws Exception;
	MessageInfo getMessage() throws Exception;
}
