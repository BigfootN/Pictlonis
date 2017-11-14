package org.pictlonis.data;

import java.io.Serializable;

/**
 * Created by bigfoot on 08/11/17.
 */

public class User implements Serializable {
	private String uname;
	private int pwdHash;

	public User(String uname, String pwd) {
		this.uname = uname;
		this.pwdHash = pwd.hashCode();
	}

	public int getPwdHash() {
		return pwdHash;
	}

	public String getName() {
		return uname;
	}

	@Override
	public int hashCode() {
		int ret;

		ret = 1;
		ret = ret * 17 + uname.hashCode();
		ret = ret * 31 + pwdHash;

		return ret;
	}

	@Override
	public boolean equals(Object o) {
		boolean ret;
		User user;

		ret = false;
		if (o != null && o instanceof User) {
			user = (User) o;
			if (user.getName().equals(uname)
					&& user.getPwdHash() == pwdHash)
				ret = true;
		}

		return ret;
	}
}
