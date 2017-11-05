package org.pictlonis.activity.wait;

import android.widget.TextView;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitPresenterImpl implements WaitPresenter {
	public WaitInteractor interactor;
	public TextView view;

	public WaitPresenterImpl(TextView view) {
		this.view = view;
	}

	@Override
	public void informNbPlayer() {

	}
}
