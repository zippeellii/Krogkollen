package se.chalmers.krogkollen.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.R.layout;
import se.chalmers.krogkollen.R.menu;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MapActivity extends Activity {

    private GoogleMap mMap;
    private UserLocation userLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        addMarker();
        this.userLocation = new UserLocation((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
        addUserMarker(this.userLocation.getCurrentLocation());
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(this.userLocation.getCurrentLatLng(), 15, 0, 0)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	private void addUserMarker(Location location){
		mMap.addMarker(new MarkerOptions()
						.position(new LatLng(location.getLatitude(), location.getLongitude()))
						.title("User"));
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
