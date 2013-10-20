package se.chalmers.krogkollen.sort;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.utils.Preferences;

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
 * Filter to only show favorites
 */
public class FilterFavorites extends ASort {

	@Override
	public List<IPub> sortAlgorithm(List<IPub> pubs) {
		List<IPub> filteredList = new ArrayList<IPub>();
		for (IPub pub : pubs) {
			if (!Preferences.getInstance().loadPreference(pub.getID())) {
				filteredList.add(pub);
			}
		}
		return filteredList;
	}
}
