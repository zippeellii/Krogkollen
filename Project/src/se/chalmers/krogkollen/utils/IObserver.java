package se.chalmers.krogkollen.utils;

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
 * Interface describing something that can observe an IObservable object
 */
public interface IObserver {

	/**
	 * Location service update indicators.
	 */
	public enum Status {
		FIRST_LOCATION,
		NORMAL_UPDATE,
		ALL_DISABLED,
		NET_DISABLED,
		GPS_DISABLED
	}

	/**
	 * Updates the observer.
	 */
	public void update(Status status);
}
