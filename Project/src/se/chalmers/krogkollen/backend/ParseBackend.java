package se.chalmers.krogkollen.backend;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.utils.StringConverter;

/**
 * A singleton backend handling the connection between the client and the server Uses parse.com as
 * backend provider
 * 
 * @author Jonathan Nilsfors
 * @author Oskar Karrman
 * 
 */
public class ParseBackend implements IBackend {

	// This exists to prevent the empty constructor to be called, since all information in the other
	// constructor is required
	@SuppressWarnings("unused")
	private ParseBackend() {}

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
				tempPubList.add(this.convertParseObjectToPub(object));
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
		return this.convertParseObjectToPub(object);
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
	public IPub convertParseObjectToPub(ParseObject object) {
		int hourFourDigit = StringConverter.convertStringToFragmentedInt(
				object.getString("openingHours"), 5);
		long queueTimeLastUpdatedTimestamp = object.getLong("queueTimeLastUpdated");
		int queueTime = object.getInt("queueTime");
		
		if (!queueTimeIsRecentlyUpdated(queueTimeLastUpdatedTimestamp)) {
			queueTime = 0;
		}
		
		return new Pub(object.getString("name"), object.getString("description"),
				object.getDouble("latitude"), object.getDouble("longitude"),
				object.getInt("ageRestriction"), object.getInt("entranceFee"),
				(hourFourDigit / 100), (hourFourDigit % 100), object.getInt("posRate"),
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
	private boolean queueTimeIsRecentlyUpdated(long queueTimeLastUpdatedTimestamp) {
		long epochTime = System.currentTimeMillis()/1000;
		
		// if the queue time was updated more than 30 minutes ago, it wasn't updated recently
		if ((epochTime - queueTimeLastUpdatedTimestamp) > 1800) {
			return false;
		} else {
			return true;
		}
	}
}
