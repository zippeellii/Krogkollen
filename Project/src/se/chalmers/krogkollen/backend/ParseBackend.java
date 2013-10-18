package se.chalmers.krogkollen.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.OpeningHours;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.utils.StringConverter;

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
 * A backend handling the connection between the client and the server. Uses parse.com as backend
 * provider
 * 
 * @author Jonathan Nilsfors
 * @author Oskar Karrman
 * 
 */
public class ParseBackend implements IBackend {

	// This exists to prevent the empty constructor to be called, since all information in the other
	// constructor is required
	@SuppressWarnings("unused")
	private ParseBackend() {
	}

	/**
	 * Initializes the backend to Parse.com, all information is required
	 * 
	 * @param context
	 * @param applicationID
	 * @param clientKey
	 */
	public ParseBackend(Context context, String applicationID, String clientKey) {
		Parse.initialize(context, applicationID, clientKey);
	}

	@Override
	public List<IPub> getAllPubs() throws NoBackendAccessException {

		// Instantiates the list to be returned
		List<IPub> tempPubList = new ArrayList<IPub>();

		// Fetches the requested query from the server
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");

		// Declares a List to be able to handle the query
		List<ParseObject> tempList;
		try {

			// Done to simplify the handling of the query
			// Makes it possible to handle as a java.util.List
			tempList = query.find();
			for (ParseObject object : tempList) {
				tempPubList.add(convertParseObjectToPub(object));
			}
		} catch (com.parse.ParseException e1) {
			throw new NoBackendAccessException(e1.getMessage());
		}
		return tempPubList;
	}

