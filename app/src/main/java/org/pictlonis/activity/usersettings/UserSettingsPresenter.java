package org.pictlonis.activity.usersettings;

import org.pictlonis.data.User;

/**
 * Created by bigfoot on 24/11/17.
 */

public interface UserSettingsPresenter {
	void validateSettings(String uname, String fname, String lname, String email);
	User getCurrentUser();
}
