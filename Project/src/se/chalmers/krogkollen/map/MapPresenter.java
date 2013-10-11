package se.chalmers.krogkollen.map;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.help.HelpActivity;
import se.chalmers.krogkollen.list.ListActivity;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.settings.SettingsActivity;
import se.chalmers.krogkollen.utils.ActivityID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Map presenter, doing the logic for the map view
 *
 * @author Oskar Karrman
 *
 */
public class MapPresenter implements IMapPresenter, GoogleMap.OnMarkerClickListener {

    /**
     * Key value used when sending intents from this class.
     */
    public static final String	MAP_PRESENTER_KEY	= "se.chalmers.krogkollen.MAP_PRESENTER_KEY";
    public static final int		PUB_REMOVED			= -1;
    public static final int		PUB_CHANGED			= 0;
    public static final int		PUB_ADDED			= 1;

    private IMapView			mapView;
    private UserLocation		userLocation;
    private Resources			resources;

    private SharedPreferences	sharedPref;
    private boolean				haveShownDialog		= false;
    private boolean				dontShowDialogAgain;

    @Override
    public void setView(IView view) {
        mapView = (IMapView) view;
        this.resources = this.mapView.getResources();
        this.sharedPref = mapView.getPreferences();
        this.dontShowDialogAgain = sharedPref.getBoolean(
                resources.getString(R.string.dont_show_again_key),
                resources.getBoolean(R.bool.dont_show_again_default));
        this.userLocation = UserLocation.getInstance();
        this.userLocation.addObserver(this);
        this.userLocation.startTrackingUser();

        // Use a default zoom if no position is found.
        if (userLocation.getCurrentLatLng() == null) {
            mapView.moveCameraToPosition(new LatLng(57.70887, 11.974613), MapActivity.DEFAULT_ZOOM);
        }
    }

