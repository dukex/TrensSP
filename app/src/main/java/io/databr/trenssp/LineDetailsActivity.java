package io.databr.trenssp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import io.databr.trenssp.adapter.CustomListAdapter;

/**
 * Created by duke on 10/16/14.
 */
public class LineDetailsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_details);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
