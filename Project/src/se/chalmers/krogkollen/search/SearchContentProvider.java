package se.chalmers.krogkollen.search;

import java.util.List;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.sort.SortBySearchRelevance;
import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

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
 * A content provider needed for search suggestions
 * 
 * @author Oskar Karrman
 * 
 */
public class SearchContentProvider extends ContentProvider {

	private MatrixCursor	cursor;

	// All columns we want to include in the auto complete list.
	private String[]		columns	= { SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_TEXT_2,
									SearchManager.SUGGEST_COLUMN_INTENT_DATA, SearchManager.SUGGEST_COLUMN_ICON_1, BaseColumns._ID };

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// Find matches.
		String query = uri.getLastPathSegment().toLowerCase(); // TODO check error

		List<IPub> allPubs = PubUtilities.getInstance().getPubList();
		List<IPub> matchingPubs = SearchActivity.getMatchingPubs(query, allPubs);

		matchingPubs = new SortBySearchRelevance(query).sortAlgorithm(matchingPubs);

		cursor = new MatrixCursor(columns);

		// For every matching pub, add a corresponding row to the cursor.
		for (int i = 0; i < matchingPubs.size(); i++) {
			IPub pub = matchingPubs.get(i);
			int queue = pub.getQueueTime();
			int color;
			switch (queue) {
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
			Object[] pubRow = { pub.getName(), pub.getTodaysOpeningHours().toString(), (pub.getID()), color, i };
			cursor.addRow(pubRow);
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// not needed in our current implementation
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// not needed in our current implementation
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// not needed in our current implementation
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// not needed in our current implementation
		return null;
	}

	@Override
	public boolean onCreate() {
		// not needed in our current implementation
		return false;
	}
}
