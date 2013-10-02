package se.chalmers.krogkollen.pub;

import java.util.LinkedList;
import java.util.List;

import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import android.content.res.Resources;

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
 * 
 */

/**
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
		
		try {
			pubList = Backend.getInstance().getAllPubs();
		} catch (NoBackendAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the list of pubs.
	 *
	 * @return The list of pubs.
	 */
	public List<IPub> getPubList() {
		return pubList;
	}

    /**
     * Returns a the pub connected with the ID given.
     *
     * @param id The ID of the pub to return
     * @return The pub according to the ID given
     */
    public IPub getPub(String id){
       for(IPub pub: pubList) {
            if(pub.getID().equals(id)){
                return pub;
            }
        }
        throw  new Resources.NotFoundException("The ID does not match with any pub");
    }
}
