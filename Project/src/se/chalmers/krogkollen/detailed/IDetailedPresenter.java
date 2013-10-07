package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

/**
 * Interface for a DetailedPresenter
 * 
 * @author Oskar Karrman
 */
public interface IDetailedPresenter extends IPresenter {

	/**
	 * Indicates that rating for a pub has changed
	 *
	 * @param rating the new rating of the pub
	 * @author Oskar Karrman
	 */
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException;

    /**
     * Sets pub that is related to the presenter
     *
     * @param pubID The pubs ID
     * @author Oskar Karrman
     */
    public void setPub(String pubID) throws NotFoundInBackendException, NoBackendAccessException;

    public void getQueueTime();

    public void getText();

    public void getThumbs();

    public void getVotes() throws NoBackendAccessException, NotFoundInBackendException;

    public void getFavoriteStar();

    public void saveFavoriteState();

    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException;

}
