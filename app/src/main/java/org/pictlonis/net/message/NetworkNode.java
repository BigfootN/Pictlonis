package org.pictlonis.net.message;

/**
 * Created by bigfoot on 01/11/17.
 */

public interface NetworkNode {
	MessageInfo getMessage() throws Exception;
	NodeType getNodeType();
	void close() throws Exception;
	void sendMessage(String msg) throws Exception;
}
