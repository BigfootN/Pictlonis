package org.pictlonis.activity.usersettings;

import org.pictlonis.data.User;

/**
 * Created by bigfoot on 24/11/17.
 */

public interface UserSettingsInteractor {
	void saveSettings(User user) throws Exception;
	User getCurrentUser();
}
