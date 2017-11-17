package org.pictlonis.net.message;

/**
 * Created by bigfoot on 16/11/17.
 */

public interface MessageAction {
	void takeAction(MessageInfo<?> msgInfo);
}
