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
    private IListPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListPresenter();
        presenter.setView(this);

        pub_data = (IPub[]) PubUtilities.getInstance().getPubList().toArray();

        PubListAdapter adapter = new PubListAdapter(this,
                R.layout.listview_item, pub_data);


        listView = (ListView)findViewById(R.id.list_view);

        View header = getLayoutInflater().inflate(R.layout.listview_header, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);
        this.addListeners();
    }
    /**
     * Add the presenter as listener to the buttons
     */
    private void addListeners(){
    	findViewById(R.id.distanceButton).setOnClickListener(presenter);
    	findViewById(R.id.queueButton).setOnClickListener(presenter);
    	findViewById(R.id.favoritesButton).setOnClickListener(presenter);
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