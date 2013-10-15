package se.chalmers.krogkollen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
/**
 * A singleton class for handling saved preferences
 * @author Jonathan Nilsfors
 *
 */
public class Preferences {

	private static Preferences prefs;
	private Context context;
	private SharedPreferences sharedPrefs;
	
	//Private constructor to prevent duplicated instantiation
	private Preferences(){
	}
	
	public static Preferences getInstance(){
		if(prefs == null){
			prefs = new Preferences();
		}
		return prefs;
	}
	/**
	 * Initializes the Preferences singleton
	 * @param context 
	 */
	public void init(Context context){
		this.context = context;
		this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);
	}
	/**
	 * Saves the preference
	 * @param id the id of the preference
	 * @param value the value of the preference
	 */
	public void savePreference(String id, boolean value){
		this.sharedPrefs.edit().putBoolean(id, value).commit();
	}
	/**
	 * Loads the preference value for specified id
	 * @param id the requested objects id
	 * @return true or false
	 */
	public boolean loadPreference(String id){
		return this.sharedPrefs.getBoolean(id, true);
	}
}
