package se.chalmers.krogkollen.backend;

import java.util.Date;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

public class BackendHandler {
	private static BackendHandler instance = null;
	private static IBackend backendInstance = null;
	
	// Private constructor to prevent accessibility
	private BackendHandler() {}
	
	/**
	 * Returns the instance for this singleton
	 * 
	 * @return the instance
	 */
	public static BackendHandler getInstance(){
		if(instance == null){
			instance = new BackendHandler();
		}
		return instance;
	}

	public void setBackend(IBackend backend){
		backendInstance = backend;
	}
	
	private void checkBackendInstance() throws BackendNotInitializedException {
		if (backendInstance == null) {
			throw new BackendNotInitializedException("Backend not initialized");
		}
	}

	public List<IPub> getAllPubs() throws NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getAllPubs();
	}

	public int getQueueTime(IPub pub) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getQueueTime(pub);
	}

	public IPub getPubFromID(String id) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getPubFromID(id);
	}

	public int getPositiveRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getPositiveRating(pub);
	}

	public int getNegativeRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getNegativeRating(pub);
	}

	public Date getLatestUpdatedTimestamp(IPub pub) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getLatestUpdatedTimestamp(pub);
	}

	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.addRatingVote(pub, rating);
	}

	public void removeRatingVote(IPub pub, int rating) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.removeRatingVote(pub, rating);
	}

	public void updatePubLocally(IPub pub) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.updatePubLocally(pub);
	}
}
