package se.chalmers.krogkollen.list;

import android.app.ActionBar.TabListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.sort.ISort;

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
 */

/**
 * Interface for the ListPresenter.
 * 
 * @author Albin Garpetun Created 2013-09-22
 */
public interface IListPresenter extends IPresenter, TabListener, OnPageChangeListener {

	/**
	 * Sorts a list of IPub objects according to specified SortMode
	 * 
	 * @param sortMode how to sort
	 * @return the sorted list
	 */
	public IPub[] sortList(ISort sortMode);
}
