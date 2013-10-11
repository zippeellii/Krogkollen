package se.chalmers.krogkollen.backend;

import java.util.Date;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

/**
 * A singleton class that handles the current active backend.
 * 
 * @author Oskar Karrman
 * 
 */
public class BackendHandler {
	private static BackendHandler	instance		= null;
	private static IBackend			backendInstance	= null;

	// Private constructor to prevent accessibility
	private BackendHandler() {}

	/**
	 * Returns the instance for this singleton
	 * 
	 * @return the instance
	 */
	public static BackendHandler getInstance() {
		if (instance == null) {
			instance = new BackendHandler();
		}
		return instance;
	}

	/**
	 * Set the current backend
	 * 
	 * @param backend
	 */
	public void setBackend(IBackend backend) {
		backendInstance = backend;
	}

	/**
	 * @return a list containing all pubs in the current backend
	 * @throws NoBackendAccessException
	 * @throws BackendNotInitializedException
	 */
	public List<IPub> getAllPubs() throws NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getAllPubs();
	}

	/**
	 * @param pub
	 * @return the current queue time for the specified pub
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public int getQueueTime(IPub pub) throws NoBackendAccessException, NotFoundInBackendException,
			BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getQueueTime(pub);
	}

	/**
	 * 
	 * @param id
	 * @return the IPub object for the specified ID
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public IPub getPubFromID(String id) throws NoBackendAccessException,
			NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getPubFromID(id);
	}

	/**
	 * @param pub
	 * @return the positive rating for the specified IPub object
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 * @throws BackendNotInitializedException
	 */
	public int getPositiveRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getPositiveRating(pub);
	}

	/**
	 * 
	 * @param pub
	 * @return the negative rating for the specified IPub object
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 * @throws BackendNotInitializedException
	 */
	public int getNegativeRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getNegativeRating(pub);
	}

	/**
	 * 
	 * @param pub
	 * @return a Date object for when the specified pub was last updated in the current backend
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public Date getLatestUpdatedTimestamp(IPub pub) throws NoBackendAccessException,
			NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		return backendInstance.getLatestUpdatedTimestamp(pub);
	}

	/**
	 * Adds a rating vote for the specified pub in the current backend
	 * 
	 * @param pub
	 * @param rating the rating vote to add
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.addRatingVote(pub, rating);
	}

	/**
	 * Removes a rating vote for the specified pub in the current backend
	 * 
	 * @param pub
	 * @param rating the rating vote to remove
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public void removeRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.removeRatingVote(pub, rating);
	}

	/**
	 * Updates queue time and ratings in the specified IPub object to match the fields in the
	 * current backend
	 * 
	 * @param pub
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public void updatePubLocally(IPub pub) throws NoBackendAccessException,
			NotFoundInBackendException, BackendNotInitializedException {
		this.checkBackendInstance();
		backendInstance.updatePubLocally(pub);
	}

	// Check if there is a backend
	private void checkBackendInstance() throws BackendNotInitializedException {
		if (backendInstance == null) {
			throw new BackendNotInitializedException("Backend not initialized");
		}
	}
}
