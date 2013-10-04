package se.chalmers.krogkollen.list;

import se.chalmers.krogkollen.IView;

/**
 * Interface for the ListActivity.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public interface IListView extends IView{

	void showErrorMessage(String message);
}
