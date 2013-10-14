package se.chalmers.krogkollen.pub;

import java.util.Date;

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
 * A class representing a pub
 * 
 * @author Jonathan Nilsfors
 * @author Albin Garpetun
 * @author Oskar Karrman
 * 
 */
public class Pub implements IPub {

	private String			name;
	private String			description;
	private int				todaysOpeningHour;
	private int				todaysClosingHour;
	private double			latitude;
	private double			longitude;
	private int				ageRestriction;
	private int				queueTime;
	private int				entranceFee;
	private int				positiveRating;
	private int				negativeRating;
	private final String	ID;
	private long			queueTimeLastUpdatedTimestamp;

	/**
	 * Create a new Pub object with default values
	 */
	public Pub() {
		this("Name", "Description", 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, "ID");
	}

	/**
	 * Create a new Pub object
	 * 
	 * @param name the name
	 * @param description the description
	 * @param latitude the latitude position
	 * @param longitude the longitude position
	 * @param ageRestriction the age restriction
	 * @param entranceFee the entrance fee
	 * @param todaysOpeningHour todays opening hour
	 * @param todaysClosingHour todays closing hour
	 * @param positiveRating positive rating of the pub
	 * @param negativeRating negative rating of the pub
	 * @param queueTime the current queue time of the pub
	 * @param ID the ID of the pub
	 */
	public Pub(String name,
		String description,
		double latitude,
		double longitude,
		int ageRestriction,
		int entranceFee,
		int todaysOpeningHour,
		int todaysClosingHour,
		int positiveRating,
		int negativeRating,
		int queueTime,
		long queueTimeLastUpdatedTimestamp,
		String ID) 
	{
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ageRestriction = ageRestriction;
		this.entranceFee = entranceFee;
		this.todaysOpeningHour = todaysOpeningHour;
		this.todaysClosingHour = todaysClosingHour;
		this.positiveRating = positiveRating;
		this.negativeRating = negativeRating;
		this.queueTime = queueTime;
		this.queueTimeLastUpdatedTimestamp = queueTimeLastUpdatedTimestamp;
		this.ID = ID;
	}

	@Override
	public OpeningHours getTodaysOpeningHours() {
		return new OpeningHours(this.todaysOpeningHour, this.todaysClosingHour);
	}

	@Override
	public LatLng getCoordinates() {
		return new LatLng(this.latitude, this.longitude);
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
	public int getAgeRestriction() {
		return this.ageRestriction;
	}

	@Override
	public int getQueueTime() {
		return this.queueTime;
	}

	@Override
	public int getEntranceFee() {
		return this.entranceFee;
	}

	@Override
	public void setQueueTime(int queueTime) {
		// No negative time allowed
		if (queueTime < 0) {
			this.queueTime = 0;
		}
		else {
			this.queueTime = queueTime;
		}

	}

	@Override
	public String getID() {
		return this.ID;
	}

	@Override
	public int getPositiveRating() {
		return this.positiveRating;
	}

	@Override
	public void setPositiveRating(int rating) {
		this.positiveRating = rating;
	}

	@Override
	public int getNegativeRating() {
		return this.negativeRating;
	}

	@Override
	public void setNegativeRating(int rating) {
		this.negativeRating = rating;
	}

	@Override
	public int getTodaysOpeningHour() {
		return this.todaysOpeningHour;
	}

	@Override
	public int getTodaysClosingHour() {
		return this.todaysClosingHour;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public void setQueueTimeLastUpdatedTimestamp(long queueTimeLastUpdatedTimestamp) {
		this.queueTimeLastUpdatedTimestamp = queueTimeLastUpdatedTimestamp;
	}

	@Override
	public long getQueueTimeLastUpdatedTimestamp() {
		return this.queueTimeLastUpdatedTimestamp;
	}
}
