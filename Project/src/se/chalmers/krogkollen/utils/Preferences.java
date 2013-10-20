package se.chalmers.krogkollen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
 * A singleton class for handling saved preferences
 * 
 * @author Jonathan Nilsfors
 * 
 */
public class Preferences {

	private static Preferences	prefs;
	private Context				context;
	private SharedPreferences	sharedPrefs;

	// Private constructor to prevent duplicated instantiation
	private Preferences() {
	}

	public static Preferences getInstance() {
		if (prefs == null) {
			prefs = new Preferences();
		}
		return prefs;
	}

	/**
	 * Initializes the Preferences singleton
	 * 
	 * @param context
	 */
	public void init(Context context) {
		this.context = context;
		this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);
	}

	/**
	 * Saves the preference
	 * 
	 * @param id the id of the preference
	 * @param value the value of the preference
	 */
	public void savePreference(String id, boolean value) {
		this.sharedPrefs.edit().putBoolean(id, value).commit();
	}

	/**
	 * Loads the preference value for specified id
	 * 
	 * @param id the requested objects id
	 * @return true or false
	 */
	public boolean loadPreference(String id) {
		return this.sharedPrefs.getBoolean(id, true);
	}
}
