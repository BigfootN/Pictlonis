package org.pictlonis.net.client;

import android.os.AsyncTask;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 15/11/17.
 */

public class NetworkConnect extends AsyncTask<InetSocketAddress, Integer, SocketChannel> {

	private SocketChannel connect(InetSocketAddress addr) throws Exception {
		SocketChannel ret;

		ret = SocketChannel.open(addr);
		while (!ret.finishConnect())
			wait(50);

		return ret;
	}

	@Override
	protected SocketChannel doInBackground(InetSocketAddress... inetSocketAddresses) {
		SocketChannel ret;

		try {
			publishProgress(0);
			ret = connect(inetSocketAddresses[0]);
			publishProgress(100);
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}

		return ret;
	}
}
