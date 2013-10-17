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
 * An exception indicating that a object which was supposed to be in the backend cannot be found
 * 
 * @author Oskar Karrman
 */
public class NotFoundInBackendException extends Exception {

	/**
	 * Generated serial version UID
	 */
	private static final long	serialVersionUID	= 303498786882068841L;

	/**
	 * Create a new NotFoundInBackendException without additional information
	 */
	public NotFoundInBackendException() {
		super();
	}

	/**
	 * Create a now NotFoundInBackendException with a specified message
	 * 
	 * @param message
	 */
	public NotFoundInBackendException(String message) {
		super(message);
	}
}