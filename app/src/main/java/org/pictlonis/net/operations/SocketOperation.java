package org.pictlonis.net.operations;

import android.os.AsyncTask;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by bigfoot on 18/11/17.
 */

public class SocketOperation extends AsyncTask<Void, Void, AsyncTaskResult<ByteBuffer>> implements Runnable {
	private OperationType opType;
	private ByteBuffer buffer;
	private SocketChannel socket;
	private boolean isFinished;

	@Override
	public void run() {
		try {
			if (opType == OperationType.WRITE) {
				while (buffer.hasRemaining()) {
					socket.write(buffer);
				}
			} else if (opType == OperationType.READ) {
				socket.read(buffer);
			}

			isFinished = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public enum OperationType {
		WRITE,
		READ;
	}

	public SocketOperation(OperationType type, ByteBuffer buffer, SocketChannel socket) {
		opType = type;
		this.buffer = buffer;
		this.socket = socket;
		isFinished = false;
	}

	public ByteBuffer getResult() {
		ByteBuffer ret;

		ret = null;
		while (!isFinished)
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		if (opType == OperationType.READ)
			ret = buffer;

		return ret;
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
			e.printStackTrace();
			ret = new AsyncTaskResult<>(e);
		}

		return ret;
	}

}
