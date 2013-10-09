package se.chalmers.krogkollen.list;

import java.util.List;

import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.view.View;

public class ListPresenter implements IListPresenter {
	private IListView view;
	private ListModel model;
	
	public ListPresenter(IListView view){
		this.view = view;
		this.model = new ListModel();
	}
	@Override
	public void setView(IView view) {
		//this.view = view;
		
	}

	@Override
	public List<IPub> sortList(List<IPub> listToSort, SortMode sortMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		view.setActionBarSelectedNavigationItem(arg0);
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		view.setViewPagerCurrentItem(tab.getPosition());
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
}
