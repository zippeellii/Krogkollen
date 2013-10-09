package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.adapter.*;


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
    private ListView listView;
    private IPub[] pub_data;
    private IListPresenter presenter;
    private ImageButton favoriteStarList;

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
    favoriteStarList = (ImageButton) findViewById(R.id.favorite_star_list);

}

    public void updateStar(boolean isStarFilled){
        if(isStarFilled){
            favoriteStarList.setBackgroundResource(R.drawable.star_filled);
        }
        else{
            favoriteStarList.setBackgroundResource(R.drawable.star_not_filled);
        }
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

	@Override
	public void refreshButtonClicked() {
		// TODO Auto-generated method stub
		
	}
}