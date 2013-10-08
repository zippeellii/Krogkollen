package se.chalmers.krogkollen.map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.BackendHandler;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapWrapper (UTF-8)
 *
 * Singleton that wraps around a Google Maps v2 map, which makes
 * it possible to retrieve the same map system wide and instantiate from
 * loading screen.
 *
 * @author Johan Backman
 * @author Linnea Otterlind
 * Date: 2013-10-03
 */
public enum MapWrapper {

    /**
     * Enum singleton instance variable.
     */
    INSTANCE;

    private GoogleMap googleMap;
    private List<Marker> pubMarkers;
    private Resources resources;
    private Context context;

    private ProgressDialog progressDialog;

    private MapWrapper() {} // Suppress instantiation

    /**
     * Initiate Google map resources and markers.
     *
     * @param activity from an activity.
     */
    public void init(Activity activity) throws NoBackendAccessException, NotFoundInBackendException {
        pubMarkers = new ArrayList<Marker>();
        googleMap = ((MapFragment) activity.getFragmentManager().findFragmentById(R.id.map)).getMap();
        this.resources = activity.getResources();
        this.context = activity;
        this.addPubMarkers(PubUtilities.getInstance().getPubList());
    }

    /**
     * Add markers for all pubs on the server to the map
     *
     * @throws NoBackendAccessException
     * @throws NotFoundInBackendException
     */
    // Add markers for all pubs on the server to the map.
    private void addPubMarkers(List<IPub> pubs) throws NoBackendAccessException, NotFoundInBackendException {
        IPub[] pubArray = new IPub[pubs.size()];

        for (int i = 0; i < pubs.size(); i++) {
            pubArray[i] = pubs.get(i);
        }
        new CreateMarkerTask().execute(pubArray);
    }

    /**
     * Removes all pub markers, loads and adds them again.
     */
    public synchronized void refreshPubMarkers(HashMap<IPub, Integer> changedPubsHash) throws NoBackendAccessException, NotFoundInBackendException {

        List<IPub> changedPubs = new ArrayList<IPub>();

        for (Map.Entry<IPub, Integer> entry: changedPubsHash.entrySet()) {

            IPub pub = entry.getKey();
            Integer integer = entry.getValue();

            // Find added and changed pubs
            if (integer == MapPresenter.PUB_CHANGED || integer == MapPresenter.PUB_ADDED) {
                changedPubs.add(pub);
            }

            // Just remove pub markers for pubs that currently got a marker on the map.
            for (Marker marker : pubMarkers) {
                if (pub.getID().equalsIgnoreCase(marker.getId())) {
                    marker.remove();
                    pubMarkers.remove(marker);
                }
            }
        }
        this.addPubMarkers(changedPubs);
    }

    /**
     * @return the map in the wrapper.
     */
    public GoogleMap getMap() {
        return this.googleMap;
    }

    /**
     * Sets the context of this wrapper
     * 
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    private class CreateMarkerTask extends AsyncTask<IPub, Void, List<MarkerOptions>> {

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(context, "", resources.getString(R.string.loading_pubs), false, false);
        }

        @Override
        protected List<MarkerOptions> doInBackground(IPub... pubs) {

            List<MarkerOptions> listMarkerOptions = new ArrayList<MarkerOptions>();

            for (int i = 0; i < pubs.length; i++) {
                IPub pub = pubs[i];

                int drawable = 0;

                // Determine which marker color to add.
                try {
                    switch (BackendHandler.getInstance().getQueueTime(pub)) {
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
                } catch (NoBackendAccessException e) {
                	// TODO this is a model, this should not be catched here, it must be shown in the view
                } catch (NotFoundInBackendException e) {
                	// TODO same as above
                } catch (BackendNotInitializedException e) {

				}
                listMarkerOptions.add(MarkerOptionsFactory.createMarkerOptions(resources, drawable, pub.getName(),
                        pub.getTodaysOpeningHours().toString(),new LatLng(pub.getLatitude(), pub.getLongitude()), pub.getID()));
            }
            return listMarkerOptions;
        }

        @Override
        protected void onPostExecute(List<MarkerOptions> markerOptions) {
            for (MarkerOptions markerOption : markerOptions) {
                pubMarkers.add(googleMap.addMarker(markerOption));
            }
            progressDialog.hide();
        }
    }
}
