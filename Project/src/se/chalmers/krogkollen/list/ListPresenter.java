package se.chalmers.krogkollen.list;

import java.util.List;

import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Holds and handles the data for the ListActivity class.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ListPresenter implements IListPresenter, OnClickListener {
    
	IListView activity;
	
	/*
    This class should hold
        A list of all the pubs.
        Different search-states
        Somehow create a string that depends on the pub as well as the search-state.
    */

	@Override
	public void setView(IView view) {
		this.activity = (IListView)view;
		
	}

	@Override
	public List<IPub> sortList(List<IPub> listToSort, SortMode sortMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onClick(View v) {
		System.out.println("A button in the listview has been clicked");
		
	}

    public void saveThumbState(int thumb){
        SharedPreferences.Editor editor = activity.getSharedPreferences(pub.getID(), 0).edit();
        editor.putInt("thumb", thumb);
        editor.commit();
    }

    public void getFavoriteStar(){
        activity.updateStar(activity.getSharedPreferences(pub.getID(), 0).getBoolean("star", true));
    }
}
