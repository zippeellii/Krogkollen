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
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * MapWrapper (UTF-8)
 *
 * Singleton that wraps around a Google Maps v2 map, which makes
 * it possible to retrieve the same map system wide and instantiate from
 * loading screen.
 *
 * Author: Johan Backman
 * Author: Linnea Otterlind
 * Date: 2013-10-03
 */

enum MapWrapper {

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
        this.addPubMarkers();
    }

    // Add markers for all pubs on the server to the map.
    private void addPubMarkers() throws NoBackendAccessException, NotFoundInBackendException {
        IPub[] pubArray = new IPub[PubUtilities.getInstance().getPubList().size()];

        for (int i = 0; i < PubUtilities.getInstance().getPubList().size(); i++) {
            pubArray[i] = PubUtilities.getInstance().getPubList().get(i);
        }
        
        new CreateMarkerTask().execute(pubArray);
    }

    /**
     * Removes all pub markers, loads and adds them again.
     */
    public synchronized void refreshPubMarkers() throws NoBackendAccessException, NotFoundInBackendException {
        for(Marker pubMarker: this.pubMarkers){
            pubMarker.remove();
        }
        this.pubMarkers.clear();
        this.addPubMarkers();
    }

    /**
     * @return the map in the wrapper.
     */
    public GoogleMap getMap() {
        return this.googleMap;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    private class CreateMarkerTask extends AsyncTask<IPub, Void, List<MarkerOptions>> {

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(context, "", "Laddar pubbar...", false, false);
        }

        @Override
        protected List<MarkerOptions> doInBackground(IPub... pubs) {

            List<MarkerOptions> listMarkerOptions = new ArrayList<MarkerOptions>();

            for (int i = 0; i < pubs.length; i++) {
                IPub pub = pubs[i];

                int drawable = 0;

                // Determine which marker color to add.
                try {
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
                } catch (NoBackendAccessException e) {

                } catch (NotFoundInBackendException e) {

                }
                listMarkerOptions.add(MarkerOptionsFactory.createMarkerOptions(resources, drawable, pub.getName(), pub.getTodaysOpeningHour(),
                        new LatLng(pub.getLatitude(), pub.getLongitude()), pub.getID()));

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
