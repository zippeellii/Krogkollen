package se.chalmers.krogkollen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import se.chalmers.krogkollen.backend.BackendHandler;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.ParseBackend;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.Constants;
import se.chalmers.krogkollen.utils.Preferences;

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
 * 
 * A class used for starting the application
 * 
 * @author Jonathan Nilsfors
 * @author Oskar Karrman
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (isNetworkAvailable()) {
            new InitResourcesTask().execute();
        } else {
            Toast.makeText(this, R.string.error_no_connection, Toast.LENGTH_LONG).show();
            this.finish();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// Checks if a network connection is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

	// Initiates required functions in another thread
	private class InitResourcesTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... voids) {
			
			//Initiates the preferences holder
			Preferences.getInstance().init(getApplicationContext());

			// Tells the backend handler to initialize its server connection
			// with a backend to Parse.com
			BackendHandler.getInstance().setBackend(
					new ParseBackend(MainActivity.this, "WgLQnilANHpjM3xITq0nM0eW8dByIgDDmxJzf6se",
							"9ZK7yjE1NiD244ymDHb8ZpbbWNNv3RuQq7ceEvJc"));

			// If you want to use the mockup backend, comment the above line and
			// uncomment the line below
			//BackendHandler.getInstance().setBackend(new BackendMockup(0));

			try {
				PubUtilities.getInstance().loadPubList();
			} catch (NoBackendAccessException e) {
                Toast.makeText(MainActivity.this, R.string.error_no_backend_access, Toast.LENGTH_LONG).show();
			} catch (BackendNotInitializedException e) {
                Toast.makeText(MainActivity.this, R.string.error_backend_not_initialized, Toast.LENGTH_LONG).show();
			}

			// initiate the user location and start the map activity.
			UserLocation.init((LocationManager) MainActivity.this
					.getSystemService(Context.LOCATION_SERVICE));

			return null; // No data to send to the post thread work method.
		}

        @Override
        protected void onPostExecute(Void result) {
            // Start the main app when all initialization is finished.
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            intent.putExtra(Constants.ACTIVITY_ID, Constants.MAIN);
            startActivity(intent);
        }
    }
}
