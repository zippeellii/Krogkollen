package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;

/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ListActivity extends Activity implements IListView {

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IPub pub_data[] = new IPub[]
                {
                        FILL DIS SHIT WITH A LOT OF PUBS
                };

        PubListAdapter adapter = new PubListAdapter(this,
                R.layout.listview_item, pub_data);


        listView = (ListView)findViewById(R.id.list_view);

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);
    }

	@Override
	public void navigate(Class<?> destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigate(Class<?> destination, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshButtonClicked() {
		// TODO Auto-generated method stub
		
	}
}