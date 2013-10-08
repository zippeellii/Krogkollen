package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

// TODO javadoc
public interface IDetailedPresenter extends IPresenter {

	// TODO javadoc
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

	// TODO javadoc
    public void setPub(String pubID) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

    // TODO javadoc
    public void getQueueTime();

    // TODO javadoc
    public void getText();

    // TODO javadoc
    public void getThumbs();

    // TODO javadoc
    public void getVotes() throws NoBackendAccessException, NotFoundInBackendException;

    // TODO javadoc
    public void getFavoriteStar();

    // TODO javadoc
    public void saveFavoriteState();

    // TODO javadoc
    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException;

}
