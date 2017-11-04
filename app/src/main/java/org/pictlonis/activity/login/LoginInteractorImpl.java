package org.pictlonis.activity.login;

import org.pictlonis.data.GameInformation;
import org.pictlonis.data.User;

/**
 * Created by bigfoot on 04/11/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

	@Override
	public void saveUserName(String name) {
		User user;

		user = new User(name);
		GameInformation.getInstance().setUser(user);
	}
}
