package se.chalmers.krogkollen.detailed;

import android.content.DialogInterface;
import android.view.View.OnClickListener;
import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

/**
 * Interface for a DetailedPresenter
 * 
 * @author Oskar Karrman
 *
 */
public interface IDetailedPresenter extends IPresenter, OnClickListener {

	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

    public void setPub(String pubID) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

    public void getQueueTime();

    public void getText();

    public void getThumbs();

    public void getVotes() throws NoBackendAccessException, NotFoundInBackendException;

    public void getFavoriteStar();

    public void saveFavoriteState();

    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException;

}
