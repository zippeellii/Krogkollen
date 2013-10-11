package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.map.UserLocation;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.utils.Distance;

import java.text.*;

/**
 * An adapter handling the different items in a list
 */
public class PubListAdapter extends ArrayAdapter<IPub> {

    Context context;
    int layoutResourceId;
    IPub data[] = null;
    View row;
    PubHolder holder;

    /**
     * A constructor that creates an PubListAdapter.
     * @param context
     * @param layoutResourceId
     * @param data
     */
    public PubListAdapter(Context context, int layoutResourceId, IPub[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PubHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.listview_image);
            holder.txtTitle = (TextView)row.findViewById(R.id.listview_title);
            holder.distanceText = (TextView)row.findViewById(R.id.listview_distance);
            holder.favoriteStar = (ImageButton)row.findViewById(R.id.favorite_star_list);

            row.setTag(holder);
        }
        else
        {
            holder = (PubHolder)row.getTag();
        }

        updateStar(context.getSharedPreferences(this.getItem(position).getID(), 0).getBoolean("star", true), holder);

        IPub pub = data[position];
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        holder.txtTitle.setText(pub.getName());
        holder.distanceText.setText(""+(numberFormat.format(Distance.calcDistBetweenTwoLatLng(new LatLng(pub.getLatitude(),pub.getLongitude()), UserLocation.getInstance().getCurrentLatLng())))+" km");
        holder.favoriteStar.setTag(position);


        holder.favoriteStar.setOnClickListener(new View.OnClickListener() {
            PubHolder tmp = holder;
            @Override
            public void onClick(View v) {
                int pos = (Integer)v.getTag();
                saveFavoriteState(pos);
                updateStar(context.getSharedPreferences(data[pos].getID(), 0).getBoolean("star", true), tmp);


            }
        });

        switch(pub.getQueueTime()){
        case 1:
        	holder.imgIcon.setImageResource(R.drawable.detailed_queue_green);
        	break;
        case 2:
        	holder.imgIcon.setImageResource(R.drawable.detailed_queue_yellow);
        	break;
        case 3:
        	holder.imgIcon.setImageResource(R.drawable.detailed_queue_red);
        	break;
        default :
        	holder.imgIcon.setImageResource(R.drawable.detailed_queue_gray);
        	break;
        }
        return row;

    }

    /**
     * Saves the state of the favorite locally
     */
    public void saveFavoriteState(int pos){
        SharedPreferences.Editor editor = context.getSharedPreferences(data[pos].getID(), 0).edit();
        editor.putBoolean("star", !(context.getSharedPreferences(data[pos].getID(), 0).getBoolean("star", true)));
        editor.commit();
    }

    /**
     * Updates the star.
     * @param isStarFilled Represents if the star is filled or not.
     */
    public void updateStar(boolean isStarFilled, PubHolder holder){
        if(isStarFilled){
            holder.favoriteStar.setBackgroundResource(R.drawable.star_not_filled);
        }
        else{
            holder.favoriteStar.setBackgroundResource(R.drawable.star_filled);
        }
    }


    /**
     * Static holder for pubs
     */
    static class PubHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView distanceText;
        ImageButton favoriteStar;

    }
}
