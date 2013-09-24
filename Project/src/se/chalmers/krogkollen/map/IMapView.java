package se.chalmers.krogkollen.map;

import com.google.android.gms.maps.model.LatLng;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface for a MapView object
 * 
 * @author Oskar Kärrman
 *
 */
public interface IMapView extends IView {
	
	/**
	 * Adds a pub to the map
	 * 
	 * @param pub the pub to be added
	 */
	public void addPubToMap(IPub pub);

    /**
     * Adds all the pubs as markers in the map view. Uses the method addPubToMap.
     */
    void addPubMarkers();

    /**
     * Adds the GPS-location of the phone as a marker in the map view.
     * @param location The location to be added.
     */
    void addUserMarker(LatLng latLng);
}
