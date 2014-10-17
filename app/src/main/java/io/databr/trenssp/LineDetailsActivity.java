package io.databr.trenssp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.databr.trenssp.adapter.StatusAdapter;
import io.databr.trenssp.models.Line;
import io.databr.trenssp.models.LineStatus;
import io.databr.trenssp.services.DataBRService;
import io.databr.trenssp.services.StatusesResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by duke on 10/16/14.
 */
public class LineDetailsActivity extends ListActivity {
    private ProgressDialog pDialog;
    final private List<LineStatus> statusesList = new ArrayList<LineStatus>();
    private StatusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_details);

        Line line = (Line) getIntent().getSerializableExtra("list");

        setTitle(line.getName());


        ListView listView = getListView();

        adapter = new StatusAdapter(this, statusesList);
        listView.setAdapter(adapter);

        loadData(line.getId(), "Carregando Status...");
    }

    private void loadData(String listId, String message) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(message);
        pDialog.show();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.databr.io")
                .build();

        DataBRService service = restAdapter.create(DataBRService.class);

        service.getLineStatuses(listId, new Callback<StatusesResponse>() {
            @Override
            public void success(StatusesResponse statusesResponse, retrofit.client.Response response) {
                List<LineStatus> statuses;
                statuses = statusesResponse.getStatuses();

                for (LineStatus status : statuses) statusesList.add(status);

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

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
