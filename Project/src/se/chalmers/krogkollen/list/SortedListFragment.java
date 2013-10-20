package se.chalmers.krogkollen.list;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.adapter.PubListAdapter;
import se.chalmers.krogkollen.adapter.TabsPagerAdapter;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.map.MapPresenter;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.sort.FilterFavorites;
import se.chalmers.krogkollen.sort.ISort;
import se.chalmers.krogkollen.sort.SortByDistance;
import se.chalmers.krogkollen.sort.SortByQueueTime;

/**
 * Created with IntelliJ IDEA.
 * User: Filip Carlén
 * Date: 2013-10-14
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class SortedListFragment extends ListFragment {

    private IPub[] data;
    private PubListAdapter adapter;
    private ISort sort;
    private ListModel model;
    private IListView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ListModel();
        data = setSortMode(getArguments().getInt(TabsPagerAdapter.SORT_MODE));
        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);

        adapter = new PubListAdapter(getActivity(), R.layout.listview_item, data, this);

        System.out.println(getActivity() instanceof ListActivity);

        view = (ListActivity) getActivity();

        setListAdapter(adapter);

        return rootView;
    }


    /**
     * Returns a sorted array of IPub objects, sort mode is chosen by the parameter
     * 
     * @param sortMode an integer representing which sort mode to be used. 0 for SortByQueueTime, 1 for SortByDistance, 2 for FilterFavorites
     * @return the sorted array
     */
    public IPub[] setSortMode(int sortMode){
        switch (sortMode){

            case 0:
                sort = new SortByQueueTime();
                break;

            case 1:
                sort = new SortByDistance();
                break;

            case 2:
                sort = new FilterFavorites();
                break;
        }
        return model.getSortedArray(sort);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(MapPresenter.MAP_PRESENTER_KEY, data[position].getID());
        view.navigate(DetailedActivity.class, bundle);
    }
    /**
     * Tells the view to update
     */
    public void update(){
       view.update();
    }
}

