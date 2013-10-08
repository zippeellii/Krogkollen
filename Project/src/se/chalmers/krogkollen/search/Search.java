package se.chalmers.krogkollen.search;

import se.chalmers.krogkollen.pub.*;
import java.util.ArrayList;
import java.util.List;

// TODO javadoc
public class Search {

	private static Search instance = null;
	
	private Search() {}
	
	public Search getInstance() {
		if(instance == null) {
			instance = new Search();
		}
		return instance;
	}
	
	/**
	 * Returns a list with all pubs that have names that contains the search string. (Temporary)
	 * 
	 * @param search	the string that will be search for in all the pubs names.
	 * @return			a list of pubs
	 */
	public List<IPub> search(String search) {
		List<IPub> allPubs = PubUtilities.getInstance().getPubList();
		
		List<IPub> matches = new ArrayList<IPub>();
		for(IPub pub: allPubs) {
			if(pub.getName().toLowerCase().contains(search)){ // TODO check this warning
				matches.add(pub);
			}
		}
		return matches;
	}
}
