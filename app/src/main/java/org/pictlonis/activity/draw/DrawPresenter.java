package org.pictlonis.activity.draw;

import android.view.View;

/**
 * Created by bigfoot on 03/11/17.
 */

public interface DrawPresenter {
	void validateMessage(String msg);

	void manageDrawing(View view);
}
