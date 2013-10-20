package se.chalmers.krogkollen.list;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.sort.ISort;

/**
 * A class containing logic for the list view.
 */
public class ListModel {
	private List <IPub> originalPubList;
	
	/**
	 * Creates a new ListModel
	 */
	public ListModel(){
		originalPubList = PubUtilities.getInstance().getPubList();
	}
	
	/**
	 * Returns a copy of the original pub list sorted as specified by algorithm
	 * 
	 * @param algorithm wanted to use
	 * @return the sorted list
	 */
	public IPub[] getSortedArray(ISort algorithm){
		List<IPub> sortedList = algorithm.sortAlgorithm(originalPubList);
		
		return convertListToArray(sortedList);
	}
	
	//Converts the java.util.list to Array
	private IPub[] convertListToArray(List <IPub> list){
		Pub[] pubArray = new Pub[list.size()];
		for(int i = 0; i < list.size(); i++){
			pubArray[i] = (Pub)list.get(i);
		}
		return pubArray;
	}
}
