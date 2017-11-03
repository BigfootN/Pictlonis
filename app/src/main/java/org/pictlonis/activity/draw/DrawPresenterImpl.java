package org.pictlonis.activity.draw;

import android.view.View;

/**
 * Created by bigfoot on 03/11/17.
 */

public class DrawPresenterImpl implements DrawPresenter {
	DrawInteractor interactor;
	DrawingView view;

	public DrawPresenterImpl(DrawingView view) {
		this.view = view;
		interactor = new DrawInteractorImpl();
	}

	@Override
	public void validateMessage(String msg) {
		if (msg != null)
			try {
				interactor.sendMessage(msg);
			} catch (Exception e) {
				view.onFailure(e.getMessage());
			}
	}

	@Override
	public void manageDrawing(View view) {

	}
}
