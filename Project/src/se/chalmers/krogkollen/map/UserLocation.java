package se.chalmers.krogkollen.map;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.utils.IObservable;
import se.chalmers.krogkollen.utils.IObserver;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

 /**
 * A class handling the phones current position.
 */
public class UserLocation implements LocationListener, IObservable{

	private static UserLocation instance = null;
	
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	private Location currentLocation;
	private LocationManager locationManager;
	private String NetLocationProvider = LocationManager.NETWORK_PROVIDER;
	private String GpsLocationProvider = LocationManager.GPS_PROVIDER;

    // Reading interval, used to determine which reading is better.
	private static final int TWO_MINUTES = 1000 * 60 * 2;

    /**
     * Instantiates a user location object.
     *
     * @param locationManager location manager from an activity.
     */
	private UserLocation(LocationManager locationManager){
		this.locationManager = locationManager;
		//Initiate first position of the user.
		if(isBetterLocation(this.locationManager.getLastKnownLocation(NetLocationProvider), this.locationManager.getLastKnownLocation(GpsLocationProvider))){
			this.currentLocation = this.locationManager.getLastKnownLocation(NetLocationProvider);
		} else {
			this.currentLocation = this.locationManager.getLastKnownLocation(GpsLocationProvider);
		}
	}
	
	/**
	 * Initiates a new UserLocation object with given LocationManager.
	 * 
	 * @param locationManager	LocationManager from an activity
	 */
	public static void init(LocationManager locationManager) {
		if(instance != null){
			throw new IllegalStateException("The user location has already been initialized!");
		}
		instance = new UserLocation(locationManager);
	}
	
	/**
	 * Returns an instance of this class if it's been initialized.
	 * 
	 * @return	an instance of UserLocation
	 */
	public static UserLocation getInstance() {
		if(instance == null){
			throw new IllegalStateException("The user location hasn't been initialized!");
		}
		return instance;
	}

    /**
     * Returns the current user location.
     *
     * @return current user location.
     */
	public Location getCurrentLocation(){
		return this.currentLocation;
	}

    /**
     * Get the current user location of the phone in latitude and longitude.
     *
     * @return current user location.
     */
	public LatLng getCurrentLatLng(){
		return new LatLng(this.currentLocation.getLatitude(), this.currentLocation.getLongitude()); // TODO sometimes get NullPointerException here
	}
	
	/**
	 * Stop tracking the users location (to save battery).
	 */
	public void stopTrackingUser() {
		locationManager.removeUpdates(this);
	}
	
	/**
	 * Start tracking the users location.
	 */
	public void startTrackingUser() {
		this.locationManager.requestLocationUpdates(this.NetLocationProvider, 0, 0, this);
		this.locationManager.requestLocationUpdates(this.GpsLocationProvider, 0, 0, this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if(isBetterLocation(location, this.currentLocation)){
			this.currentLocation = location;
		}
		for(IObserver observer: observers) {
			observer.update();
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
	    long timeDelta = location.getTime() - currentBestLocation.getTime(); // TODO Sometimes get NullPointerException here
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

	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
}
