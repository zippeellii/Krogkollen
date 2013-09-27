package se.chalmers.krogkollen.pub;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.ParseException;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
 * A singleton holding a list of all the pubs, and is used to load data from the server.
 *
 * For now this class only contains hard coded values, since we don't have support for a server.
 *
 * @author Albin Garpetun
 * Created 2013-09-22
 */
public class PubUtilities {


	private List<IPub> pubList = new LinkedList<IPub>();
	private static PubUtilities instance = null;

	private PubUtilities() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Creates an instance of this object if there is none, otherwise it simply returns the old one.
	 *
	 * @return The instance of the object.
	 */
	public static PubUtilities getInstance() {
		if(instance == null) {
			instance = new PubUtilities();
		}
		return instance;
	}

	/**
	 * Loads the pubs from the server and puts them in the list of pubs.
	 *
	 * For now this method only adds hardcoded pubs into the list.
	 */
	public void loadPubList() {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");
		List <ParseObject> tempList;
		try {
			tempList = query.find();
			for(ParseObject object : tempList){
				pubList.add(new Pub(object.getString("name"),
						object.getString("description"),
						object.getDouble("latitude"),
						object.getDouble("longitude"),
						object.getInt("ageRestriction"),
						object.getInt("entranceFee"),
						object.getInt("todaysOpeningHour"),
						object.getInt("todaysClosingHour"),
						object.getInt("posRate"),
						object.getInt("negRate"),
						object.getInt("queueTime"),
						object.getString("objectId")));

			}
		} catch (com.parse.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		/*query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parsePubList, com.parse.ParseException e){
				if(e == null){
					Log.d("pub", "Retrieved " + parsePubList.size() + " pubs");
					for(ParseObject object : parsePubList){
						pubList.add(new Pub(object.getString("name"),
								object.getString("description"),
								object.getString("openingHours"),
								object.getInt("ageRestriction"),
								object.getInt("queueTime"),
								object.getDouble("latitude"),
								object.getDouble("longitude"),
								object.getString("objectId")));

					}
				}
				else{
					Log.d("pub", "Error: " + e.getMessage());
				}
			}

		});
		Log.d("STR�NG", "PUBAR I LISTAN : " + pubList.size());
*/
		/*
		pubList.add(new Pub("Hubben 2.0", "IT's pub", "22:00-05:00", 18, 2, 57.688221, 11.979539, 1));
		pubList.add(new Pub("Kajsabaren", "E", "18:00-01:00", 18, 1, 57.688248, 11.978506, 2));
		pubList.add(new Pub("Databasen", "D", "19:00-01:00", 18, 3, 57.687502, 11.978667, 3));
		pubList.add(new Pub("Zaloonen", "Z", "19:00-01:00", 18, 2, 57.689094,11.979308, 4));
		pubList.add(new Pub("Bulten", "-", "18:00-01:00", 18, 2, 57.68924,11.978487, 5));
		pubList.add(new Pub("Winden", "M", "18:00-01:00", 18, 1, 57.689685,11.978004, 6));
		pubList.add(new Pub("Focus", "TF", "17:00-03:00", 18, 1, 57.691316,11.975523, 7));
		pubList.add(new Pub("Golden I", "I", "19:00-03:00", 18, 1, 57.693277,11.975391, 8));
		pubList.add(new Pub("Sigurd", "A", "19:00-03:00", 18, 2, 57.687818,11.976682, 9));
		pubList.add(new Pub("Röda rummet", "?", "19:00-03:00", 18, 3, 57.687783,11.976274, 10));
		pubList.add(new Pub("Järnvägspub", "?", "18:00 - 01:00", 18, 0, 57.688483,11.975867, 11));
		pubList.add(new Pub("Gasquen", "-", "20:00 - 03:00", 18, 3, 57.688862,11.975223, 12));
		pubList.add(new Pub("J.A. Pripps", "-", "17:00 - 02:00", 18, 2, 57.688988,11.974579, 13));
		pubList.add(new Pub("GasTown", "?", "19:00 - 01:00", 18, 1, 57.691144,11.978077, 14));
		pubList.add(new Pub("FortNOx", "?", "19:00 - 01:00", 18, 1, 57.691144,11.978077, 15));
		pubList.add(new Pub("Spritköket", "?", "19:00 - 01:00", 18, 2, 57.691144,11.978077, 16));
		pubList.add(new Pub("Club Avancez", "-", "19:00 - 03:00", 18, 0, 57.693289,11.976017, 17));
		pubList.add(new Pub("Pub P", "?", "18:00 - 01:00", 18, 1, 57.706922,11.939643, 18));
		pubList.add(new Pub("11:an", "?", "18:00 - 01:00", 18, 3, 57.706555,11.936639, 19));
		 */
	}

	/**
	 * Returns the list of pubs.
	 *
	 * @return The list of pubs.
	 */
	public List<IPub> getPubList() {
		return pubList;
	}
	
    public IPub getPub(int id){
        return pubList.get(id);
    }

}
