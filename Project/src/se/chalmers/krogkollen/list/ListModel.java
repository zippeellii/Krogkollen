package se.chalmers.krogkollen.list;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;
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
 * A class containing data for the list view.
 */
public class ListModel {
	private List<IPub>	originalPubList;

	/**
	 * Creates a new ListModel
	 */
	public ListModel() {
		originalPubList = PubUtilities.getInstance().getPubList();
	}

	/**
	 * Returns a copy of the original pub list sorted as specified by algorithm
	 * 
	 * @param algorithm wanted to use
	 * @return the sorted list
	 */
	public IPub[] getSortedArray(ISort algorithm) {
		List<IPub> sortedList = algorithm.sortAlgorithm(originalPubList);

		return convertListToArray(sortedList);
	}

	// Converts the java.util.list to Array
	private IPub[] convertListToArray(List<IPub> list) {
		Pub[] pubArray = new Pub[list.size()];
		for (int i = 0; i < list.size(); i++) {
			pubArray[i] = (Pub) list.get(i);
		}
		return pubArray;
	}
}
