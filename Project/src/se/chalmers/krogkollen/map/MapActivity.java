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
import se.chalmers.krogkollen.pub.IPub;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;

public class MapActivity extends Activity implements IMapView{

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
        for (int i = 0; i < PubUtilities.getInstance().getPubList().size(); i++) {
            IPub pub = PubUtilities.getInstance().getPubList().get(i);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(pub.getCoordinates().latitude, pub.getCoordinates().longitude))
                    .title(pub.getName() + " - " + pub.getDescription()));
        }
    }

	@Override
	public void navigate(Class<?> destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPubToMap(IPub pub) {
		// TODO Auto-generated method stub
		
	}
}
