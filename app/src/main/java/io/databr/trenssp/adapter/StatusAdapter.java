package io.databr.trenssp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import io.databr.trenssp.R;
import io.databr.trenssp.models.Line;
import io.databr.trenssp.models.LineStatus;

/**
 * Created by duke on 10/17/14.
 */
public class StatusAdapter extends BaseAdapter{
    final private Activity activity;
    private LayoutInflater inflater;
    final private List<LineStatus> statusItems;

    public StatusAdapter(Activity activity, List<LineStatus> statusItems) {
        this.activity = activity;
        this.statusItems = statusItems;
    }

    @Override
    public int getCount() {
        return statusItems.size();
    }

    @Override
    public Object getItem(int location) {
        return statusItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.status_list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView updatedAt = (TextView) convertView.findViewById(R.id.updatedAt);

        LineStatus s = statusItems.get(position);

        title.setText(s.getMessage());

        try {
            updatedAt.setText(s.getCreatedAtAgo());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

}
