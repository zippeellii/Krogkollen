package se.chalmers.krogkollen.list;

import java.util.List;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface for the ListPresenter.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public interface IListPresenter extends IPresenter{
	
	/**
	 * Sorts a list of IPub objects according to specified SortMode
	 * 
	 * @param listToSort a list which should be sorted
	 * @param sortMode how to sort
	 * @return the sorted list
	 */
	public List<IPub> sortList(List<IPub> listToSort, SortMode sortMode);
}
