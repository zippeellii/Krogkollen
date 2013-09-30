package se.chalmers.krogkollen.utils;

import se.chalmers.krogkollen.pub.*;
import java.util.ArrayList;
import java.util.List;

// TODO flytta denna till ett eget paket, sökning räknas som en feature
// TODO javadoc
public class Search {

	private static Search instance = null;
	
	// TODO javadoc, nothing inside method?
	private Search() {
		
	}
	
	// TODO javadoec
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
