package se.chalmers.krogkollen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.CallingActivity;

/**
 * A class used for starting the application
 * @author Jonathan Nilsfors
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Initializes the Parse.com connection
		Parse.initialize(this, "WgLQnilANHpjM3xITq0nM0eW8dByIgDDmxJzf6se", "9ZK7yjE1NiD244ymDHb8ZpbbWNNv3RuQq7ceEvJc");
		ParseAnalytics.trackAppOpened(getIntent());
	
        PubUtilities.getInstance().loadPubList();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initiate the user location and start the map activity.
		UserLocation.init((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
		Intent intent = new Intent(this, MapActivity.class);
		intent.putExtra("previous_activity", CallingActivity.MAIN);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onResume() {
		super.onResume();
		this.finish();
	}

}
