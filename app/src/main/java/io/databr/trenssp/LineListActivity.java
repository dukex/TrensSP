package io.databr.trenssp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.databr.trenssp.adapter.CustomListAdapter;
import io.databr.trenssp.models.Line;
import io.databr.trenssp.services.DataBRService;
import io.databr.trenssp.services.LinesResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class LineListActivity extends Activity {
    private static String TAG = LineListActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private List<Line> lineList = new ArrayList<Line>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_list);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, lineList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Carregando...");
        pDialog.show();

        String urlLines = "http://api.databr.io/v1/states/sp/transports/trains/lines";
        Log.d(TAG, urlLines);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.databr.io")
                .build();

        DataBRService service = restAdapter.create(DataBRService.class);

         service.listLines(new Callback<LinesResponse>() {
                @Override
                public void success(LinesResponse linesResponse, retrofit.client.Response response) {
                    List<Line> lines = linesResponse.getLines();
                    Collections.reverse(lines);

                    for (Line line : lines) lineList.add(line);

                    adapter.notifyDataSetChanged();
                    hidePDialog();
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                    hidePDialog();
                }
         });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}