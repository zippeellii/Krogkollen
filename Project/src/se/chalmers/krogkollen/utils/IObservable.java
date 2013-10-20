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
 * Interface describing something which can be observed by an IObserver
 */
public interface IObservable {

	/**
	 * Adds an observer to the observable object.
	 * 
	 * @param observer the new observer
	 */
	public void addObserver(IObserver observer);

	/**
	 * Removes and observer from the observable object.
	 * 
	 * @param observer the observer that will be removed
	 */
	public void removeObserver(IObserver observer);
}
