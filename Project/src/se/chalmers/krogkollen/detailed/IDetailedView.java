package se.chalmers.krogkollen.detailed;

import se.chalmers.krogkollen.IView;

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
	public void updateText();
	
	/**
	 * Updates the rating on the detailed view
	 */
	public void updateRating();
	
	/**
	 * Updates the queue indicator on the detailed view
	 */
	public void updateQueueIndicator();
	
	/**
	 * Called when a refresh button is clicked
	 */
	public void refresh();
}
