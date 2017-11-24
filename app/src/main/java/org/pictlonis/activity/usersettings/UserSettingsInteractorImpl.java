package org.pictlonis.activity.usersettings;

import org.pictlonis.data.GameInformation;
import org.pictlonis.data.User;
import org.pictlonis.data.UserList;

import java.io.File;

/**
 * Created by bigfoot on 24/11/17.
 */

public class UserSettingsInteractorImpl implements UserSettingsInteractor{
	@Override
	public void saveSettings(User user) throws Exception {
		UserList uList;
		User curUser;
		File userListFile;

		uList = GameInformation.getInstance().getUserList();
		userListFile = GameInformation.getInstance().getUsersFile();
		curUser = getCurrentUser();
		uList.replaceUser(curUser, user);
		uList.saveToFile(userListFile);
	}

	@Override
	public User getCurrentUser() {
		return GameInformation.getInstance().getUser();
	}
}
