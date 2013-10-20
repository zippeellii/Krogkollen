package se.chalmers.krogkollen.pub;

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
 * Class handling the opening and closing hours of a object If the object has opening hours then
 * those should be added to the constructor argument. If the object is closed then the empty
 * constructor should be used.
 * 
 * @author Philip Carlen
 * @author Oskar Karrman
 */
public class OpeningHours {

	private int		openingHour;
	private int		closingHour;
	private boolean	isOpen	= true;

	/**
	 * Creates a new OpeningHours object for a open object
	 * 
	 * @param openingHour the opening hour
	 * @param closingHour the closing hour
	 */
	public OpeningHours(int openingHour, int closingHour) {
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}

	/**
	 * Creates a new OpeningHours object for a closed object
	 */
	public OpeningHours() {
		this.isOpen = false;
	}

	/**
	 * @return the opening hour, returns null if it is closed
	 */
	public int getOpeningHour() {
		return this.openingHour;
	}

	/**
	 * @return the closing hour, returns null if it is closed
	 */
	public int getClosingHour() {
		return this.closingHour;
	}

	/**
	 * @return true if the it is open, false otherwise
	 */
	public boolean isOpen() {
		return this.isOpen;
	}

	@Override
	public String toString() {
		if (isOpen) {
			return (getOpeningHour() < 10 ? "0" + String.valueOf(getOpeningHour()) : String.valueOf(getOpeningHour())) + ":00 - " +
					(getClosingHour() < 10 ? "0" + String.valueOf(getClosingHour()) : String.valueOf(getClosingHour())) + ":00";
		} else {
			return "Stängt";
		}
	}
}
