package se.chalmers.krogkollen.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.android.gms.maps.model.LatLng;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;

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
 * Interface for a MapView object
 * 
 * @author Oskar Karrman
 *
 */
public interface IMapView extends IView {
	
	/**
	 * Adds a pub to the map
	 * 
	 * @param pub the pub to be added
	 */
	//public void addPubToMap(IPub pub);

    /**
     * Adds all the pubs as markers in the map view. Uses the method addPubToMap.
     */
    //void addPubMarkers();

    /**
     * Adds the GPS-location of the phone as a marker in the map view.
     * @param location The location to be added.
     */
    void addUserMarker(LatLng latLng);

    Resources getResources();

    void showAlertDialog(String msg);
    
    void moveCameraToPosition(LatLng pos, int zoom);
    
    void animateUserMarker(final LatLng toPosition);
    
    SharedPreferences getPreferences(int context);
 }
