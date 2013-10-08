package se.chalmers.krogkollen.pub;

import android.content.res.Resources;
import se.chalmers.krogkollen.backend.BackendHandler;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;

import java.util.LinkedList;
import java.util.List;

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
	 * @throws BackendNotInitializedException 
	 * @throws NoBackendAccessException 
	 */
	public void loadPubList() throws NoBackendAccessException, BackendNotInitializedException {
		pubList = BackendHandler.getInstance().getAllPubs();
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
     * Refreshes the list of pubs from the server.
     * @throws BackendNotInitializedException 
     * @throws NoBackendAccessException 
     */
    public void refreshPubList() throws NoBackendAccessException, BackendNotInitializedException {
        pubList.clear();
        loadPubList();
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
        throw  new Resources.NotFoundException("The ID does not match with any pub");// TODO get this from strings.xml, no static text
    }
}
