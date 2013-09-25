package se.chalmers.krogkollen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.map.MapActivity;
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
		//Automatically starts the map activity
		Intent intent = new Intent(this, DetailedActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
