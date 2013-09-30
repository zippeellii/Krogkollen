package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface for a DetailedPresenter
 * 
 * @author Oskar Karrman
 *
 */
public interface IDetailedPresenter extends IPresenter {

	/**
	 * Indicates that rating for a pub has changed
	 * 
	 * @param pub the pub which rating has changed
	 * @param rating the new rating of the pub
	 */
	public void ratingChanged(IPub pub, int rating);
}
