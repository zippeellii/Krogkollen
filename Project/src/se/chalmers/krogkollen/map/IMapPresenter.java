package se.chalmers.krogkollen.map;

import java.util.List;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;

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
 *
 * Interface for a MapPresenter
 * 
 * @author Oskar Karrman
 *
 */
public interface IMapPresenter extends IPresenter {
	
	/**
	 * Indicates that a pub has been selected on the map
	 * 
	 * @param pub the pub which was selected
	 */
	public abstract void pubSelected(IPub pub);
	
	/**
	 * Start a search for an IPub object
	 * 
	 * @param search the string that the user searched for
	 * @return a list containing zero or more IPub objects that matched the search
	 */
	public abstract List<IPub> search(String search);
	
	/**
	 * Refresh information for all IPub objects
	 */
	public abstract void refresh();
}
