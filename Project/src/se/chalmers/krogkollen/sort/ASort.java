package se.chalmers.krogkollen.sort;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

/**
 * An abstract class for sorting
 * @author Jonathan Nilsfors
 *
 */
public abstract class ASort implements ISort{

	/**
	 * Copy a list
	 * @param pubs the list of which a copy is required 
	 * @return a copy of the param pubs
	 */
	public List<IPub> copyPubList(final List<IPub> pubs){
		List<IPub> list_copy = new ArrayList<IPub>();
		for(int i = 0; i < pubs.size(); i++){
			list_copy.add(pubs.get(i));
		}
		return list_copy;
	}
}
