package se.chalmers.krogkollen.pub;

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
	 * @return the timestamp of when the queue time was last updated
	 */
	public int getQueueTimeLastUpdatedTimestamp();
	
	//public Point getCoordinates(); // TODO how to handle coordinates? sync with Google maps
}