    @Override
    public List<IPub> search(String search) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onActionBarClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_info:
                new RefreshTask().execute();
                break;
            case R.id.search:
                // Open search
                break;
            case R.id.go_to_my_location:
                // If no position has been found, show corresponding dialog, otherwise move the
                // camera to the users location.
                if (userLocation.getCurrentLatLng() == null) {
                    showDialog(userLocation.getProviderStatus(), false);
                } else {
                    mapView.moveCameraToPosition(userLocation.getCurrentLatLng(),
                            MapActivity.USER_ZOOM);
                }
                break;
            case R.id.action_help:
                mapView.navigate(HelpActivity.class);
                break;
            case R.id.action_settings:
                mapView.navigate(SettingsActivity.class);
                break;
            case android.R.id.home:
                Bundle bundle = new Bundle();
                bundle.putInt(ActivityID.ACTIVITY_ID, ActivityID.MAP);
                mapView.navigate(ListActivity.class, bundle);

                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        this.userLocation.onPause();
    }

    @Override
    public void onResume() {
        this.userLocation.onResume();
    }

    @Override
    public void pubMarkerClicked(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(MAP_PRESENTER_KEY, title);
        mapView.navigate(DetailedActivity.class, bundle);
    }

    @Override
    public void update(Status status) {
        // Update accordingly to what has happened in user location.
        if (status == Status.FIRST_LOCATION) {
            // User location has received a first location so a user marker is added and
            // map is centered on user.
            this.mapView.addUserMarker(this.userLocation.getCurrentLatLng());
            this.mapView.moveCameraToPosition(userLocation.getCurrentLatLng(),
                    MapActivity.USER_ZOOM);
        } else if (status == Status.NORMAL_UPDATE) {
            // The location has been updated, move the marker accordingly.
            this.mapView.animateUserMarker(this.userLocation.getCurrentLatLng());
        } else if ((status == Status.GPS_DISABLED || status == Status.NET_DISABLED || status == Status.ALL_DISABLED)
                &&
                !(this.haveShownDialog || this.dontShowDialogAgain)) {
            showDialog(status, true);
        }
    }

    // Showing the correct dialog for GPS and NET status.
    private void showDialog(Status status, boolean showCheckbox) {
        String baseMessage = resources.getString(R.string.alert_dialog_base_message);
        String additionalMessage = status == Status.NET_DISABLED || status == Status.ALL_DISABLED ?
                resources.getString(R.string.alert_dialog_net) : "";

        additionalMessage += status == Status.GPS_DISABLED || status == Status.ALL_DISABLED ?
                resources.getString(R.string.alert_dialog_gps) : "";

        this.mapView.showAlertDialog(baseMessage + additionalMessage, showCheckbox);
        this.haveShownDialog = true;
    }

    /**
     * Save information in the key values of Android.
     *
     * @param dontShowAgain true, don't show dialogs again; false, show dialogs again.
     */
    public void saveOption(boolean dontShowAgain) {
        // Save the don't show again option.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(resources.getString(R.string.dont_show_again_key), dontShowAgain);
        editor.commit();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Move camera to the clicked marker.
        mapView.moveCameraToPosition(marker.getPosition(), MapActivity.MARKER_ZOOM);

        if (marker.getTitle().equalsIgnoreCase(resources.getString(R.string.map_user_name))) {
            // TODO Open favorites.
        } else {
            // Open detailed view.
            Bundle bundle = new Bundle();
            bundle.putString(MAP_PRESENTER_KEY, marker.getId());
            mapView.navigate(DetailedActivity.class, bundle);
        }
        return true; // Suppress default behavior; move camera and open info window.
    }

    // Send the heavy work of recreating the markers to another thread.
    // Only changed pubs/markers will be altered.
    private class RefreshTask extends AsyncTask<Void, Void, Void>
    {
        // Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            mapView.showProgressDialog();
        }

        // The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params) {

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Save the old list of pubs.
                        List<IPub> oldPubs = new ArrayList<IPub>();
                        for (IPub pub : PubUtilities.getInstance().getPubList()) {
                            oldPubs.add(pub);
                        }
                        // Refresh with new pubs from the server.
                        PubUtilities.getInstance().refreshPubList();

                        // List containing only pubs that have been changed, removed or added.
                        HashMap<IPub, Integer> changedPubsHash = new HashMap<IPub, Integer>();

                        // Find the changed/removed pubs and add them to the new list; changedPubs.
                        for (IPub oldPub : oldPubs) {

                            // Used to find removed pubs.
                            boolean removed = true;
                            for (IPub newPub : PubUtilities.getInstance().getPubList()) {

                                // Check if the pub is in the saved list of old pubs.
                                if (oldPub.getID().equals(newPub.getID())) {
                                    removed = false;

                                    // Check if any of the relevant values for map markers has changed.
                                    if (oldPub.getQueueTime() != newPub.getQueueTime() ||
                                            oldPub.getLatitude() != newPub.getLatitude() ||
                                            oldPub.getLongitude() != newPub.getLongitude() ||
                                            !oldPub.getTodaysOpeningHours().toString().equals(newPub.getTodaysOpeningHours().toString()) ||
                                            !oldPub.getName().equals(newPub.getName())) {
                                        changedPubsHash.put(newPub, PUB_CHANGED);
                                    }
                                }
                            }
                            if (removed) {
                                changedPubsHash.put(oldPub, PUB_REMOVED);
                            }
                        }

                        // Check if any new pubs have been added to the server and add them to the HashMap.
                        for (IPub newPub : PubUtilities.getInstance().getPubList()) {
                            boolean added = true;
                            for (IPub oldPub : oldPubs) {
                                if (oldPub.getID().equals(newPub.getID())) {
                                    added = false;
                                }
                            }
                            if (added) {
                                changedPubsHash.put(newPub, PUB_ADDED);
                            }
                        }

                        MapWrapper.INSTANCE.refreshPubMarkers(changedPubsHash);
                    } catch (NoBackendAccessException e) {
                        mapView.showErrorMessage(resources.getString(R.string.error_no_backend_access));
                    } catch (NotFoundInBackendException e) {
                        mapView.showErrorMessage(resources.getString(R.string.error_no_backend_item));
                    } catch (BackendNotInitializedException e) {
                        mapView.showErrorMessage(resources.getString(R.string.error_no_backend_access));
                    }
                }
            });
            return null; // Nothing to return to the post execute.
        }

        // After executing the code in the thread
        @Override
        protected void onPostExecute(Void result)
        {
            mapView.hideProgressDialog();
        }
    }
}
