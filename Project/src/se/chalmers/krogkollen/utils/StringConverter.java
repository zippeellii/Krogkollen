package se.chalmers.krogkollen.utils;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Converts Strings to different formats
 * 
 * @author Jonathan Nilsfors
 */
public class StringConverter {

	/**
	 * Converts a String to an int. The String should be in form :[NUMBER1]:[NUMBER2]:..., where it
	 * the returns the fragment in the argument. Fragment start indexing at 1, so if the string is
	 * as stated above, passing 1 as the fragment argument will return [NUMBER1] and passing 2 will
	 * return [NUMBER2].
	 * 
	 * @param string the entire string
	 * @param fragment what fragment it should return, index starts at 1
	 * @return the fragment corresponding to the fragment argument
	 */
	public static int convertStringToFragmentedInt(String string, int fragment) {
		try {
			for (int i = 0; i < fragment; i++) {
				int index = string.indexOf(':');
				string = string.substring(index + 1);
			}
			int index = string.indexOf(':');
			string = string.substring(0, index);
			return Integer.parseInt(string);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Converts the hour to string, if the hour is only one digit, add 0 in front, e.g. 3 becomes 03
	 * 
	 * @param hour
	 * @return the hour as a String
	 */
	public static String convertOpeningHours(int hour) {
		if (hour / 10 == 0) {
			return "0" + hour;
		}
		return "" + hour;
	}
}
