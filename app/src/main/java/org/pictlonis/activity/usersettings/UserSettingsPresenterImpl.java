package org.pictlonis.activity.usersettings;

import org.pictlonis.data.User;

/**
 * Created by bigfoot on 24/11/17.
 */

public class UserSettingsPresenterImpl implements UserSettingsPresenter {
	private UserSettingsView userSettingsView;
	private UserSettingsInteractor interactor;

	public UserSettingsPresenterImpl(UserSettingsView userSettingsView) {
		this.userSettingsView = userSettingsView;
		interactor = new UserSettingsInteractorImpl();
	}

	@Override
	public void validateSettings(String uname, String fname, String lname, String email) {
		User curUser;

		if (uname.isEmpty()) {
			userSettingsView.onFailure("Veuillez rentrer un nom d'utilisateur");
			return;
		}

		curUser = getCurrentUser();
		curUser.setEmail(email);
		curUser.setFirstName(fname);
		curUser.setLastName(lname);
		curUser.setUname(uname);
		try {
			interactor.saveSettings(curUser);
		} catch (Exception e) {
			userSettingsView.onFailure(e.getMessage());
		}
	}

	@Override
	public User getCurrentUser() {
		return interactor.getCurrentUser();
	}
}
