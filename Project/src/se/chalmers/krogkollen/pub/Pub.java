package se.chalmers.krogkollen.pub;
import com.google.android.gms.maps.model.LatLng;
/**
 * A class representing a pub
 * @author Jonathan Nilsfors
 *
 */
public class Pub implements IPub{
	
	private String name;
	private String description;
	private String openingHours;
	private int ageRestriction;
	private int queueTime;
	private LatLng coordinates;
	
	/**
	 * A constructor for creating a new pub
	 * @param name - the name of the pub
	 * @param description - a short description of the pub
	 * @param openingHours - the pubs opening hours
	 * @param ageRestriction - the age restriction
	 * @param queueTime- the present queue time
	 * @param latitude - the latitude coordinate
	 * @param longitude - the longitude coordinate
	 */
	public Pub(String name, String description, String openingHours, int ageRestriction, int queueTime, double latitude, double longitude){
		this.name = name;
		this.description = description;
		this.openingHours = openingHours;
		this.ageRestriction = ageRestriction;
		this.queueTime = queueTime;
		coordinates = new LatLng(latitude, longitude);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getOpeningHours() {
		return this.openingHours;
	}

	@Override
	public int getAgeRestriction() {
		return this.ageRestriction;
	}

	@Override
	public int getQueueTime() {
		return this.queueTime;
	}

	@Override
	public int getQueueTimeLastUpdatedTimestamp() {
		// TODO Auto-generated method stub
		return 0;
	}

}
