package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.adapter.*;

import java.util.List;


/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 */
public class ListActivity extends FragmentActivity implements IListView, ActionBar.TabListener{

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
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        
        //DETTA �R BARA F�R TEST SLUT
        
       /* presenter = new ListPresenter();
        presenter.setView(this);

        List<IPub> pubs = PubUtilities.getInstance().getPubList();
        pub_data = new IPub[pubs.size()];
        for(int i = 0; i < pubs.size(); i++){
            pub_data[i] = pubs.get(i);
        }

        PubListAdapter adapter = new PubListAdapter(this,
                R.layout.listview_item, pub_data);


        //listView = (ListView)findViewById(R.id.list_view);

        View header = getLayoutInflater().inflate(R.layout.listview_header, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);
<<<<<<< Updated upstream
        */
    
    
    /**
     * on swiping the viewpager make respective tab selected
     * */
    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // on changing the page
            // make respected tab selected
            actionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    });

    favoriteStarList = (ImageButton) findViewById(R.id.favorite_star_list);

    this.addListeners();
}
    /**
     * Add the presenter as listener to the buttons
     */
    private void addListeners(){
    	findViewById(R.id.distanceButton).setOnClickListener(presenter);
    	findViewById(R.id.queueButton).setOnClickListener(presenter);
    	findViewById(R.id.favoritesButton).setOnClickListener(presenter);
        findViewById(R.id.favorite_star_list).setOnClickListener(presenter);
    }

    public void updateStar(boolean isStarFilled){
        if(isStarFilled){
            favoriteStarList.setBackgroundResource(R.drawable.star_filled);
        }
        else{
            favoriteStarList.setBackgroundResource(R.drawable.star_not_filled);
        }
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    	viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}