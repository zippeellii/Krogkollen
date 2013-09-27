package se.chalmers.krogkollen.utils;

public class StringConverter {
	/**
	 * 
	 * @param string - the entire string
	 * @param day - what day supposed to look for
	 * @return the selected interval of the string in int
	 */
	public static int convertStringtoInt(String string, int day){
		for(int i = 0; i < day; i++){
			int index = string.indexOf(':');
			string = string.substring(index+1);
		}
		int index = string.indexOf(':');
		string = string.substring(0, index);
		return Integer.parseInt(string);
	}

}
