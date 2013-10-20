package se.chalmers.krogkollen.sort;

import java.util.ArrayList;
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
 * An abstract class for sorting
 * 
 * @author Jonathan Nilsfors
 * 
 */
public abstract class ASort implements ISort {

	/**
	 * Copy a list
	 * 
	 * @param pubs the list of which a copy is required
	 * @return a copy of the param pubs
	 */
	public List<IPub> copyPubList(final List<IPub> pubs) {
		List<IPub> list_copy = new ArrayList<IPub>();
		for (int i = 0; i < pubs.size(); i++) {
			list_copy.add(pubs.get(i));
		}
		return list_copy;
	}
}
