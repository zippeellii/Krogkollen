package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.utils.EQueueIndicator;

/**
 * Interface for a DetailedView
 * 
 * @author Oskar Karrman
 *
 */
public interface IDetailedView extends IView {

	/**
	 * Updates all text fields on the detailed view
	 */
	public void updateText(String pubName, String description, String openingHours, String age, String price);
	
	/**
	 * Updates the rating on the detailed view
	 */
	public void updateRating();
	
	/**
	 * Updates the queue indicator on the detailed view
	 */
	public void updateQueueIndicator(EQueueIndicator queueTime);
	
	/**
	 * Called when a refresh button is clicked
	 */
	public void refresh();

    public void updateVotes(String upVotes, String downVotes);

}
