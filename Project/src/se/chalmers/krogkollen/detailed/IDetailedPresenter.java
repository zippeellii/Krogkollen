package se.chalmers.krogkollen.detailed;

import android.content.DialogInterface;
import android.view.View.OnClickListener;
import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

/**
 * Interface for a DetailedPresenter
 * 
 * @author Oskar Karrman
 *
 */
public interface IDetailedPresenter extends IPresenter, OnClickListener {

	/**
	 * Indicates that rating for a pub has changed
	 *
	 * @param rating the new rating of the pub
	 */
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException;

    /**
     * Sets pub that is related to the presenter
     *
     * @param pubID The pubs ID
     */
    public void setPub(String pubID) throws NotFoundInBackendException, NoBackendAccessException;

    /**
     * Getter for queue time.
     * @return queue time
     */
    public void getQueueTime();

    public void getText();

    public void getThumbs();

    public void getVotes() throws NoBackendAccessException, NotFoundInBackendException;

    public void getFavoriteStar();

    public void saveFavoriteState();

    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException;

}
