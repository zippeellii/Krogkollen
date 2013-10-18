package se.chalmers.krogkollen.map;

import android.view.MenuItem;
import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.utils.IObserver;

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
 * Interface for a MapPresenter
 * 
 * @author Oskar Karrman
 * 
 */
public interface IMapPresenter extends IPresenter, IObserver {

	/**
	 * Determine what will happen when an action bar item is clicked.
	 * 
	 * @param item the menu item that was clicked.
	 */
	public void onActionBarClicked(MenuItem item);

	/**
	 * When the corresponding activity is paused this method gets called.
	 */
	public void onPause();

	/**
	 * When the corresponding activity is resumed this method gets called.
	 */
	public void onResume();

	/**
	 * Indicates that a pub marker has been clicked
	 * 
	 * @param id the id of the clicked pub
	 */
	void pubMarkerClicked(String id);
}
