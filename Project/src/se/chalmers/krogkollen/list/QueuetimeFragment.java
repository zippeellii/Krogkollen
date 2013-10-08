package se.chalmers.krogkollen.list;

import se.chalmers.krogkollen.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QueuetimeFragment extends Fragment {

	
	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_queue_list, container, false);
	         
	        return rootView;
	    }
}
