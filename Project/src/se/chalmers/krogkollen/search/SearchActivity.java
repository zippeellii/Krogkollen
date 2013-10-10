package se.chalmers.krogkollen.search;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.list.PubListAdapter;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class SearchActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		handleIntent(getIntent());
	}
	
	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO BLA
		// Call detail activity for clicked entry
	}

	// Checks if the intent passed is a search intent
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			this.doSearch(query);
		}
	}
	
	// Searches
	private void doSearch(String query) {
		List<IPub> allPubs = PubUtilities.getInstance().getPubList();

		List<IPub> matches = new ArrayList<IPub>();
		for (IPub pub : allPubs) {
			if (pub.getName().toLowerCase().contains(query.toLowerCase())) { // TODO check this warning
				matches.add(pub);
			}
		}
		
		IPub[] pubs = this.convertListToArray(matches);
		
		this.addMatchesToListView(pubs);
	}
	
	// Adds all the search matches to the listview
	private void addMatchesToListView(IPub[] pubs) {
		PubListAdapter adapter = new PubListAdapter(this,  R.layout.listview_item, pubs);
		
		getListView().setAdapter(adapter);
	}
	
	// TODO use in utils instead, temp
	private IPub[] convertListToArray(List <IPub> list) {
		Pub[] pubArray = new Pub[list.size()];
		for(int i = 0; i < list.size(); i++){
			pubArray[i] = (Pub)list.get(i);
		}
		return pubArray;
	}
}
