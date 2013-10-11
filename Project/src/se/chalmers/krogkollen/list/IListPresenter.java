package se.chalmers.krogkollen.list;

import android.app.ActionBar.TabListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.search.ISort;

/**
 * Interface for the ListPresenter.
 * 
 * @author Albin Garpetun Created 2013-09-22
 */

public interface IListPresenter extends IPresenter, TabListener, OnPageChangeListener{

	/**
	 * Sorts a list of IPub objects according to specified SortMode
	 *
	 * @param sortMode how to sort
	 * @return the sorted list
	 */
	public IPub[] sortList(ISort sortMode);
}
