package org.pictlonis.net.operations;

import android.os.AsyncTask;

/**
 * Created by bigfoot on 18/11/17.
 */

public class AsyncTaskResult<T> {
	private T result;
	private Exception error;

	public AsyncTaskResult() {
		result = null;
		error = null;
	}

	public AsyncTaskResult(T result) {
		this.result = result;
		error = null;
	}

	public AsyncTaskResult(Exception error) {
		this.error = error;
		result = null;
	}

	public Exception getException() {
		return error;
	}

	public T getResult() {
		return result;
	}
}
