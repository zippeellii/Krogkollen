package se.chalmers.krogkollen.backend;

import java.util.Date;
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
	
	/**
	 * Returns the number of positive ratings for a requested pub
	 * 
	 * @param pub the pub
	 * @return the number of positive ratings for the pub
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 */
	public int getPositiveRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;

	/**
	 * Returns the number of negative ratings for a requested pub
	 * 
	 * @param pub the pub
	 * @return the number of positive ratings for the pub
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException 
	 */
	public int getNegativeRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;
	
	/**
	 * Returns the date of which the pub was latest updated
	 * 
	 * @param pub the requested pub
	 * @return latest update date
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException 
	 */
	public Date getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;
	
	/**
	 * Updates the pub object with the current info about the queue time, positive and negative rating
	 * 
	 * @param pub the pub which values should be updated
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void updatePubLocally(IPub pub) throws NoBackendAccessException, NotFoundInBackendException;
}