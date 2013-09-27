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
 * A class representing a pub
 * @author Jonathan Nilsfors
 * @author Albin Garpetun
 *
 */
public class Pub implements IPub{

	private String name;
	private String description;
	private String openingHours;
	private int ageRestriction;
	private int queueTime;
	private LatLng coordinates;
    private int entranceFee;
	private final String ID;
	
	/**
     * Instantiates the object, creating a dummy pub object.
     *
     * More constructors should be added, in case there is information missing.
     */
    public Pub () {
        this("Name", "Description", "00:00 - 00:00", 18, 15, 0.0, 0.0, "0");
    }

	/**
	 * A constructor for creating a new pub
	 * 
	 * @param name the name of the pub
	 * @param description a short description of the pub
	 * @param openingHours the pubs opening hours
	 * @param ageRestriction the age restriction
	 * @param queueTime the present queue time
	 * @param latitude the latitude coordinate
	 * @param longitude the longitude coordinate
	 * @param id the unique id of the pub
	 */
	public Pub(String name, String description, String openingHours, int ageRestriction, int queueTime, double latitude, double longitude, String id){
		this.name = name;
		this.description = description;
		this.openingHours = openingHours;
		this.ageRestriction = ageRestriction;
		this.setQueueTime(queueTime);
		this.ID = id;
        this.entranceFee = entranceFee;
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

	@Override
	public LatLng getCoordinates() {
		return new LatLng(this.coordinates.latitude, this.coordinates.longitude);
	}

    @Override
    public int getEntranceFee(){
        return entranceFee;
    }

	@Override
	public void setQueueTime(int queueTime) {
		//No negative time allowed
		if(queueTime < 0){
			this.queueTime = 0;
		}
		else{
			this.queueTime = queueTime;
		}

	}

	@Override
	public String getID() {
		return this.ID;
    }
}
