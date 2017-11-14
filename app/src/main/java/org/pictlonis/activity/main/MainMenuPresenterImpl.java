package org.pictlonis.activity.main;

import android.view.View;

import org.pictlonis.R;

/**
 * Created by bigfoot on 31/10/17.
 */

public class MainMenuPresenterImpl implements MainMenuPresenter {
	private MainMenuView view;

	public MainMenuPresenterImpl(MainMenuView view) {
		this.view = view;
	}

	@Override
	public void manageButtonActions(View v) {
		switch (v.getId()) {
			case R.id.subscribe:
				view.gotToSubscribe();
				break;
			case R.id.login:
				view.goToLogin();
				break;
		}
	}
}
