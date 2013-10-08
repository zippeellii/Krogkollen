package se.chalmers.krogkollen.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.utils.Constants;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class PubListAdapter extends ArrayAdapter<IPub> {

    Context context;
    int layoutResourceId;
    IPub data[] = null;

    public PubListAdapter(Context context, int layoutResourceId, IPub[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PubHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PubHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.listview_image);
            holder.txtTitle = (TextView)row.findViewById(R.id.listview_title);

            row.setTag(holder);
        }
        else
        {
            holder = (PubHolder)row.getTag();
        }

        IPub pub = data[position];
        holder.txtTitle.setText(pub.getName());
        switch(pub.getQueueTime()){
        case 1:
        	holder.imgIcon.setImageResource(Constants.SHORT_QUEUE);
        	break;
        case 2:
        	holder.imgIcon.setImageResource(Constants.MEDIUM_QUEUE);
        	break;
        case 3:
        	holder.imgIcon.setImageResource(Constants.LONG_QUEUE);
        	break;
        default :
        	holder.imgIcon.setImageResource(Constants.NO_INFO_QUEUE);
        	break;
        }
        return row;
    }

    static class PubHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
