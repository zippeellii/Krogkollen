package se.chalmers.krogkollen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.list.SortedListFragment;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.utils.Distance;
import se.chalmers.krogkollen.utils.Preferences;

import java.text.*;

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
 * An adapter handling the different items in a list
 */
public class PubListAdapter extends ArrayAdapter<IPub> {

	private Context				context;
	private int					layoutResourceId;
	private IPub				data[]	= null;
	private View				row;
	private PubHolder			holder;
	private SortedListFragment	fragment;

	/**
	 * A constructor that creates an PubListAdapter.
	 * 
	 * @param context
	 * @param layoutResourceId
	 * @param data
	 */
	public PubListAdapter(Context context, int layoutResourceId, IPub[] data, SortedListFragment fragment) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.fragment = fragment;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		row = convertView;
		holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new PubHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.listview_image);
			holder.txtTitle = (TextView) row.findViewById(R.id.listview_title);
			holder.distanceText = (TextView) row.findViewById(R.id.listview_distance);
			holder.favoriteStar = (ImageButton) row.findViewById(R.id.favorite_star_list);

			row.setTag(holder);
		} else {
			holder = (PubHolder) row.getTag();
		}

		updateStar(Preferences.getInstance().loadPreference(this.getItem(position).getID()), holder);

		IPub pub = data[position];
		DecimalFormat numberFormat = new DecimalFormat("#0.00");
		holder.txtTitle.setText(pub.getName());
		holder.distanceText.setText("" + (numberFormat.format(Distance.calcDistBetweenTwoLatLng(new LatLng(pub.getLatitude(), pub.getLongitude()), UserLocation.getInstance().getCurrentLatLng())))
				+ " km"); // TODO possible to get this in xml?
		holder.favoriteStar.setTag(position);

		holder.favoriteStar.setOnClickListener(new View.OnClickListener() {
			PubHolder	tmp	= holder;

			@Override
			public void onClick(View v) {
				int pos = (Integer) v.getTag();
				saveFavoriteState(pos);
				updateStar(Preferences.getInstance().loadPreference(data[pos].getID()), tmp);
				fragment.update();

			}
		});

		switch (pub.getQueueTime()) {
			case 1:
				holder.imgIcon.setImageResource(R.drawable.detailed_queue_green);
				break;
			case 2:
				holder.imgIcon.setImageResource(R.drawable.detailed_queue_yellow);
				break;
			case 3:
				holder.imgIcon.setImageResource(R.drawable.detailed_queue_red);
				break;
			default:
				holder.imgIcon.setImageResource(R.drawable.detailed_queue_gray);
				break;
		}
		return row;
	}

	/**
	 * Saves the state of the favorite locally
	 */
	public void saveFavoriteState(int pos) {
		Preferences.getInstance().savePreference(data[pos].getID(), !Preferences.getInstance().loadPreference(data[pos].getID()));
	}

	/**
	 * Updates the star.
	 * 
	 * @param isStarFilled Represents if the star is filled or not.
	 * @param holder the PubHolder which holds the pub
	 */
	public void updateStar(boolean isStarFilled, PubHolder holder) {
		if (isStarFilled) {
			holder.favoriteStar.setBackgroundResource(R.drawable.star_not_filled);
		} else {
			holder.favoriteStar.setBackgroundResource(R.drawable.star_filled);
		}
	}

	/**
	 * Static holder for pubs
	 */
	static class PubHolder
	{
		ImageView	imgIcon;
		TextView	txtTitle;
		TextView	distanceText;
		ImageButton	favoriteStar;
	}
}
