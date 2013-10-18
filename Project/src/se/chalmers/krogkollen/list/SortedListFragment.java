package se.chalmers.krogkollen.list;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.adapter.PubListAdapter;
import se.chalmers.krogkollen.detailed.DetailedActivity;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.sort.FilterFavorites;
import se.chalmers.krogkollen.sort.ISort;
import se.chalmers.krogkollen.sort.SortByDistance;
import se.chalmers.krogkollen.sort.SortByQueueTime;
import se.chalmers.krogkollen.utils.Constants;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Created with IntelliJ IDEA. User: Filip Carlén Date: 2013-10-14 Time: 15:56
 */
public class SortedListFragment extends ListFragment {

	private IPub[]			data;
	private PubListAdapter	adapter;
	private ISort			sort;
	private ListModel		model;
	private IListView		view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		model = new ListModel();
		data = setSortMode(getArguments().getInt(Constants.SORT_MODE));
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
	 * @param sortMode an integer representing which sort mode to be used. 0 for SortByQueueTime, 1
	 *            for SortByDistance, 2 for FilterFavorites
	 * @return the sorted array
	 */
	public IPub[] setSortMode(int sortMode) {
		switch (sortMode) {

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
		bundle.putString(Constants.MAP_PRESENTER_KEY, data[position].getID());
		view.navigate(DetailedActivity.class, bundle);
	}

	/**
	 * Tells the view to update
	 */
	public void update() {
		view.update();
	}
}
