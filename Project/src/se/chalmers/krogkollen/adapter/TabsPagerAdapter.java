package se.chalmers.krogkollen.adapter;


import android.support.v4.app.ListFragment;
import se.chalmers.krogkollen.list.*;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import se.chalmers.krogkollen.sort.SortByDistance;
import se.chalmers.krogkollen.sort.SortByQueueTime;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
    private IListPresenter presenter;
 
    // TODO javadoc
    public TabsPagerAdapter(FragmentManager fm, IListPresenter presenter) {
        super(fm);
        this.presenter = presenter;
    }
 
    @Override
    public ListFragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new SortedListFragment(new SortByQueueTime(), presenter);
        case 1:
            // Games fragment activity
            return new SortedListFragment(new SortByDistance(), presenter);
        case 2:
            // Movies fragment activity
            return new SortedListFragment(new SortByDistance(), presenter);
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}