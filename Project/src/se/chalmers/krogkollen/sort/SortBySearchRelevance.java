package se.chalmers.krogkollen.sort;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

/**
 * Sorts a List of IPub objects according to search relevance.
 * The string to search for is passed as an argument to the constructor,
 * then sortAlgorithm is called with a list of IPub objects. The list is
 * sorted by how much the pub name contains the search string.
 * 
 * @author Oskar Karrman
 *
 */
public class SortBySearchRelevance extends ASort {
	
	private String search;
	
	/**
	 * Create a new SortBySearchRelevance
	 * 
	 * @param searchString the string which a list should be sorted against
	 */
	public SortBySearchRelevance(String searchString) {
		this.search = searchString.toLowerCase();
	}
	
	@Override
	public List<IPub> sortAlgorithm(List<IPub> pubs) {
		List<IPub> listCopy = this.copyPubList(pubs);
		
		ASort secondarySort = new SortByDistance();
		secondarySort.sortAlgorithm(listCopy);

		
		for(int i = 1; i < listCopy.size(); i++){
			for(int j = 0; j < listCopy.size()-i; j++){
				if(this.matchScore(listCopy.get(j).getName()) < this.matchScore(listCopy.get(j+1).getName())){
					IPub temp = listCopy.get(j);
					listCopy.set(j, listCopy.get(j + 1));
					listCopy.set((j+1), temp);
				}
			}
		}
		
		return listCopy;
	}
	
	// evaluates how much a string matches the search string
	// the score starts at 100 and decreases if it doesn't meet conditions
	private int matchScore(String stringToEvaluate){
		stringToEvaluate = stringToEvaluate.toLowerCase();
		
		int currentScore = 100;
		
		if(stringToEvaluate.equals(search)) {
			return currentScore;
		} else {
			currentScore--;
		}
		
		// if the string contains the search, give shorter strings higher points
		if(stringToEvaluate.contains(search)) {
			currentScore = currentScore - (stringToEvaluate.length() - search.length());
		} else { // if the string doesn't contain the search, give shorter strings higher points
			currentScore = currentScore - stringToEvaluate.length();
		}
		
		return currentScore;
	}
}
