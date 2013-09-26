package se.chalmers.krogkollen.pub;

import com.google.android.gms.maps.model.LatLng;

/**
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
