package se.chalmers.krogkollen.settings;

import android.app.Activity;
import android.os.Bundle;
import se.chalmers.krogkollen.R;

/**
 * ${TITLE} (UTF-8)
 * <p/>
 * @author Johan Backman
 * Date: 2013-10-04
 */
public class SettingsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.transparent_spacer);
        getActionBar().setTitle(R.string.settings_title);
    }
}