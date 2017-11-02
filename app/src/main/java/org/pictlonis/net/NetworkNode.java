package org.pictlonis.net;

/**
 * Created by bigfoot on 01/11/17.
 */

public interface NetworkNode {
	MessageInfo getMessage() throws Exception;
	NodeType getNodeType();
	void close() throws Exception;
}
