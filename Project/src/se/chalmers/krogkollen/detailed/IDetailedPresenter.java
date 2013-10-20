package se.chalmers.krogkollen.detailed;

import android.view.View.OnClickListener;
import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;

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
 * Interface for a DetailedPresenter
 * 
 * @author Oskar Karrman
 * 
 */
public interface IDetailedPresenter extends IPresenter, OnClickListener {

	/**
	 * Updates the thumb state.
	 * 
	 * @param rating represents thumb up, down or neutral.
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 * @throws BackendNotInitializedException
	 */
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

	/**
	 * Sets the pub which the presenter is connected to.
	 * 
	 * @param pubID the pub
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 * @throws BackendNotInitializedException
	 */
	public void setPub(String pubID) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException;

	/**
	 * Saves the favorite state locally.
	 */
	public void saveFavoriteState();

	/**
	 * Gets new information from the server
	 * 
	 * @throws NoBackendAccessException
	 * @throws NotFoundInBackendException
	 * @throws BackendNotInitializedException
	 */
	public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException;

	/**
	 * Updates the star
	 */
	public void updateStar();
}
