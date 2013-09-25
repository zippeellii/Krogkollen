package se.chalmers.krogkollen.map;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.map.marker.MarkerFactory;
import se.chalmers.krogkollen.pub.IPub;
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
        getActionBar().setDisplayUseLogoEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);

		return true;
 	}

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.refresh_info) {
            // REFRESH MAP HERE PLS
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
	
	private void addUserMarker(Location location){
		mMap.addMarker(new MarkerOptions()
						.position(new LatLng(location.getLatitude(), location.getLongitude()))
						.title("User"));
	}
	
    private void addMarker() {
        for (int i = 0; i < PubUtilities.getInstance().getPubList().size(); i++) {
            addPubToMap(PubUtilities.getInstance().getPubList().get(i));
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

        int drawable;
        // Determine which marker color to add.
        switch (pub.getQueueTime()) {
            case 1:
                drawable = R.drawable.green_marker_bg;
                break;
            case 2:
                drawable = R.drawable.yellow_marker_bg;
                break;
            case 3:
                drawable = R.drawable.red_marker_bg;
                break;
            default:
                drawable = R.drawable.gray_marker_bg;
                break;
        }
        mMap.addMarker(MarkerFactory.createMarkerOptions(getResources(), drawable, pub.getName(), pub.getOpeningHours(), new LatLng(pub.getCoordinates().latitude, pub.getCoordinates().longitude)));
	}
}
