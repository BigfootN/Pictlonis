package org.pictlonis.activity.menu;

import android.view.View;

import org.pictlonis.R;

/**
 * Created by bigfoot on 31/10/17.
 */

public class MenuPresenterImpl implements MenuPresenter {
	private MenuView view;

	public MenuPresenterImpl(MenuView view) {
		this.view = view;
	}

	@Override
	public void manageButtonActions(View v) {
		switch (v.getId()) {
			case R.id.clt_btn:
				view.gotToClientMenu();
				break;
			case R.id.host_btn:
				view.goToHostMenu();
				break;
		}
	}
}
