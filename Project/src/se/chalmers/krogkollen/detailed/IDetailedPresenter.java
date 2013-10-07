package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

public interface IDetailedPresenter extends IPresenter {

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
