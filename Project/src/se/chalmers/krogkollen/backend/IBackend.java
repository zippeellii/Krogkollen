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
	 * @param pub the pub for which the rating should be added
	 * @param rating the rating
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException, NotFoundInBackendException;
	
	/**
	 * Removes a rating vote to the backend
	 * 
	 * @param pub the pub for which the rating should be removed
	 * @param rating the rating vote to be removed
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void removeRatingVote(IPub pub, int rating) throws NoBackendAccessException, NotFoundInBackendException;
}