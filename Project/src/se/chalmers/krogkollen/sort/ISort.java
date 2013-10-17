package se.chalmers.krogkollen.sort;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

/**
 * An interface for a sorting algorithm
 * 
 * @author Jonathan Nilsfors
 * 
 */
public interface ISort {
	/**
	 * Takes the list and returns a sorted copy
	 * 
	 * @param pubs the list that should be sorted
	 * @return the sorted list
	 */
	public List<IPub> sortAlgorithm(final List<IPub> pubs);
	
}
