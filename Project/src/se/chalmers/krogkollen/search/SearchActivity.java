package se.chalmers.krogkollen.search;

import se.chalmers.krogkollen.R;
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
		Log.d("OVWEHU", query); // TODO do this
	}
}
