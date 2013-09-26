package se.chalmers.krogkollen;

/**
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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Interface for a View class in the MVP design pattern.
 * 
 * @author Oskar Kärrman
 *
 */
public interface IView {
	
	/**
	 * Navigates to another view
	 * 
	 * @param destination
	 */
	public abstract void navigate(Class<?> destination);
	
	public abstract void showErrorMessage(String message);
}
