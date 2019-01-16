package com.jackoyee.xprome.ticketsFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jackoyee.xprome.R;
import com.jackoyee.xprome.model.Tickets;
import com.jackoyee.xprome.model.TicketsResponse;
import com.jackoyee.xprome.adapters.TicketListAdapter;
import com.jackoyee.xprome.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class IncidenceFragment extends Fragment {
    Context context;
    private RecyclerView mRecyclerView;
    // private RecyclerView.Adapter mAdapter;
    private TicketListAdapter adapter;
    private List<Tickets>myticktes;
    private TabLayout tabLayout;

    SharedPreferences preferences;
    public final   String status="new";
    public final  String company ="Safaricom";
    public  final String request_type="incident";
    public   String current_session_key;
    public  IncidenceFragment(){
       }

    public void  quesrydatayangu(){
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.ticket_recyclerView);
        preferences=getActivity().getSharedPreferences("myData",MODE_PRIVATE);
        current_session_key = preferences.getString("session_key","");
        // Log.d("Session key",current_session_key);
        Log.d("Session key",current_session_key);

        Call<TicketsResponse> call = RetrofitClient
                .getNetworkInstance()
                .getApiService()
                .viewMyTickets(current_session_key,status,request_type,company);


//
//        Call<TicketsResponse>call=service.getTickets(ticket,current_session_key);

        Log.d("Url",call.request().url().toString() );

        call.enqueue(new Callback<TicketsResponse>() {
            @Override
            public void onResponse(Call<TicketsResponse> call, Response<TicketsResponse> response) {
                TicketsResponse ticketsResponse1=response.body();
                int response_code=ticketsResponse1.getStatus();

                if (ticketsResponse1.isResponse()) {
                    if (response_code==200) {
                        generateMyTickets(ticketsResponse1.getData());
//                    generateMyTickets(ticketsResponse1.getUser());
//                    Log.d("My data here", response.body().toString());
                        //                  mRecyclerView = findViewById(R.id.ticket_recyclerView);
                        //                  adapter = new TicketListAdapter(ticketsResponse1.getUser());
                        //                  Log.d("my adapter", String.valueOf(mRecyclerView));
                        //                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewTickets.this);
                        //                  mRecyclerView.setLayoutManager(layoutManager);
                        //                  mRecyclerView.setAdapter(adapter);

                    }
                    else  if (response_code==403){
                        Toast.makeText(context, "No Tickets assigned to you at the Moment. ", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(context, "Error occurred or Session Expired!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TicketsResponse> call, Throwable t) {

            }
        });

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quesrydatayangu();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        inflater.inflate(R.layout.test_fragment_one,container,false);

        return  inflater.inflate(R.layout.test_fragment_one,container,false);
       // mRecyclerView = container.findViewById(R.id.ticket_recyclerView);
    }


    private   void  generateMyTickets(List<Tickets> tickets){

        adapter = new TicketListAdapter(tickets);
        Log.d("my adapter", String.valueOf(mRecyclerView));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

    }
}
