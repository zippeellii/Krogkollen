package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;


/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 */
public class ListActivity extends Activity implements IListView {

    private ListView listView;
    private IPub[] pub_data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pub_data = (IPub[]) PubUtilities.getInstance().getPubList().toArray();

        PubListAdapter adapter = new PubListAdapter(this,
                R.layout.listview_item, pub_data);


        listView = (ListView)findViewById(R.id.list_view);

        View header = getLayoutInflater().inflate(R.layout.listview_header, null);
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