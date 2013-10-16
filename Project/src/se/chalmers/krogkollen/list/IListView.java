package se.chalmers.krogkollen.list;

import se.chalmers.krogkollen.IView;

/**
 * Interface for the ListActivity.
 * 
 * @author Albin Garpetun
 * Created 2013-09-22
 */
public interface IListView extends IView {
	
	/**
	 * Called when sliding between sort modes, selects the new tab
	 * 
	 * @param pos the tab which should be selected
	 */
    public void setActionBarSelectedNavigationItem(int pos);
    
    /**
     * Called when a tab is clicked, switches to the new tab
     * 
     * @param pos the tab which should be switched to
     */
    public void setViewPagerCurrentItem(int pos);
}
