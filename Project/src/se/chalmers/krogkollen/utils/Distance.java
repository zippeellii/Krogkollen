package se.chalmers.krogkollen.utils;

import com.google.android.gms.maps.model.LatLng;

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
 * Class providing distance calculating methods.
 */
public class Distance {

	/**
	 * Calculate the distance between two points on earth using the Haversine formula. Since we need
	 * the great-circle distance from points latitude and longitude this formula is the most
	 * efficient and correct.
	 * 
	 * The full description of the algorithm can be found here:
	 * http://en.wikipedia.org/wiki/Haversine_formula.
	 * 
	 * @param first the first point.
	 * @param second the second point.
	 * @return the distance between the two points.
	 */
	public static double calcDistBetweenTwoLatLng(LatLng first, LatLng second) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(second.latitude - first.latitude);
		double dLng = Math.toRadians(second.longitude - first.longitude);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
				* Math.cos(Math.toRadians(first.latitude)) * Math.cos(Math.toRadians(second.latitude));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return earthRadius * c;
	}
}
