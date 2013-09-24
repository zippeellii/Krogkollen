package se.chalmers.krogkollen.map;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.IObserver;

/**
 * The standard implementation of IMapView.
 *
 * This is a normal map with the user marked on the map, and with a list of pubs marked on the map.
 */
public class MapActivity extends Activity implements IMapView, IObserver{

    private GoogleMap mMap;
    private UserLocation userLocation;
    private Marker userMarker;
    private List<Marker> pubMarkers = new ArrayList<Marker>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		//get the map and add some markers for pubs.
        this.mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        this.addPubMarkers();
        
        //set focus of the map on the users location.
        this.userLocation = UserLocation.getInstance();
        this.userLocation.addObserver(this);
        this.userLocation.startTrackingUser();
        addUserMarker(this.userLocation.getCurrentLatLng());
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(this.userLocation.getCurrentLatLng(), 15, 0, 0)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	/**
	 * Adds a user marker to the map.
	 * 
	 * @param location the user location
	 */
	public void addUserMarker(LatLng latLng){
		userMarker = mMap.addMarker(new MarkerOptions()
						.position(latLng)
						.title("User")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.user_marker)));
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
	public void addPubToMap(IPub pub) {
        this.pubMarkers.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(pub.getCoordinates().latitude, pub.getCoordinates().longitude))
                .title(pub.getName() + ": " + pub.getDescription())));
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
	
}
