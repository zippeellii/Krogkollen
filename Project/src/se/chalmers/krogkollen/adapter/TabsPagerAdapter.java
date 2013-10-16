package se.chalmers.krogkollen.adapter;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import se.chalmers.krogkollen.list.*;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// TODO javadoc
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public static final String SORT_MODE = "SORT_MODE";
 
    // TODO javadoc
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public ListFragment getItem(int index) {

        ListFragment fragment = new SortedListFragment();
        Bundle bundle = new Bundle();
 
        switch (index) {
        case 0:
            bundle.putInt(SORT_MODE, 0);
            break;
        case 1:
            bundle.putInt(SORT_MODE, 1);
            break;
        case 2:
            bundle.putInt(SORT_MODE, 2);
            break;
        }

        fragment.setArguments(bundle);
        return fragment;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
 
}