package se.chalmers.krogkollen.map;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.*;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * The standard implementation of IMapView.
 *
 * This is a normal map with the user marked on the map, and with a list of pubs marked on the map.
 */
public class MapActivity extends Activity implements IMapView, IObserver{

    /**
     * Identifier for the intent used to start the activity for detailed view.
     */
    public static final String MARKER_MESSAGE = "se.chalmers.krogkollen.MARKER_MESSAGE";

    private GoogleMap mMap;
    private UserLocation userLocation;
    private Marker userMarker;
    private List<Marker> pubMarkers = new ArrayList<Marker>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		// Get the map and add some markers for pubs.
        this.mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        this.addPubMarkers();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                // Move camera to the clicked marker
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        new LatLng(marker.getPosition().latitude, marker.getPosition().longitude), 18, 0, 0)));

                // Open detailed view.
                /*Intent detailedIntent = new Intent(getApplicationContext(), MainActivity.class);
                detailedIntent.putExtra(MARKER_MESSAGE, marker.getTitle()); // Sends the name of the pub with the intent
                startActivity(detailedIntent); */

                return true; // Suppress default behavior; move camera and open info window.
            }
        });

        // Add services for auto update of the user's location.
        this.userLocation = UserLocation.getInstance();
        this.userLocation.addObserver(this);
        this.userLocation.startTrackingUser();
        addUserMarker(this.userLocation.getCurrentLatLng());

        // Move to the current location of the user.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(this.userLocation.getCurrentLatLng(), 16, 0, 0)));

        getActionBar().setDisplayUseLogoEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);

		return true;
	}

	/**
	 * Adds a user marker to the map.
	 * 
	 * @param latLng the user location
	 */
	public void addUserMarker(LatLng latLng){
		userMarker = mMap.addMarker(new MarkerOptions()
						.position(latLng)
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.user_marker)));
 	}

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.refresh_info) {
            // REFRESH MAP HERE PLS
            return true;
        }
        if (menuItem.getItemId() == R.id.search) {
            // ÖPPNA SÖKFÄLT
            return true;
        }
        if (menuItem.getItemId() == R.id.open_list_view) {
            // SKICKA INTENT - ÖPPNA LISTA
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
	
	/**
	 * Adds markers to the map for all pubs in PubUtilities.
	 */
    public void addPubMarkers() {
        for (int i = 0; i < PubUtilities.getInstance().getPubList().size(); i++) {
            addPubToMap(PubUtilities.getInstance().getPubList().get(i));
        }
    }
    
    /**
     * Removes all pub markers and adds them again with (new) information.
     */
    public void refreshPubMarkers() {
    	for(Marker pubMarker: this.pubMarkers){
    		pubMarker.remove();
    	}
    	this.pubMarkers.clear();
    	this.addPubMarkers();
    }
    
    @Override
	public void update() {
		this.animateMarker(this.userMarker, this.userLocation.getCurrentLatLng());
	}
    
    /**
	 * Moves the user marker smoothly to a new position, code is taken from a google maps v2 demo app.
	 * http://stackoverflow.com/questions/13728041/move-markers-in-google-map-v2-android
	 * 
	 * @param marker	the marker that will be moved
	 * @param toPosition	the position to where the marker will be moved
	 */
	private void animateMarker(final Marker marker, final LatLng toPosition) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = this.mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final LinearInterpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
	
	@Override
	public void onPause() {
		super.onPause();
		this.userLocation.stopTrackingUser();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.userLocation.startTrackingUser();
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
        mMap.addMarker(MarkerOptionsFactory.createMarkerOptions(getResources(), drawable, pub.getName(), pub.getOpeningHours(),
                new LatLng(pub.getCoordinates().latitude, pub.getCoordinates().longitude)));
	}
}
