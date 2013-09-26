package se.chalmers.krogkollen.pub;

import com.google.android.gms.maps.model.LatLng;

/**
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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Interface for a Pub object
 * 
 * @author Oskar Kärrman
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
	 * @return the opening hours of the pub
	 */
	public String getOpeningHours();
	
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
	 */
	public void setQueueTime(int queueTime);
	
	/**
	 * @return the timestamp of when the queue time was last updated
	 */
	public int getQueueTimeLastUpdatedTimestamp();
	
	/**
	 * @return the LatLng object for the pub, which describes the latitude and longitude coordinates
	 */
	public LatLng getCoordinates();

    public int getEntranceFee();
	
	/**
	 * @return the unique ID for the pub
	 */
	public int getID();
}
