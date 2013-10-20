package se.chalmers.krogkollen.sort;

import java.util.List;

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
 * A class with a method for sorting a List of IPubs by queue time. 0 is considered as largest
 * 
 * @author Jonathan Nilsfors
 * 
 */
public class SortByQueueTime extends ASort {

	// This is an implementation of the well know Bubble sorting algorithm
	@Override
	public List<IPub> sortAlgorithm(final List<IPub> pubs) {

		// Makes a copy so the original list remains untouched
		List<IPub> copyOfPubs = this.copyPubList(pubs);
		for (int i = 1; i < copyOfPubs.size(); i++) {

			// Makes sure that the "largest" element is placed on the last index
			// that is not yet determined
			for (int j = 0; j < copyOfPubs.size() - i; j++) {

				// Switches index of two objects if the first one is considered to be
				// behind the second one. In this algorithm 0 is considered the largest element
				// therefore the extra statement if == 0
				if ((copyOfPubs.get(j).getQueueTime() > copyOfPubs.get(j + 1).getQueueTime()
						&& copyOfPubs.get(j + 1).getQueueTime() != 0)
						|| copyOfPubs.get(j).getQueueTime() == 0) {
					IPub temp = copyOfPubs.get(j);
					copyOfPubs.set(j, copyOfPubs.get(j + 1));
					copyOfPubs.set((j + 1), temp);
				}
			}
		}
		return copyOfPubs;
	}
}
