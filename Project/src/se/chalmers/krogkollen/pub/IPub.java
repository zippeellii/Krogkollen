package se.chalmers.krogkollen.pub;

public interface IPub {
	public String getName();
	
	public String getDescription();
	
	public String getOpeningHours();
	
	public int getAgeRestriction();
	
	public int getQueueTime();
	
	public int getQueueTimeLastUpdatedTimestamp();
	
	//public Point getCoordinates(); // TODO how to handle coordinates? sync with Google maps
}
