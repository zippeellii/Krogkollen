package se.chalmers.krogkollen.list;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.adapter.TabsPagerAdapter;
import se.chalmers.krogkollen.help.HelpActivity;
import se.chalmers.krogkollen.utils.Constants;

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
 * Activity for the list view. This shows a list that is sorted by a few default values, that the
 * user can chose between. Such as distance, queue-time or favorites.
 * 
 */
public class ListActivity extends FragmentActivity implements IListView {

	private ViewPager			viewPager;
	private TabsPagerAdapter	mAdapter;
	private ActionBar			actionBar;
	private String[]			tabs;
	private IListPresenter		presenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		// Tab titles
		tabs = new String[] { getString(R.string.list_tab_name_queue_time),
				getString(R.string.list_tab_name_distance),
				getString(R.string.list_tab_name_favorites) };

		// Instantiates
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		presenter = new ListPresenter(this);
		viewPager.setAdapter(mAdapter);

		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this.presenter));
		}

		viewPager.setOnPageChangeListener(presenter);

		// Remove the default logo icon and add our list icon.
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(R.drawable.map_icon);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	// Start the activity in a local method to keep the right context.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);

		return true;
	}

	@Override
	public void setActionBarSelectedNavigationItem(int pos) {
		actionBar.setSelectedNavigationItem(pos);
	}

	@Override
	public void setViewPagerCurrentItem(int pos) {
		viewPager.setCurrentItem(pos);
	}

	@Override
	public void navigate(Class<?> destination) {
		Intent intent = new Intent(this, destination);
		intent.putExtra(Constants.ACTIVITY_FROM, Constants.LIST_ACTIVITY_NAME);
		startActivity(intent);
	}

	@Override
	public void navigate(Class<?> destination, Bundle extras) {
		Intent intent = new Intent(this, destination);
		intent.putExtra(Constants.MARKER_PUB_ID, extras.getString(Constants.MAP_PRESENTER_KEY));
		intent.putExtra(Constants.ACTIVITY_FROM, Constants.LIST_ACTIVITY_NAME);
		startActivity(intent);
	}

	@Override
	public void showErrorMessage(String message) {
		CharSequence text = message;
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.refresh_info:
				mAdapter.notifyDataSetChanged();
				break;
			case R.id.search:
				this.onSearchRequested();
				break;
			case R.id.action_help:
				this.navigate(HelpActivity.class);
				break;
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void update() {
		mAdapter.notifyDataSetChanged();
	}
}