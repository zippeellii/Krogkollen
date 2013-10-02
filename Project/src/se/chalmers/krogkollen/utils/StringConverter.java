package se.chalmers.krogkollen.utils;

/**
 * Converts Strings to different formats
 * 
 * @author Jonathan Nilsfors
 */
public class StringConverter {
	/**
	 * Converts a String to an int.
	 * The String should be in form :[NUMBER]:, where it returns NUMBER.
	 * The String can also be formatted as :[NUMBER1]:[NUMBER2]:... where it returns the first number encountered.
	 * Effects when the String is formatted another way is undocumented.
	 * 
	 * @param string 	the entire string
	 * @param day 		what day supposed to look for
	 * @return the selected interval of the string in int
	 */
	public static int convertStringToFragmentedInt(String string, int day){
		for(int i = 0; i < day; i++){
			int index = string.indexOf(':');
			string = string.substring(index+1);
		}
		int index = string.indexOf(':');
		string = string.substring(0, index);
		return Integer.parseInt(string);
	}
}
