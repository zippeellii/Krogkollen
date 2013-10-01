package se.chalmers.krogkollen.map;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.utils.IObservable;
import se.chalmers.krogkollen.utils.IObserver;
import se.chalmers.krogkollen.utils.IObserver.Status;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

/**
 * A class handling the phones current position.
 */
public class UserLocation implements LocationListener, IObservable {

	private static UserLocation instance = null;
	
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	private Location currentLocation = null;
	private LocationManager locationManager;
	private String netLocationProvider = LocationManager.NETWORK_PROVIDER;
	private String gpsLocationProvider = LocationManager.GPS_PROVIDER;
	private boolean netProviderEnabled = false;
	private boolean gpsProviderEnabled = false;

    // Reading interval, used to determine which reading is better.
	private static final int TWO_MINUTES = 1000 * 60 * 2;

    /**
     * Instantiates a user location object.
     *
     * @param locationManager location manager from an activity.
     */
	private UserLocation(LocationManager locationManager){
		this.locationManager = locationManager;
		
		Location netLocation = this.locationManager.getLastKnownLocation(this.netLocationProvider);
		Location gpsLocation = this.locationManager.getLastKnownLocation(this.gpsLocationProvider);
		
		//Initiate first position of the user if either gps or net providers are enabled.
		if(netLocation == null && gpsLocation != null) {
			this.currentLocation = gpsLocation;
			this.gpsProviderEnabled = true;
		} else if(netLocation != null && gpsLocation == null) {
			this.currentLocation = netLocation;
			this.netProviderEnabled = true;
		} else if(netLocation != null && gpsLocation != null){
			if(this.isBetterLocation(netLocation, gpsLocation)){
				this.currentLocation = netLocation;
			} else {
				this.currentLocation = gpsLocation;
			}
			this.gpsProviderEnabled = true;
			this.netProviderEnabled = true;
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
	
	public boolean hasLocation() {
		return this.currentLocation != null;
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
		if(this.currentLocation != null) {
			return new LatLng(this.currentLocation.getLatitude(), this.currentLocation.getLongitude());
		}
		return null;
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
		this.locationManager.requestLocationUpdates(this.netLocationProvider, 0, 0, this);
		this.locationManager.requestLocationUpdates(this.gpsLocationProvider, 0, 0, this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		Status status = Status.NORMAL_UPDATE;
		if(this.currentLocation == null) {
			this.currentLocation = location;
			status = Status.FIRST_LOCATION;
		} else if (isBetterLocation(location, this.currentLocation)){
			this.currentLocation = location;
		}
		
		for(IObserver observer: observers) {
			observer.update(status);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		if(provider.equals(this.netLocationProvider)) {
			this.netProviderEnabled = false;
		} else if(provider.equals(this.gpsLocationProvider)) {
			this.gpsProviderEnabled = false;
		}
		Status status = Status.NORMAL_UPDATE;
		if(!this.netProviderEnabled && !this.gpsProviderEnabled) {
			status = Status.ALL_DISABLED;
		} else if(!this.netProviderEnabled) {
			status = Status.NET_DISABLED;
		} else if(!this.gpsProviderEnabled) {
			status = Status.GPS_DISABLED;
		}
		for(IObserver observer: observers) {
			observer.update(status);
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		if(provider.equals(this.netLocationProvider)) {
			this.netProviderEnabled = true;
		} else if(provider.equals(this.gpsLocationProvider)) {
			this.gpsProviderEnabled = true;
		}
		Status status = Status.NORMAL_UPDATE;
		if(this.netProviderEnabled && this.gpsProviderEnabled) {
			status = Status.ALL_ENABLED;
		} else if(this.netProviderEnabled) {
			status = Status.NET_ENABLED;
		} else if(this.gpsProviderEnabled) {
			status = Status.GPS_ENABLED;
		}
		for(IObserver observer: observers) {
			observer.update(status);
		}
		this.locationManager.requestLocationUpdates(provider, 0, 0, this);
	}

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

	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
		if(this.currentLocation != null) {
			observer.update(Status.FIRST_LOCATION);
		}
	}

	@Override
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
}
