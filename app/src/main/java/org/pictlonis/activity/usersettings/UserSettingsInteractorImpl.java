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

		curUser = getCurrentUser();

		userListFile = GameInformation.getInstance().getUsersFile();
		uList = new UserList();
		uList.replaceFromFile(userListFile);

		uList.replaceUser(curUser, user);
		uList.saveToFile(userListFile);
		GameInformation.getInstance().setUser(user);
	}

	@Override
	public User getCurrentUser() {
		return GameInformation.getInstance().getUser();
	}
}
