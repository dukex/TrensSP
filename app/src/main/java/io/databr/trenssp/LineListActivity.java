package io.databr.trenssp;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.databr.trenssp.adapter.CustomListAdapter;
import io.databr.trenssp.models.Line;
import io.databr.trenssp.services.DataBRService;
import io.databr.trenssp.services.LinesResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class LineListActivity extends ListActivity {
    private ProgressDialog pDialog;
    private List<Line> lineList = new ArrayList<Line>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_list);

        ListView listView = getListView();


        adapter = new CustomListAdapter(this, lineList);
        listView.setAdapter(adapter);

        Log.d("A", lineList.toString());

        loadData("Carregando...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

  //  @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       // final Line item = lineList.get(position);
        startActivity(new Intent(this, LineDetailsActivity.class));
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                lineList.clear();
                loadData("Recarregando...");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData(String message) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(message);
        pDialog.show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}