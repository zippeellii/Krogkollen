package se.chalmers.krogkollen.map;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Interface for a MapView object
 * 
 * @author Oskar Kärrman
 *
 */
public interface IMapView extends IView {
	
	/**
	 * Adds a pub to the map
	 * 
	 * @param pub the pub to be added
	 */
	public void addPubToMap(IPub pub);
	
}
