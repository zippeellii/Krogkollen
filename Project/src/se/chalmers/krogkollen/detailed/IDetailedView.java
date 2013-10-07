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
	public void updateText(String pubName, String description, String openingHours, String age, String price);
	
	/**
	 * Updates the queue indicator on the detailed view
	 */
	public void updateQueueIndicator(int queueTime);
	
	/**
	 * Called when a refresh button is clicked
	 */
	public void refresh();

    /**
     * Updates the vote text.
     *
     * @param upVotes
     * @param downVotes
     */
    public void updateVotes(String upVotes, String downVotes);
}
