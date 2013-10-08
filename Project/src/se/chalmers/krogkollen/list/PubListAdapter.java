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
        holder.txtTitle.setText(GET TITLE OF PUB HERE);
        holder.imgIcon.setImageResource(GET ICON OF PUB HERE);

        return row;
    }

    static class PubHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
