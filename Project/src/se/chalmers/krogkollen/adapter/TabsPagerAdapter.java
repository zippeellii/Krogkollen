package se.chalmers.krogkollen.adapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import se.chalmers.krogkollen.list.*;
import se.chalmers.krogkollen.utils.Constants;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
 * An adapter class for fragment handling in the list view
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

	/**
	 * Creates a new TabsPagerAdapter
	 * 
	 * @param fm
	 */
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public ListFragment getItem(int index) {

		ListFragment fragment = new SortedListFragment();
		Bundle bundle = new Bundle();

		switch (index) {
			case 0:
				bundle.putInt(Constants.SORT_MODE, 0);
				break;
			case 1:
				bundle.putInt(Constants.SORT_MODE, 1);
				break;
			case 2:
				bundle.putInt(Constants.SORT_MODE, 2);
				break;
		}

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return Constants.LIST_NUMBER_OF_TABS;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}