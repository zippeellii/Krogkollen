package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.adapter.*;
import se.chalmers.krogkollen.pub.IPub;


/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 */
public class ListActivity extends FragmentActivity implements IListView{

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "K�tid", "Distans", "Favoriter" };
    private IListPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        //Detta �r bara f�r TEST
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), presenter);
        
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        presenter = new ListPresenter(this);
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this.presenter));
        }

        viewPager.setOnPageChangeListener(presenter);

        //DETTA �R BARA F�R TEST SLUT

    }


    public void setActionBarSelectedNavigationItem(int pos){
    	actionBar.setSelectedNavigationItem(pos);
    }
    public void setViewPagerCurrentItem(int pos){
    	viewPager.setCurrentItem(pos);
    }

    @Override
    public void navigate(Class<?> destination) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void navigate(Class<?> destination, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

    public void favoriteStarClickHandler(View v){
    }
}