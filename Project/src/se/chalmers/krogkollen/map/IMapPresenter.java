package se.chalmers.krogkollen.map;

import java.util.List;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface for a MapPresenter
 * 
 * @author Oskar Kärrman
 *
 */
public interface IMapPresenter extends IPresenter {
	
	/**
	 * Indicates that a pub has been selected on the map
	 * 
	 * @param pub the pub which was selected
	 */
	public abstract void pubSelected(IPub pub);
	
	/**
	 * Start a search for an IPub object
	 * 
	 * @param search the string that the user searched for
	 * @return a list containing zero or more IPub objects that matched the search
	 */
	public abstract List<IPub> search(String search);
	
	/**
	 * Refresh information for all IPub objects
	 */
	public abstract void refresh();
}
