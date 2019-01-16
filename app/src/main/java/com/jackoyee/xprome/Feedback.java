package com.jackoyee.xprome;

import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jackoyee.xprome.adapters.MyDashAdapter;
import com.jackoyee.xprome.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends BaseActivity  {

    private CardView devics,feedback,dispatch_histo;

    final String URL_GET_DATA = "https://simplifiedcoding.net/demos/marvel/";
    RecyclerView recyclerView;
    MyDashAdapter adapter;
    List<Model> ticketlist;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.feedback_cust, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);
        //setContentView(R.layout.feedback_cust);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ticketlist = new ArrayList<>();

        loadTickets();

        
    }

    private void loadTickets() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        Model model = new Model(
                                obj.getString("name"),
                                obj.getString("realname"),
                                obj.getString("team"),
                                obj.getString("firstappearance"),
                                obj.getString("createdby"),
                                obj.getString("publisher"),
                                obj.getString("imageurl"),
                                obj.getString("bio"));

                        ticketlist.add(model);

                    }
                    adapter = new MyDashAdapter(ticketlist, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
