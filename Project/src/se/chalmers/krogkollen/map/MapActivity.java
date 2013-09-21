package se.chalmers.krogkollen.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.R.layout;
import se.chalmers.krogkollen.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapActivity extends Activity {

    private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        addMarker();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

    private void addMarker() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(i*10, j*10))
                        .title(i*10 + " . " + j*10));
            }
        }
    }
}
