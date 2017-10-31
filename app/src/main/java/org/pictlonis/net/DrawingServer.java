package org.pictlonis.net;

import android.graphics.PointF;

/**
 * Created by bigfoot on 30/10/17.
 */

public class DrawingServer {
	private Server server;

	public DrawingServer(Server server) throws Exception {
		this.server = server;
	}

	public void sendCurPos(PointF pos) throws Exception {
		server.sendToAll(pos.toString());
	}
}
