package org.pictlonis.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by bigfoot on 03/11/17.
 */

public class UserList extends Vector {

	private void replaceList(UserList list) {
		Iterator<User> it;
		User curUser;

		removeAllElements();

		it = list.iterator();
		while (it.hasNext()) {
			curUser = it.next();
			add(curUser);
		}
	}

	public UserList() {
		super();
	}

	public void put(User u) {
		add(u);
	}

	public void saveToFile(String fileName) throws Exception {
		FileOutputStream fos;
		ObjectOutputStream oos;

		fos = new FileOutputStream(fileName);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}

	public void createFromFile(String fileName) throws Exception {
		FileInputStream fis;
		ObjectInputStream ois;
		UserList uList;

		fis = new FileInputStream(fileName);
		ois = new ObjectInputStream(fis);
		uList = (UserList) ois.readObject();

		replaceList(uList);
	}

	public boolean userExists(User u) {
		return contains(u);
	}
}
