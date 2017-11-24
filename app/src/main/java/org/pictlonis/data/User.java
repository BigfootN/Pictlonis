package org.pictlonis.data;

import java.io.Serializable;

/**
 * Created by bigfoot on 08/11/17.
 */

public class User implements Serializable {
	private String uname;
	private String email;
	private String fname;
	private String lname;
	private int pwdHash;

	private String buildProperty(String prop) {
		String ret;

		if (prop != null && prop.isEmpty())
			ret = null;
		else
			ret = prop;

		return ret;
	}

	public User(String uname, String pwd, String email, String fname, String lname) {
		this.uname = uname;
		this.pwdHash = pwd.hashCode();
		this.lname = lname;
		this.fname = fname;
		this.email = email;
	}

	public int getPwdHash() {
		return pwdHash;
	}

	public String getName() {
		return uname;
	}

	public String getFirstName() {
		return fname;
	}

	public String getLastName() {
		return lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = buildProperty(email);
	}

	public void setFirstName(String fname) {
		this.fname = buildProperty(fname);
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setLastName(String lname) {
		this.lname = buildProperty(lname);
	}

	@Override
	public int hashCode() {
		int ret;

		ret = 1;
		ret = ret * 17 + uname.hashCode();
		ret = ret * 31 + pwdHash;
		ret = ret * 37 + email.hashCode();
		ret = ret * 11 + fname.hashCode();
		ret = ret * 7  + lname.hashCode();

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
