package se.chalmers.krogkollen.map;

import android.os.Bundle;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

import java.util.List;

/**
 * A Map presenter, doing the logic for the map view
 * 
 * @author Oskar Karrman
 *
 */
public class MapPresenter implements IMapPresenter {

    static final String MAP_PRESENTER_KEY = "se.chalmers.krogkollen.MAP_PRESENTER_KEY";

    private IMapView mapView;
    private UserLocation userLocation;

    public MapPresenter() {
        this.userLocation = UserLocation.getInstance();
        this.userLocation.addObserver(this);
        this.userLocation.startTrackingUser();
    }

	@Override
	public void setView(IView view) {
		mapView = (IMapView) view;

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
    public void onPause() {
        this.userLocation.onPause();
    }

    @Override
    public void onResume() {
        this.userLocation.onResume();
    }

    @Override
    public void update(Status status) {

    }
}
