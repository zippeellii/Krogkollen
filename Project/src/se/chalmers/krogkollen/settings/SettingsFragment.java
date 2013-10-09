package se.chalmers.krogkollen.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import se.chalmers.krogkollen.R;

/**
 * ${TITLE} (UTF-8)
 * <p/>
 * 
 * @author Johan Backman
 */
public class SettingsFragment extends PreferenceFragment {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
	}
}