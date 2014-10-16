package io.databr.trenssp.adapter;

import java.text.ParseException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import io.databr.trenssp.R;
import io.databr.trenssp.models.Line;


public class CustomListAdapter extends BaseAdapter {
    protected Activity activity;
    private LayoutInflater inflater;
    private List<Line> lineItems;

    public CustomListAdapter(Activity activity, List<Line> lineItems) {
        this.activity = activity;
        this.lineItems = lineItems;
    }

    @Override
    public int getCount() {
        return lineItems.size();
    }

    @Override
    public Object getItem(int location) {
        return lineItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView lastStatus = (TextView) convertView.findViewById(R.id.lastStatus);
        TextView updatedAt = (TextView) convertView.findViewById(R.id.updatedAt);

        Line l = lineItems.get(position);

        title.setText(l.getName());
        number.setText(l.getNumber());
        number.setBackgroundColor(l.getColor().getHex());
        lastStatus.setText(l.getStatus().getMessage());

        try {
            updatedAt.setText("Atualizado ".concat(l.getStatus().getUpdatedAgo()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
