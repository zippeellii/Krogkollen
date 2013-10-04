package se.chalmers.krogkollen.map;

import android.content.SharedPreferences;
import android.content.res.Resources;
import com.google.android.gms.maps.model.LatLng;
import se.chalmers.krogkollen.IView;

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
     * Adds the GPS-location of the phone as a marker in the map view.
     * @param latLng The location to be added.
     */
    public void addUserMarker(LatLng latLng);

    /**
     * @return resources.
     */
    public Resources getResources();

    /**
     * Shows an alert dialog with the given message and the option of adding a checkbox.
     *
     * @param msg message to be shown.
     * @param showCheckbox show a checkbox or not? :P
     */
    public void showAlertDialog(final String msg, final boolean showCheckbox);

    /**
     * Move the camera to the given position and zoom the given amount.
     *
     * @param pos the position to move to.
     * @param zoom zoom level.
     */
    public void moveCameraToPosition(LatLng pos, int zoom);

    /**
     * Animate the user marker from the current position to the new one.
     *
     * @param toPosition position to animate to.
     */
    public void animateUserMarker(final LatLng toPosition);

    /**
     * @return shared preferences of the activity.
     */
    public SharedPreferences getPreferences();

    /**
     * Shows loading progress in a dialog.
     */
    public void showProgressDialog();

    /**
     * Hide the loading dialog if it exists.
     */
    public void hideProgressDialog();
}
