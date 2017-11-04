package org.pictlonis.chat;

/**
 * Created by bigfoot on 04/11/17.
 */

public interface ChatViewPresenter {
	void validateMessage(String sender, String message, int gravity);
}
