package se.chalmers.krogkollen.map;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;

/**
 * A class handling the phones current position.
 */
public class UserLocation implements LocationListener{

	private Location currentLocation;
	private LocationManager locationManager;

    // Reading interval, used to determine which reading is better.
	private static final int TWO_MINUTES = 1000 * 60 * 2;

    /**
     * Instantiate a user location object.
     *
     * @param locationManager location manager from an activity.
     */
	public UserLocation(LocationManager locationManager){
		this.locationManager = locationManager;
		
		//Initiate first position of the user.
		String locationProvider1 = LocationManager.NETWORK_PROVIDER;
		String locationProvider2= LocationManager.GPS_PROVIDER;
		if(isBetterLocation(this.locationManager.getLastKnownLocation(locationProvider1), this.locationManager.getLastKnownLocation(locationProvider2))){
			this.currentLocation = this.locationManager.getLastKnownLocation(locationProvider1);
		} else {
			this.currentLocation = this.locationManager.getLastKnownLocation(locationProvider2);
		}
	}

    /**
     * Returns current location.
     *
     * @return current location.
     */
	public Location getCurrentLocation(){
		return this.currentLocation;
	}

    /**
     * Get the current location of the phone in latitude and longitude.
     *
     * @return current location.
     */
	public LatLng getCurrentLatLng(){
		return new LatLng(this.currentLocation.getLatitude(), this.currentLocation.getLongitude());
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if(isBetterLocation(location, this.currentLocation)){
			this.currentLocation = location;
		}
	}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}
	
	/**
     * ** Method written by Google, found in Android training examples for locations. **
     *
     * Determines whether one Location reading is better than the current Location fix.
     *
	 * @param location  The new Location that you want to evaluate
	 * @param currentBestLocation  The current Location fix, to which you want to compare the new one
	 */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
	    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}
	
}
