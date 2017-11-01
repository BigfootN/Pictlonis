package org.pictlonis;

/**
 * Created by bigfoot on 31/10/17.
 */

public class Utils {
	private Utils() {}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch	(Exception e) {
			return false;
		}

		return true;
	}
}
