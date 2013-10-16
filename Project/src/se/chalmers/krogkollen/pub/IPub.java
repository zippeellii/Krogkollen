package se.chalmers.krogkollen.pub;

import com.google.android.gms.maps.model.LatLng;

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
 * 
 * Interface for a Pub object
 * 
 * @author Oskar Karrman
 * 
 */
public interface IPub {

	/**
	 * @return the name of the pub
	 */
	public String getName();

	/**
	 * @return the description of the pub
	 */
	public String getDescription();

	/**
	 * @return the age restriction of the pub
	 */
	public int getAgeRestriction();

	/**
	 * @return the current queue time of the pub
	 */
	public int getQueueTime();

	/**
	 * Sets the queuetime for the pub
	 * @param queueTime the queue time of the pub
	 */
	public void setQueueTime(int queueTime);

	/**
	 * @return the latitude of this pub
	 */
	public double getLatitude();

	/**
	 * @return the longitude of this pub
	 */
	public double getLongitude();

	/**
	 * @return the coordinates of this pub as a LatLng object
	 */
	public LatLng getCoordinates();
	
	/**
	 * @return the entrance fee for the pub
	 */
	public int getEntranceFee();

	/**
	 * @return the positive rating of the pub
	 */
	public int getPositiveRating();

	/**
	 * sets the number of positive ratings
	 * 
	 * @param rating
	 */
	public void setPositiveRating(int rating);

	/**
	 * @return the negative rating of the pub
	 */
	public int getNegativeRating();

	/**
	 * sets the number of negative ratings
	 * 
	 * @param rating
	 */
	public void setNegativeRating(int rating);

	/**
	 * @return the unique ID for the pub
	 */
	public String getID();

	/**
	 * @return todays opening and closing hours
	 */
	public OpeningHours getTodaysOpeningHours();
	
	/**
	 * @param queueTimeLastUpdated the time the queue time was last updated
	 */
	public void setQueueTimeLastUpdatedTimestamp(long queueTimeLastUpdatedTimestamp);
	
	/**
	 * @return the time the queue time was last updated
	 */
	public long getQueueTimeLastUpdatedTimestamp();
}
