package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

import java.util.List;


/**
 * Activity for the list view. This shows a list that is sorted by a few default values, that the user can chose between.
 * Such as distance, queue-time or favorites.
 *
 */
public class ListActivity extends FragmentActivity implements IListView, ActionBar.TabListener{

    private ListView listView;
    private IPub[] pub_data;
    private IListPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter();
        presenter.setView(this);

        List<IPub> pubs = PubUtilities.getInstance().getPubList();
        pub_data = new IPub[pubs.size()];
        for(int i = 0; i < pubs.size(); i++){
            pub_data[i] = pubs.get(i);
        }

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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}