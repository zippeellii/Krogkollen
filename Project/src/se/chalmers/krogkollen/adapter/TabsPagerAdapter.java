package se.chalmers.krogkollen.adapter;


import se.chalmers.krogkollen.list.DistanceFragment;
import se.chalmers.krogkollen.list.QueuetimeFragment;
import se.chalmers.krogkollen.list.FavoriteFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new QueuetimeFragment();
        case 1:
            // Games fragment activity
            return new DistanceFragment();
        case 2:
            // Movies fragment activity
            return new FavoriteFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}