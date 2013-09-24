package se.chalmers.krogkollen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.PubUtilities;

/**
 * A class used for starting the application
 * @author Jonathan Nilsfors
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        PubUtilities.getInstance().loadPubList();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initiate the user location and start the map activity.
		UserLocation.init((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
