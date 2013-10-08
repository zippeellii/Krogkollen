package se.chalmers.krogkollen.utils;

/**
 * Converts Strings to different formats
 * 
 * @author Jonathan Nilsfors
 */
public class StringConverter {
	/**
	 * Converts a String to an int.
	 * The String should be in form :[NUMBER1]:[NUMBER2]:..., where it the returns the fragment in the argument.
	 * Fragment start indexing at 1, so if the string is as stated above, passing 1 as the fragment argument will 
	 * return [NUMBER1] and passing 2 will return [NUMBER2].
	 * 
	 * @param string 	the entire string
	 * @param fragment	what fragment it should return, index starts at 1
	 * @return			the fragment corresponding to the fragment argument
	 */
	public static int convertStringToFragmentedInt(String string, int fragment){
		try {
			for(int i = 0; i < fragment; i++){
				int index = string.indexOf(':');
				string = string.substring(index+1);
			}
			int index = string.indexOf(':');
			string = string.substring(0, index);
			return Integer.parseInt(string);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
