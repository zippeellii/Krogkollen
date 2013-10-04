package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ListActivity extends Activity implements IListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}