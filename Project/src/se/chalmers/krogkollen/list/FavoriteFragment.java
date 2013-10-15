package se.chalmers.krogkollen.list;

import android.support.v4.app.ListFragment;
import se.chalmers.krogkollen.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.krogkollen.sort.FilterFavorites;
import se.chalmers.krogkollen.sort.SortByQueueTime;

/**
 * A fragment showing a list with all favorites
 */
public class FavoriteFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ListModel model = new ListModel();
		View rootView = inflater.inflate(R.layout.fragment_favorites_list, container, false);

		PubListAdapter adapter = new PubListAdapter(getActivity(), R.layout.listview_item,
				model.getSortedArray(new FilterFavorites()));

		setListAdapter(adapter);

		return rootView;
	}
}
