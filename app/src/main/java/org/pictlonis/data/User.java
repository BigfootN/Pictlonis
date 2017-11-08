package org.pictlonis.data;

import java.io.Serializable;

/**
 * Created by bigfoot on 08/11/17.
 */

public class User implements Serializable {
	public String uname;
	public int pwdHash;

	public User(String uname, String pwd) {
		this.uname = uname;
		this.pwdHash = pwd.hashCode();
	}
}
