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
	
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	/*	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		
		return true;
	}*/
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		// BLA
		// Call detail activity for clicked entry
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			this.doSearch(query);
		}
	}
	
	private void doSearch(String query) {
		Log.d("OVWEHU", "DET VAR EN GÅNG EN LITEN FÅGELLLLLLL");
	}
}
