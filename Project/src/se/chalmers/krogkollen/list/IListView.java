package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import se.chalmers.krogkollen.IView;

/**
 * Interface for the ListActivity.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public interface IListView extends IView{
	
	/**
	 * Called when the refresh button is clicked
	 */
    
    public void setActionBarSelectedNavigationItem(int pos);
    public void setViewPagerCurrentItem(int pos);

}
