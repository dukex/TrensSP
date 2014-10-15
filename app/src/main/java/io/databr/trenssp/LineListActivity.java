package io.databr.trenssp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.databr.trenssp.adapter.CustomListAdapter;
import io.databr.trenssp.models.Line;
import io.databr.trenssp.models.LineColor;
import io.databr.trenssp.models.LineStatus;

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
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        String urlLines = "http://api.databr.io/v1/states/sp/transports/trains/lines";

        Log.d(TAG, urlLines);
        JsonObjectRequest listsReq = new JsonObjectRequest(urlLines, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        JSONArray linesItems;
                        try {
                            linesItems = response.getJSONArray("lines");




                        for (int i = 0; i < linesItems.length(); i++) {
                            try {

                                JSONObject obj = linesItems.getJSONObject(i);
                                Line line = new Line();
                                line.setName(obj.getString("name"));
                                line.setNumber(obj.getInt("number"));

                                JSONObject colorObj = obj.getJSONObject("color");
                                if (colorObj != null) {
                                    LineColor color = new LineColor(colorObj.getString("hex"));
                                    line.setColor(color);
                                }

                                JSONObject statusObj = obj.getJSONObject("status");
                                if (statusObj != null) {
                                    LineStatus status = new LineStatus(statusObj.getString("message"), statusObj.getString("updated_at"));
                                    line.setLastStatus(status);
                                }

                                lineList.add(line);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(listsReq);
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
        // Inflate the menu; this adds items to the action bar if it is present.
       //  getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

}