package se.chalmers.krogkollen.list;

import se.chalmers.krogkollen.IView;

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
 * Interface for the ListActivity.
 * 
 * @author Albin Garpetun Created 2013-09-22
 */
public interface IListView extends IView {

	/**
	 * Called when sliding between sort modes, selects the new tab
	 * 
	 * @param pos the tab which should be selected
	 */
	public void setActionBarSelectedNavigationItem(int pos);

	/**
	 * Called when a tab is clicked, switches to the new tab
	 * 
	 * @param pos the tab which should be switched to
	 */
	public void setViewPagerCurrentItem(int pos);

	/**
	 * Updates the listView
	 */
	public void update();
}
