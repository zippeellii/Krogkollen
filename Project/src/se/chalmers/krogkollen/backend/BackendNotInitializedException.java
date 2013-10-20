package se.chalmers.krogkollen.backend;

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
 * Exception which should be thrown if a backend has not been initialized
 * 
 * @author Oskar Karrman
 */
public class BackendNotInitializedException extends Exception {

	/**
	 * Generated serial version UID
	 */
	private static final long	serialVersionUID	= -5054620912828459605L;

	/**
	 * Create the exception without additional information
	 */
	public BackendNotInitializedException() {
		super();
	}

	/**
	 * Create the exception with the specified message
	 * 
	 * @param message
	 */
	public BackendNotInitializedException(String message) {
		super(message);
	}
}
