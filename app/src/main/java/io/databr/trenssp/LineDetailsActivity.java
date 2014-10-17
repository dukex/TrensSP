package io.databr.trenssp;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import io.databr.trenssp.adapter.CustomListAdapter;
import io.databr.trenssp.models.Line;

/**
 * Created by duke on 10/16/14.
 */
public class LineDetailsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_details);

        Line line = (Line) getIntent().getSerializableExtra("list");
        Log.d("AA", line.getName());

        setTitle(line.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
