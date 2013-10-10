package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class PubListAdapter extends ArrayAdapter<IPub> {

    Context context;
    int layoutResourceId;
    IPub data[] = null;
    View row;

    public PubListAdapter(Context context, int layoutResourceId, IPub[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        PubHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PubHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.listview_image);
            holder.txtTitle = (TextView)row.findViewById(R.id.listview_title);
            holder.distanceText = (TextView)row.findViewById(R.id.listview_distance);
            holder.listButton = (ImageButton)row.findViewById(R.id.favorite_star_list);
            holder.listButton.setBackgroundResource(R.drawable.star_filled);

            row.setTag(holder);
        }
        else
        {
            holder = (PubHolder)row.getTag();
        }

        IPub pub = data[position];
        holder.txtTitle.setText(pub.getName());
        holder.distanceText.setText("distance");
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

    static class PubHolder
    {


        ImageView imgIcon;
        TextView txtTitle;
        TextView distanceText;
        ImageButton listButton;

    }
}
