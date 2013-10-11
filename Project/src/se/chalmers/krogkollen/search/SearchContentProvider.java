package se.chalmers.krogkollen.search;

import java.util.List;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class SearchContentProvider extends ContentProvider {

	private MatrixCursor cursor;
	// All columns we want to include in the auto complete list.
	String[] columns = {SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_TEXT_2, 
			SearchManager.SUGGEST_COLUMN_INTENT_DATA, SearchManager.SUGGEST_COLUMN_ICON_1, BaseColumns._ID};

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		//Find matches.
		String query = uri.getLastPathSegment().toLowerCase();

		List<IPub> allPubs = PubUtilities.getInstance().getPubList();
		List<IPub> matchingPubs = SearchActivity.getMatchingPubs(query, allPubs);

		//TODO Sort the list with matching pubs.

		cursor = new MatrixCursor(columns);
		
		//For every matching pub, add a corresponding row to the cursor.
		for (int i = 0; i < matchingPubs.size(); i++) {
			IPub pub = matchingPubs.get(i);
			int queue = pub.getQueueTime();
			int color;
			switch(queue) {
			case 1: 
				color = R.drawable.detailed_queue_green;
				break;
			case 2:
				color = R.drawable.detailed_queue_yellow;
				break;
			case 3:
				color = R.drawable.detailed_queue_red;
				break;
			default:
				color = R.drawable.detailed_queue_gray;
				break;
			}
			Object[] pubRow = {pub.getName(), pub.getTodaysOpeningHours().toString(), (pub.getID()), color, i};
			cursor.addRow(pubRow);
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}	
}
