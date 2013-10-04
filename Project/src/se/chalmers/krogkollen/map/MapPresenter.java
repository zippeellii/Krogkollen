package se.chalmers.krogkollen.map;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.help.HelpActivity;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.settings.SettingsActivity;

import java.util.List;

/**
 * A Map presenter, doing the logic for the map view
 * 
 * @author Oskar Karrman
 *
 */
public class MapPresenter implements IMapPresenter {

    /**
     * Key value used when sending intents from this class.
     */
    static final String MAP_PRESENTER_KEY = "se.chalmers.krogkollen.MAP_PRESENTER_KEY";

    private IMapView mapView;
    private UserLocation userLocation;
    private Resources resources;
    
    private SharedPreferences sharedPref;
    private boolean haveShownDialog = false;
    private boolean dontShowDialogAgain;

	@Override
	public void setView(IView view) {
		mapView = (IMapView) view;
		this.resources = this.mapView.getResources();
		this.sharedPref = mapView.getPreferences();
		this.dontShowDialogAgain = sharedPref.getBoolean(resources.getString(R.string.dont_show_again_key), 
				resources.getBoolean(R.bool.dont_show_again_default));
        this.userLocation = UserLocation.getInstance();
        this.userLocation.addObserver(this);
        this.userLocation.startTrackingUser();
	}

	@Override
	public void pubMarkerClicked(String pubId) {
        Bundle bundle = new Bundle();
        bundle.putString(MAP_PRESENTER_KEY, pubId);
        mapView.navigate(DetailedActivity.class, bundle);
	}

	@Override
	public List<IPub> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
        PubUtilities.getInstance().refreshPubList();
        try {
            MapWrapper.INSTANCE.refreshPubMarkers();
        } catch (NoBackendAccessException e) {
            mapView.showErrorMessage(mapView.getResources().getString(R.string.error_no_backend_access));
        } catch (NotFoundInBackendException e) {
            mapView.showErrorMessage(mapView.getResources().getString(R.string.error_no_backend_item));
        }
    }

    @Override
    public void onActionBarClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_info:
                PubUtilities.getInstance().refreshPubList();
                try {
                    MapWrapper.INSTANCE.refreshPubMarkers();
                } catch (NoBackendAccessException e) {
                    mapView.showErrorMessage(resources.getString(R.string.error_no_backend_access));
                } catch (NotFoundInBackendException e) {
                    mapView.showErrorMessage(resources.getString(R.string.error_no_backend_item));
                }
                break;
            case R.id.search:
                // Open search
                break;
            case R.id.go_to_my_location:
                if (userLocation.getCurrentLatLng() == null) {
                    showDialog(userLocation.getProviderStatus(), false);
                } else {
                    mapView.moveCameraToPosition(userLocation.getCurrentLatLng(), MapActivity.USER_ZOOM);
                }
                break;
            case R.id.action_help:
                mapView.navigate(HelpActivity.class);
                break;
            case R.id.action_settings:
                mapView.navigate(SettingsActivity.class);
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
    public void update(Status status) {
    	//Update accordingly to what has happened in user location.
        if(status == Status.FIRST_LOCATION) {
        	//User location has received a first location so a user marker is added and
        	//map is centered on user.
        	this.mapView.addUserMarker(this.userLocation.getCurrentLatLng());
        	this.mapView.moveCameraToPosition(userLocation.getCurrentLatLng(), MapActivity.USER_ZOOM);
        } else if(status == Status.NORMAL_UPDATE) {
        	//The location has been updated, move the marker accordingly.
        	this.mapView.animateUserMarker(this.userLocation.getCurrentLatLng());
        } else if ((status == Status.GPS_DISABLED || status == Status.NET_DISABLED || status == Status.ALL_DISABLED) && 
        			!(this.haveShownDialog || this.dontShowDialogAgain)) {
            showDialog(status, true);
        }
    }

    // Showing the correct dialog for GPS and NET status.
    private void showDialog(Status status, boolean showCheckbox) {
        String baseMessage = resources.getString(R.string.alert_dialog_base_message);
        String additionalMessage = status == Status.NET_DISABLED || status == Status.ALL_DISABLED ?
                resources.getString(R.string.alert_dialog_net): "";

        additionalMessage += status == Status.GPS_DISABLED || status == Status.ALL_DISABLED ?
                resources.getString(R.string.alert_dialog_gps): "";

        this.mapView.showAlertDialog(baseMessage + additionalMessage, showCheckbox);
        this.haveShownDialog = true;
    }

    /**
     * Save information in the key values of Android.
     *
     * @param dontShowAgain true, don't show dialogs again; false, show dialogs again.
     */
    public void saveOption(boolean dontShowAgain) {
    	//Save the don't show again option.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(resources.getString(R.string.dont_show_again_key), dontShowAgain);
        editor.commit();
    }
}
