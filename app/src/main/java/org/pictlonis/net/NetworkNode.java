package org.pictlonis.net;

/**
 * Created by bigfoot on 01/11/17.
 */

public interface NetworkNode {
	String getMessage() throws Exception;
	NodeType getNodeType();
	void close() throws Exception;
	void sendMessage(String msg) throws Exception;
	void launch() throws Exception;
}
