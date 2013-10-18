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
 * An exception indicating that an action to the backend failed. Causes could be, but not limited
 * to, not logged in, backend is offline, no permission to edit selected pub
 * 
 * @author Oskar Karrman
 */
public class NoBackendAccessException extends Exception {

	/**
	 * Generated serial version UID
	 */
	private static final long	serialVersionUID	= -7468080825750362150L;

	/**
	 * Create a new NoBackendAccessException without additional information
	 */
	public NoBackendAccessException() {
		super();
	}

	/**
	 * Create a new NoBackendAccessException with a message
	 * 
	 * @param message the message saved in the exception
	 */
	public NoBackendAccessException(String message) {
		super(message);
	}

	/**
	 * Create a new NoBackendAccessException with a message and an error code. Output for the
	 * message becomes: "errorcode: [errorcode], message: [message]
	 * 
	 * @param message
	 * @param errorcode
	 */
	public NoBackendAccessException(String message, int errorcode) {
		super("errorcode: " + errorcode + ", message: " + message);
	}
}
