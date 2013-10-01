package se.chalmers.krogkollen.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.utils.StringConverter;
/**
 * A singleton backend handling the connection between the client and the server
 * @author Jonathan Nilsfors
 *
 */
public class Backend implements IParseBackend{
	private static Backend instance = null;
	/**
	 * Private constructor preventing accessibility 
	 */
	private Backend(){

	}
	/**
	 * Returns the instance for this singleton
	 * @return the instance
	 */
	public static Backend getInstance(){
		if(instance == null){
			instance = new Backend();
		}
		return instance;
	}
	/**
	 * Initializes the connection to the server
	 * @param context the conext
	 * @param applicationID of the appliaction
	 * @param clientKey of the client
	 */
	public static void init(Context context, String applicationID, String clientKey){
		Parse.initialize(context, applicationID, clientKey);
	}
	@Override
	public List<IPub> getAllPubs() throws NoBackendAccessException {
		List<IPub> tempPubList = new ArrayList<IPub>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");
		List <ParseObject> tempList;
		try {
			tempList = query.find();
			for(ParseObject object : tempList){
				int hourFourDigit = StringConverter.convertCombinedStringto(object.getString("openingHours"), 5);
				tempPubList.add(new Pub(object.getString("name"),
						object.getString("description"),
						object.getDouble("latitude"),
						object.getDouble("longitude"),
						object.getInt("ageRestriction"),
						object.getInt("entranceFee"),
						(hourFourDigit/100),
						(hourFourDigit%100),
						object.getInt("posRate"),
						object.getInt("negRate"),
						object.getInt("queueTime"),
						object.getObjectId()));

			}
		} catch (com.parse.ParseException e1) {
			throw new NoBackendAccessException(e1.getMessage());
		}
		return tempPubList;
	}

	@Override
	public void updateQueueTime(IPub pub, int newQueueTime)
			throws NoBackendAccessException, NotFoundInBackendException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBackendPub(IPub pub) throws NoBackendAccessException,
	NotFoundInBackendException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getQueueTime(IPub pub) throws NoBackendAccessException,
	NotFoundInBackendException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPub getPubFromID(String id) throws NoBackendAccessException,
	NotFoundInBackendException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRatingVote(int rating) throws NoBackendAccessException,
	NotFoundInBackendException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRatingVote(int rating) throws NoBackendAccessException,
	NotFoundInBackendException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPositiveRating(IPub pub) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNegativeRating(IPub pub) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getLatestUpdatedTimestamp(IPub pub) {
		// TODO Auto-generated method stub
		return null;
	}

}
