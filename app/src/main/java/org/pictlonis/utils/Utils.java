package org.pictlonis.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static boolean containsWhiteSpace(String str) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("\\s");
		matcher = pattern.matcher(str);

		return matcher.find();
	}
}
