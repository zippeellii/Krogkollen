package se.chalmers.krogkollen.backend;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Interface describing methods for a backend
 * 
 * @author Oskar Karrman
 * 
 */
public interface IBackend {

	/**
	 * @return a list of all pubs contained in the backend
	 * @throws NoBackendAccessException
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
	 * @param pub the pub for which the rating should be added to
	 * @param rating the rating vote to be added
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException, NotFoundInBackendException;

	/**
	 * Removes a rating vote from the backend
	 * 
	 * @param pub the pub for which the rating should be removed from
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
	 * Returns the timestamp in seconds from The Epoch when the queue time was last updated
	 * 
	 * @param pub the requested pub
	 * @return the timestamp
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 */
	public long getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;

	/**
	 * Updates the pub object with the current info about the queue time, queue timestamp, positive
	 * and negative rating
	 * 
	 * @param pub the pub for which values should be updated
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 */
	public void updatePubLocally(IPub pub) throws NoBackendAccessException, NotFoundInBackendException;
}