	@Override
	public int getQueueTime(IPub pub) throws NoBackendAccessException, NotFoundInBackendException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(pub.getID());
		} catch (ParseException e) {
			if (e.getCode() == ParseException.INVALID_KEY_NAME
					|| e.getCode() == ParseException.OBJECT_NOT_FOUND) {
				throw new NotFoundInBackendException(e.getMessage());
			} else {
				throw new NoBackendAccessException(e.getMessage());
			}
		}
		return object.getInt("queueTime");
	}

	@Override
	public IPub getPubFromID(String id) throws NoBackendAccessException, NotFoundInBackendException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(id);
		} catch (ParseException e) {
			if (e.getCode() == ParseException.INVALID_KEY_NAME
					|| e.getCode() == ParseException.OBJECT_NOT_FOUND) {
				throw new NotFoundInBackendException(e.getMessage());
			} else {
				throw new NoBackendAccessException(e.getMessage());
			}
		}
		return convertParseObjectToPub(object);
	}

	@Override
	public int getPositiveRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(pub.getID());
		} catch (ParseException e) {
			if (e.getCode() == ParseException.INVALID_KEY_NAME
					|| e.getCode() == ParseException.OBJECT_NOT_FOUND) {
				throw new NotFoundInBackendException(e.getMessage());
			} else {
				throw new NoBackendAccessException(e.getMessage());
			}
		}
		return object.getInt("posRate");
	}

	@Override
	public int getNegativeRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(pub.getID());
		} catch (ParseException e) {
			if (e.getCode() == ParseException.INVALID_KEY_NAME
					|| e.getCode() == ParseException.OBJECT_NOT_FOUND) {
				throw new NotFoundInBackendException(e.getMessage());
			} else {
				throw new NoBackendAccessException(e.getMessage());
			}
		}
		return object.getInt("negRate");
	}

	@Override
	public long getLatestUpdatedTimestamp(IPub pub) throws NoBackendAccessException,
			NotFoundInBackendException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(pub.getID());
		} catch (ParseException e) {
			throw new NotFoundInBackendException(e.getMessage());
		}
		return object.getLong("queueTimeLastUpdated");
	}

	/**
	 * A method for converting a ParseObject to an IPub
	 * 
	 * @param object the ParseObject
	 * @return the IPub representation of the ParseObject
	 */
	public static IPub convertParseObjectToPub(ParseObject object) {
		int hoursInFourDigits = 0;

		boolean pubClosed = false;

		// today == 1 if Sunday, 2 if Monday ... 7 if Saturday
		int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		if (hour < 6) {
			--today;
		}
		if (today < 1) {
			today = today + 7;
		}

		switch (today) {
			case Calendar.MONDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 1);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.TUESDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 2);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.WEDNESDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 3);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.THURSDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 4);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.FRIDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 5);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.SATURDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 6);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;

			case Calendar.SUNDAY:
				try {
					hoursInFourDigits = StringConverter.convertStringToFragmentedInt(object.getString("openingHours"), 7);
				} catch (IllegalArgumentException e) {
					pubClosed = true;
				}
				break;
		}

		OpeningHours openingHoursToday;

		if (pubClosed) {
			openingHoursToday = new OpeningHours();
		} else {
			openingHoursToday = new OpeningHours(hoursInFourDigits / 100, hoursInFourDigits % 100);
		}

		long queueTimeLastUpdatedTimestamp = object.getLong("queueTimeLastUpdated");
		int queueTime = object.getInt("queueTime");

		if (!queueTimeIsRecentlyUpdated(queueTimeLastUpdatedTimestamp)) {
			queueTime = 0;
		}

		return new Pub(object.getString("name"), object.getString("description"),
				object.getDouble("latitude"), object.getDouble("longitude"),
				object.getInt("ageRestriction"), object.getInt("entranceFee"),
				openingHoursToday, object.getInt("posRate"),
				object.getInt("negRate"), queueTime,
				queueTimeLastUpdatedTimestamp, object.getObjectId());
	}

	@Override
	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException {

		// Create a pointer to an object of class Pub
		ParseObject tempPub = ParseObject.createWithoutData("Pub", pub.getID());

		if (rating > 0) {
			tempPub.increment("posRate");
		} else {
			tempPub.increment("negRate");
		}

		// Save
		tempPub.saveInBackground(new SaveCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// object saved successfully
				} else {
					// TODO throw something from here, why is it not possible?
				}
			}
		});
	}

	@Override
	public void removeRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException {

		// Create a pointer to an object of class Pub
		ParseObject tempPub = ParseObject.createWithoutData("Pub", pub.getID());

		if (rating > 0) {
			tempPub.increment("posRate", -1);
		} else {
			tempPub.increment("negRate", -1);
		}

		// Save
		tempPub.saveInBackground(new SaveCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// object in backend updated successfully
				} else {
					// TODO throw something, how?
				}
			}
		});
	}

	@Override
	public void updatePubLocally(IPub pub) throws NoBackendAccessException,
			NotFoundInBackendException {
		ParseObject object = new ParseObject("Pub");

		try {
			object = ParseQuery.getQuery("Pub").get(pub.getID());
		} catch (ParseException e) {
			if (e.getCode() == ParseException.INVALID_KEY_NAME
					|| e.getCode() == ParseException.OBJECT_NOT_FOUND) {
				throw new NotFoundInBackendException(e.getMessage());
			} else {
				throw new NoBackendAccessException(e.getMessage());
			}
		}
		long lastUpdate = object.getLong("queueTimeLastUpdated");

		if (queueTimeIsRecentlyUpdated(lastUpdate)) {
			pub.setQueueTime(object.getInt("queueTime"));
		} else {
			pub.setQueueTime(0);
		}

		pub.setQueueTimeLastUpdatedTimestamp(lastUpdate);
		pub.setPositiveRating(object.getInt("posRate"));
		pub.setNegativeRating(object.getInt("negRate"));
	}

	// checks if the queue time was recently updated
	private static boolean queueTimeIsRecentlyUpdated(long queueTimeLastUpdatedTimestamp) {
		long epochTime = System.currentTimeMillis() / 1000;

		// if the queue time was updated more than 60 minutes ago, it wasn't updated recently
		if ((epochTime - queueTimeLastUpdatedTimestamp) > 3200) {
			return false;
		} else {
			return true;
		}
	}
}
