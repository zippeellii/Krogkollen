package se.chalmers.krogkollen.backend;

import java.util.List;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface describing methods for a backend
 * 
 * @author Oskar Karrman
 *
 */
public interface IBackend {

	/**
	 * @return a list of all pubs contained in the database
	 */
	public List<IPub> getAllPubs() throws NoBackendAccessException;

	/**
	 * Updates the queue time for a pub
	 * @param pub the pub which should be updated
	 * @param newQueueTime the new queue time for the pub
	 * @throws NoBackendAccessException
	 */
	public void updateQueueTime(IPub pub, int newQueueTime) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Updates the backend so all fields matches that of the specified IPub object
	 * 
	 * @param pub the IPub object
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void updateBackendPub(IPub pub) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Returns the queue time for the specified IPub object
	 * 
	 * @param pub the pub which queue time should be fetched from
	 * @return the current queue time for the specified pub
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public int getQueueTime(IPub pub) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Returns an IPub object with fields matching a pub in the backend with the specified ID
	 * 
	 * @param id the ID of the pub in the backend
	 * @return the pub matching the ID
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public IPub getPubFromID(String id) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Adds a rating vote to the backend
	 * 
	 * @param rating the rating vote to be added
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void addRatingVote(int rating) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Removes a rating vote to the backend
	 * 
	 * @param rating the rating vote to be removed
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void removeRatingVote(int rating) throws NoBackendAccessException, NotFoundInBackendException;
}
