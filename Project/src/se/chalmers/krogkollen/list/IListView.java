package se.chalmers.krogkollen.list;

import se.chalmers.krogkollen.IView;

/**
 * Interface for the ListActivity.
 * 
 * @author Albin Garpetun Created 2013-09-22
 */
public interface IListView extends IView {
	
	// TODO javadoc
    public void setActionBarSelectedNavigationItem(int pos);
    
    // TODO javadoc
    public void setViewPagerCurrentItem(int pos);
}
