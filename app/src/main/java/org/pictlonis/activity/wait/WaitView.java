package org.pictlonis.activity.wait;

/**
 * Created by bigfoot on 05/11/17.
 */

public interface WaitView {
	void onAllConnected();
	void onFailure(String msg);
	void setNbPlayer(String msg);
}
