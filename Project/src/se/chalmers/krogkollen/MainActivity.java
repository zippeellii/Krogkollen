package se.chalmers.krogkollen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.ActivityID;

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

        new InitResourcesTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    // TODO javadoc
    private class InitResourcesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            setContentView(R.layout.activity_main);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Tells the backend to initialize its server connection
            Backend.init(MainActivity.this, "WgLQnilANHpjM3xITq0nM0eW8dByIgDDmxJzf6se", "9ZK7yjE1NiD244ymDHb8ZpbbWNNv3RuQq7ceEvJc");

            PubUtilities.getInstance().loadPubList();

            //initiate the user location and start the map activity.
            UserLocation.init((LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE));

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            intent.putExtra("previous_activity", ActivityID.MAIN);
            startActivity(intent);
        }
    }
}
