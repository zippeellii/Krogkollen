package se.chalmers.krogkollen.list;

import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import se.chalmers.krogkollen.IView;
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
 * A presenter for a list activity
 */
public class ListPresenter implements IListPresenter {

	private IListView	listView;
	private ListModel	model;

	/**
	 * Constructs a list presenter
	 * 
	 * @param view that the listpresenter should hold
	 */
	public ListPresenter(IListView view) {
		this.listView = view;
		this.model = new ListModel();
	}

	@Override
	public void setView(IView view) {
	}

	@Override
	public IPub[] sortList(ISort sortMode) {
		return model.getSortedArray(sortMode);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// not used in our current implementation
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// not used in our current implementation
	}

	@Override
	public void onPageSelected(int arg0) {
		listView.setActionBarSelectedNavigationItem(arg0);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// not used in our current implementation
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		listView.setViewPagerCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// not used in our current implementation
	}
}
