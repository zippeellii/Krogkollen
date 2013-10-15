package se.chalmers.krogkollen.list;

import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.map.MapPresenter;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.sort.ISort;

// TODO javadoc
public class ListPresenter implements IListPresenter {

    private IListView listView;
    private ListModel model;

    public ListPresenter(IListView view){
        this.listView = view;
        this.model = new ListModel();
    }
    @Override
    public void setView(IView view) {
        //this.view = view;

    }

    @Override
    public IPub[] sortList(ISort sortMode) {
        return model.getSortedArray(sortMode);
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
        listView.setActionBarSelectedNavigationItem(arg0);
    }
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        listView.setViewPagerCurrentItem(tab.getPosition());

    }
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        //IPub pub = (IPub)adapterView.getItemAtPosition(i);
        bundle.putString(MapPresenter.MAP_PRESENTER_KEY, "aXqzzvQb7X");
        listView.navigate(DetailedActivity.class, bundle);
    }
}
