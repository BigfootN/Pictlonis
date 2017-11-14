package org.pictlonis.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by bigfoot on 03/11/17.
 */

public class UserList implements Serializable {
	private ArrayList<User> users;

	private void replaceList(ArrayList<User> newList) {
		Iterator<User> it;
		User curUser;

		users.clear();
		it = newList.iterator();
		while (it.hasNext()) {
			curUser = it.next();
			users.add(curUser);
		}
	}

	public UserList() {
		users = new ArrayList<User>();
	}

	public void put(User u) {
		users.add(u);
	}

	public void saveToFile(File file) throws Exception {
		FileOutputStream fos;
		ObjectOutputStream oos;
		String filePath;

		filePath = file.getAbsolutePath();
		fos = new FileOutputStream(filePath);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(users);
		oos.close();
	}

	public void replaceFromFile(File file) throws Exception {
		FileInputStream fis;
		ObjectInputStream ois;
		ArrayList<User> uList;
		String filePath;

		if (file.length() > 0) {
			filePath = file.getAbsolutePath();
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			uList = (ArrayList<User>) ois.readObject();
			replaceList(uList);
		} else {
			users = new ArrayList<User>();
		}

	}

	public boolean userExists(User u) {
		return users.contains(u);
	}
}
