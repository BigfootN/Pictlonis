package org.pictlonis.activity.subscribe;

import org.pictlonis.data.GameInformation;
import org.pictlonis.data.User;
import org.pictlonis.data.UserList;

import java.io.File;

/**
 * Created by bigfoot on 13/11/17.
 */

public class SubscribeInteractorImpl implements SubscribeInteractor {

	@Override
	public void saveUser(User user) throws Exception {
		UserList uList;
		File usersFile;

		uList = new UserList();
		usersFile = GameInformation.getInstance().getUsersFile();

		uList.replaceFromFile(usersFile);
		uList.put(user);
		uList.saveToFile(usersFile);
	}
}
