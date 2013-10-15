package se.chalmers.krogkollen.list;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.sort.ISort;

/**
 * Created with IntelliJ IDEA.
 * User: filipcarlen
 * Date: 2013-10-14
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class SortedListFragment extends ListFragment {


    private IPub[] data;
    private IListPresenter presenter;

    public SortedListFragment(ISort sort, IListPresenter presenter){
        this.presenter = presenter;
        data = presenter.sortList(sort);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);

        PubListAdapter adapter = new PubListAdapter(getActivity(), R.layout.listview_item, data);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(presenter);

        return rootView;
    }

}

