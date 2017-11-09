package org.pictlonis.data;

import java.io.File;
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

	public void saveToFile(File file) throws Exception {
		FileOutputStream fos;
		ObjectOutputStream oos;
		String filePath;

		filePath = file.getAbsolutePath();
		fos = new FileOutputStream(filePath);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}

	public void replaceFromFile(File file) throws Exception {
		FileInputStream fis;
		ObjectInputStream ois;
		UserList uList;
		String filePath;

		filePath = file.getAbsolutePath();
		fis = new FileInputStream(filePath);
		ois = new ObjectInputStream(fis);
		uList = (UserList) ois.readObject();

		replaceList(uList);
	}

	public boolean userExists(User u) {
		return contains(u);
	}
}
