package se.chalmers.krogkollen.list;

import android.support.v4.app.ListFragment;
import se.chalmers.krogkollen.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.krogkollen.search.SortByQueueTime;

public class QueuetimeFragment extends ListFragment {

	
	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

           ListModel model = new ListModel();
           View rootView = inflater.inflate(R.layout.fragment_queue_list, container, false);

           PubListAdapter adapter = new PubListAdapter(getActivity(), R.layout.listview_item, model.getSortedArray(new SortByQueueTime()));

           setListAdapter(adapter);

           return rootView;
	    }
}
