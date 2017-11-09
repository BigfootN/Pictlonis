package org.pictlonis.activity.login;

import org.pictlonis.data.GameInformation;
import org.pictlonis.data.User;
import org.pictlonis.data.UserList;

import java.io.File;

/**
 * Created by bigfoot on 04/11/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

	@Override
	public boolean loginIsValid(String uname, String pwd) {
		User user;
		UserList userList;
		File usersFile;
		boolean ret;

		ret = false;
		usersFile = GameInformation.getInstance().getUsersFile();
		userList = new UserList();
		user = new User(uname, pwd);

		try {
			userList.replaceFromFile(usersFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userList.userExists(user)) {
			GameInformation.getInstance().setUser(user);
			ret = true;
		}

		return ret;
	}
}
