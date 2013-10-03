package se.chalmers.krogkollen.map;

import android.app.FragmentManager;
import android.content.res.Resources;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * MapWrapper (UTF-8)
 * <p/>
 * Author: Johan Backman
 * Date: 2013-10-03
 */

public enum MapWrapper {

    INSTANCE;

    private GoogleMap googleMap;
    private List<Marker> pubMarkers;
    private Resources resources;

    private MapWrapper() {} // Suppress instantiation

    /**
     * Initiate Google map.
     *
     * @param fragmentManager from an activity.
     */
    public void init(FragmentManager fragmentManager, Resources resources) throws NoBackendAccessException, NotFoundInBackendException {
        pubMarkers = new ArrayList<Marker>();
        googleMap = ((MapFragment) fragmentManager.findFragmentById(R.id.map)).getMap();
        this.resources = resources;
        this.addPubMarkers();
    }

    public void addPubToMap(IPub pub) throws NoBackendAccessException, NotFoundInBackendException {
        int drawable;

        // Determine which marker color to add.
        switch (Backend.getInstance().getQueueTime(pub)) {
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
        pubMarkers.add(googleMap.addMarker(MarkerOptionsFactory.createMarkerOptions(resources, drawable, pub.getName(), pub.getTodaysOpeningHour(),
                new LatLng(pub.getLatitude(), pub.getLongitude()), pub.getID())));
    }

    private void addPubMarkers() throws NoBackendAccessException, NotFoundInBackendException {
        for (int i = 0; i < PubUtilities.getInstance().getPubList().size(); i++) {
            addPubToMap(PubUtilities.getInstance().getPubList().get(i));
        }
    }

    /**
     * Removes all pub markers and adds them again with new information.
     */
    public void refreshPubMarkers() throws NoBackendAccessException, NotFoundInBackendException {
        for(Marker pubMarker: this.pubMarkers){
            pubMarker.remove();
        }
        this.pubMarkers.clear();
        this.addPubMarkers();
    }

    /**
     *
     * @return
     */
    public GoogleMap getMap() {
        return this.googleMap;
    }

    /**
     *
     * @return
     */
    public List<Marker> getPubMarkers() {
        return this.pubMarkers;
    }
}
