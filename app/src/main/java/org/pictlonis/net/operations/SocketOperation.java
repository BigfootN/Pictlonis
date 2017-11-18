package org.pictlonis.net.operations;

import android.os.AsyncTask;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 18/11/17.
 */

public class SocketOperation extends AsyncTask<Void, Void, AsyncTaskResult<ByteBuffer>> {
	private OperationType opType;
	private ByteBuffer buffer;
	private SocketChannel socket;

	public enum OperationType {
		WRITE,
		READ;
	}
	public SocketOperation(OperationType type, ByteBuffer buffer, SocketChannel socket) {
		opType = type;
		this.buffer = buffer;
		this.socket = socket;
	}

	@Override
	protected AsyncTaskResult<ByteBuffer> doInBackground(Void... voids) {
		AsyncTaskResult<ByteBuffer> ret;

		ret = null;
		try {
			if (opType == OperationType.WRITE) {
				while (buffer.hasRemaining()) {
					socket.write(buffer);
				}

				ret = new AsyncTaskResult<>();
			} else if (opType == OperationType.READ) {
				socket.read(buffer);
				ret = new AsyncTaskResult<>(buffer);
			}
		} catch (Exception e) {
			ret = new AsyncTaskResult<>(e);
		}

		return ret;
	}

}
