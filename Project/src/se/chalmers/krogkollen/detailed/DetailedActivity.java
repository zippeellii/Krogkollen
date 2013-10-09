package se.chalmers.krogkollen.detailed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.MarkerOptionsFactory;
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
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 *
 <<<<<<< Updated upstream
 */

/**
 * This class represent the activity for the detailed view
 */
public class DetailedActivity extends Activity implements IDetailedView {

    /** The presenter connected to the detailed view */
    private IDetailedPresenter presenter;

    /** A bunch of view elements*/
    private TextView pubTextView, descriptionTextView,openingHoursTextView,
            ageRestrictionTextView, entranceFeeTextView, votesUpTextView, votesDownTextView;
    private ImageView thumbsUpImage, thumbsDownImage, queueIndicator;
    private MenuItem favoriteStar;
    private LinearLayout thumbsUpLayout, thumbsDownLayout;
    private ProgressDialog progressDialog;

    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        presenter = new DetailedPresenter();
        presenter.setView(this);

        try {
            presenter.setPub(getIntent().getStringExtra(MapActivity.MARKER_PUB_ID));
        } catch (NotFoundInBackendException e) {
            this.showErrorMessage(e.getMessage());
        } catch (NoBackendAccessException e) {
            this.showErrorMessage(e.getMessage());
        } catch (BackendNotInitializedException e) {
            this.showErrorMessage(e.getMessage());
        }

        addListeners();

        ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);
        scroll.setFadingEdgeLength(100);
        pubTextView= (TextView) findViewById(R.id.pub_name);
        descriptionTextView = (TextView) findViewById(R.id.description);
        openingHoursTextView = (TextView) findViewById(R.id.opening_hours);
        ageRestrictionTextView = (TextView) findViewById(R.id.age);
        entranceFeeTextView = (TextView) findViewById(R.id.entrance_fee);
        queueIndicator = (ImageView) findViewById(R.id.queueIndicator);
        votesUpTextView = (TextView) findViewById(R.id.thumbsUpTextView);
        votesDownTextView = (TextView) findViewById(R.id.thumbsDownTextView);
        thumbsUpImage = (ImageView) findViewById(R.id.thumbsUpButton);
        thumbsDownImage = (ImageView) findViewById(R.id.thumbsDownButton);
        thumbsUpLayout = (LinearLayout) findViewById(R.id.thumbsUpLayout);
        thumbsDownLayout = (LinearLayout) findViewById(R.id.thumbsDownLayout);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed, menu);
		favoriteStar = menu.findItem(R.id.favorite_star);

        try {
            presenter.updateInfo();
        } catch (NoBackendAccessException e) {
            this.showErrorMessage(e.getMessage());
        } catch (NotFoundInBackendException e) {
            this.showErrorMessage(e.getMessage());
        } catch (BackendNotInitializedException e) {
            this.showErrorMessage(e.getMessage());
        }

        return true;
    }

    @Override
    public void navigate(Class<?> destination) {
        Intent navigateBack = new Intent(this, destination);
        startActivity(navigateBack);

    }

    @Override
    public void showErrorMessage(String message) {
        // TODO This method should create a toast or some kind of window showing the error message
    }

    @Override
    public void updateText(String pubName, String description, String openingHours, String age, String price) {
        pubTextView.setText(pubName);
        descriptionTextView.setText(description);
        openingHoursTextView.setText(openingHours);
        ageRestrictionTextView.setText(age);
        entranceFeeTextView.setText(price);
    }

    @Override
    public void updateQueueIndicator(int queueTime) {
        switch(queueTime) {
            case 1:
                queueIndicator.setBackgroundResource(R.drawable.detailed_queue_green);
                break;
            case 2:
                queueIndicator.setBackgroundResource(R.drawable.detailed_queue_yellow);
                break;
            case 3:
                queueIndicator.setBackgroundResource(R.drawable.detailed_queue_red);
                break;
            default:
                queueIndicator.setBackgroundResource(R.drawable.detailed_queue_gray);
                break;
        }
    }

    @Override
    public void navigate(Class<?> destination, Bundle extras) {
        // TODO Auto-generated method stub

    }

    private void addListeners(){
        findViewById(R.id.thumbsUpLayout).setOnClickListener(presenter);
        findViewById(R.id.thumbsDownLayout).setOnClickListener(presenter);
    }

    /**
     * Updates the thumb button pictures.
     *
     * @param thumb Represents thumb up, thumb down or neither with 1, -1 or 0.
     */
    public void setThumbs(int thumb){
        switch (thumb){
            case -1:
                thumbsDownImage.setBackgroundResource(R.drawable.thumb_down_selected);
                thumbsUpImage.setBackgroundResource(R.drawable.thumb_up);
                break;
            case 1:
                thumbsUpImage.setBackgroundResource(R.drawable.thumb_up_selected);
                thumbsDownImage.setBackgroundResource(R.drawable.thumb_down);
                break;
            default:
                thumbsDownImage.setBackgroundResource(R.drawable.thumb_down);
                thumbsUpImage.setBackgroundResource(R.drawable.thumb_up);
                break;
        }


    }

    /**
     * Updates the text showing number of votes.
     *
     * @param upVotes Number of up votes.
     * @param downVotes Number of down votes.
     */
    public void showVotes(String upVotes, String downVotes){
        votesUpTextView.setText(upVotes);
        votesDownTextView.setText(downVotes);
    }

    @Override
    public void addMarker(IPub pub) {
        map.addMarker(MarkerOptionsFactory.createMarkerOptions(getResources().getDisplayMetrics() ,getResources(), pub);
    }

    @Override
    public void navigateToLocation(LatLng latLng, int zoom) {
        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, zoom, 0, 45)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){

            case R.id.favorite_star:
                presenter.updateStar();
                break;

            case R.id.refresh_info:

                try {
                    presenter.updateInfo();
                } catch (NoBackendAccessException e) {
                    this.showErrorMessage(e.getMessage());
                } catch (NotFoundInBackendException e) {
                    this.showErrorMessage(e.getMessage());
                } catch (BackendNotInitializedException e) {
                    this.showErrorMessage(e.getMessage());
                }
                break;

            case R.id.action_settings:
                break;
        }
        return true;
    }

    /**
     * Updates the star.
     * @param isStarFilled Represents if the star is filled or not.
     */
    public void showStar(boolean isStarFilled){

        if(isStarFilled){
            favoriteStar.setIcon(R.drawable.star_not_filled);
        }
        else{
            favoriteStar.setIcon(R.drawable.star_filled);
        }
    }


    public void showProgressDialog(){
        progressDialog = ProgressDialog.show(this, "","Uppdaterar", false, false);
    }

    public void hideProgressDialog(){
        progressDialog.hide();
    }
}