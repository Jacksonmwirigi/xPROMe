package com.jackoyee.xprome;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import android.widget.FrameLayout;
import android.widget.Toast;

import com.jackoyee.xprome.model.Tickets;
import com.jackoyee.xprome.adapters.TicketListAdapter;
import com.jackoyee.xprome.ticketsFragments.IncidenceFragment;
import com.jackoyee.xprome.ticketsFragments.InstallFragment;

import java.util.List;


public class ViewTickets extends BaseActivity {

    private RecyclerView mRecyclerView;
   // private RecyclerView.Adapter mAdapter;
    private TicketListAdapter adapter;
    private List<Tickets>myticktes;
    private TabLayout tabLayout;

    SharedPreferences preferences;
    public final   String status="assigned";
    public final  String company ="Safaricom";
    public  final String request_type="installation";
    public   String current_session_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    // setContentView(R.layout.view_tickets);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.view_tickets,frameLayout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        Fragment fragmentt=new InstallFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transactionn=fragmentManager.beginTransaction();
        transactionn.replace(R.id.tickets_frame,fragmentt);
        transactionn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transactionn.commit();

        tabLayout=(TabLayout)findViewById(R.id.tabs);
      //  final int selectedTab=tabLayout.getSelectedTabPosition();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                Toast.makeText(ViewTickets.this, "selected:"+tab.getText(), Toast.LENGTH_SHORT).show();
                Fragment fragment=null;
                switch (tab.getPosition()){
                    default:
                        fragment=new InstallFragment();
                         break;
                    case 0:
                       // Toast.makeText(ViewTickets.this, "First option", Toast.LENGTH_SHORT).show();
                        fragment=new InstallFragment();
                        break;

                    case 1:
                     //   Toast.makeText(ViewTickets.this, "second option", Toast.LENGTH_SHORT).show();
                         fragment=new IncidenceFragment();
                        break;

                }
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.tickets_frame,fragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



//
//       preferences=getApplicationContext().getSharedPreferences("myData",MODE_PRIVATE);
//       current_session_key = preferences.getString("session_key","");
//
//      Call<TicketsResponse> call =RetrofitClient
//              .getNetworkInstance()
//              .getApiService()
//              .viewMyTickets(current_session_key,status,request_type,company);
//

////
////        Call<TicketsResponse>call=service.getTickets(ticket,current_session_key);
//
//        Log.d("Url",call.request().url().toString() );
//
//        call.enqueue(new Callback<TicketsResponse>() {
//            @Override
//            public void onResponse(Call<TicketsResponse> call, Response<TicketsResponse> response) {
//              TicketsResponse ticketsResponse1=response.body();
//              int response_code=ticketsResponse1.getStatus();
//
//                if (ticketsResponse1.isResponse()) {
//                    if (response_code==200) {
//                        generateMyTickets(ticketsResponse1.getUser());
////                    generateMyTickets(ticketsResponse1.getUser());
////                    Log.d("My data here", response.body().toString());
//                        //                  mRecyclerView = findViewById(R.id.ticket_recyclerView);
//                        //                  adapter = new TicketListAdapter(ticketsResponse1.getUser());
//                        //                  Log.d("my adapter", String.valueOf(mRecyclerView));
//                        //                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewTickets.this);
//                        //                  mRecyclerView.setLayoutManager(layoutManager);
//                        //                  mRecyclerView.setAdapter(adapter);
//
//                    }
//                    else  if (response_code==403){
//                        Toast.makeText(ViewTickets.this, "No Tickets assigned to you at the Moment. ", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//                else {
//                    Toast.makeText(ViewTickets.this, "Error occurred or Session Expired!", Toast.LENGTH_SHORT).show();
//                    }
//            }
//
//            @Override
//            public void onFailure(Call<TicketsResponse> call, Throwable t) {
//
//            }
//        });


//        Call<TicketsList>call =RetrofitClient
//                .getNetworkInstance()
//                .getApiService()
//                .viewMyTasks(ticket,current_session_key);


//        call.enqueue(new Callback<TicketsList>() {
//            @Override
//            public void onResponse(Call<TicketsList> call, Response<TicketsList> response) {
//
//               // TicketsResponse ticketsResponse=response.body();
//                Log.d("Response be like", String.valueOf(response.body()));
//                generateMyTickets(response.body().getMyticktes());
//
////                mRecyclerView = findViewById(R.id.ticket_recyclerView);
////                adapter = new TicketListAdapter(myticktes);
////
////                Log.d("my adapter", String.valueOf(mRecyclerView));
////                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewTickets.this);
////                mRecyclerView.setLayoutManager(layoutManager);
////                mRecyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<TicketsList> call, Throwable t) {
//
//            }
//        });



//      Log.d("URL Call",service.request().url().toString());
//     service.enqueue(new Callback<TicketsResponse>() {
//         @Override
//         public void onResponse(Call<TicketsList> call, Response<TicketsList> response) {
//           //  TicketsResponse ticketsResponse=  response.body();
//
////             TicketsResponse ticketsResponse=response.body();
////             int status=ticketsResponse.getStatus();
////
////
////           //  System.err.println(response.body().getUser().toString());
////                             if (status==200) {
////                                // System.out.print(response.body().getUser().getASSIGNEE());
////
////                                 Log.d("status",ticketsResponse.getUser().getCLIENT_NAME());
////                                 mRecyclerView = findViewById(R.id.ticket_recyclerView);
////                                 adapter = new TicketListAdapter(mytickets,ViewTickets.this);
////                                 RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewTickets.this);
////                                 mRecyclerView.setLayoutManager(layoutManager);
////                                 mRecyclerView.setAdapter(adapter);
////                             }
//             generateNoticeList(response.body().getNoticeArrayList());
//
//
//
//         }
//
//         @Override
//         public void onFailure(Call<TicketsResponse> call, Throwable t) {
//
//         }
//     });

    }


//
//    private   void  generateMyTickets(List<Tickets> tickets){
//
//        mRecyclerView = findViewById(R.id.ticket_recyclerView);
//        adapter = new TicketListAdapter(tickets);
//        Log.d("my adapter", String.valueOf(mRecyclerView));
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(adapter);
//
//    }


//    public  void viewMyTasks(){
//
//        mRecyclerView = findViewById(R.id.ticket_recyclerView);
//        adapter = new TicketListAdapter(mytickets);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(adapter);
//    }


//    public  void viewMyTasks(){
//
//        Call<TicketsResponse> call= RetrofitClient.getNetworkInstance()
//                .getApiService()
//                .viewMyTasks(ticket,current_session_key);
//
//        call.enqueue(new Callback<TicketsResponse>() {
//            @Override
//            public void onResponse(Call<TicketsResponse> call, Response<TicketsResponse> response) {
//                Log.d("onResponse", response.message());
//                TicketsResponse ticketsResponse =  response.body();
//
//                if (!ticketsResponse.isResponse()) {
//                    String dipatch_id = ticketsResponse.getUser().getDISPATCH_ID();
//                    dispatch_idtxt.setText(dipatch_id);
//                    String tickonumber = ticketsResponse.getUser().getTICKET();
//                    tickotxt.setText(tickonumber);
//                    String requestType = ticketsResponse.getUser().getREQUEST_TYPE();
//                    req_typetxt.setText(requestType);
//                    String assignee = ticketsResponse.getUser().getASSIGNEE();
//                    asigneetxt.setText(assignee);
//                    String asignmentDate = ticketsResponse.getUser().getASSIGNMENT_DATE();
//                    date_asigntxt.setText(asignmentDate);
//                    String assigner = ticketsResponse.getUser().getASSIGNER();
//                    asignertxt.setText(assigner);
//                    String lastupdate = ticketsResponse.getUser().getLAST_UPDATE();
//                    lst_updatetxt.setText(lastupdate);
//                    String status = ticketsResponse.getUser().getSTATUS();
//                    sttustxt.setText(status);
//                    String comment = ticketsResponse.getUser().getCOMMENTS();
//                    comentstxt.setText(comment);
//                    String scheuleTime = ticketsResponse.getUser().getSCHEDULED_TIME_SLOT();
//                    schd_timetxt.setText(scheuleTime);
//                    String client_name = ticketsResponse.getUser().getCLIENT_NAME();
//                    clinttxt.setText(client_name);
//                    String residence_clt = ticketsResponse.getUser().getCLIENT_RESIDENCE();
//                    residencetxt.setText(residence_clt);
//                    String client_phone = ticketsResponse.getUser().getCLIENT_PHONE();
//                    phonetxt.setText(client_phone);
//                    String scheduledate = ticketsResponse.getUser().getSCHEDULED_DATE();
//                    sched_datetxt.setText(scheduledate);
//
//                }
//
//
//
////                mLayoutManager=new LinearLayoutManager(ViewTickets.this);
////                mRecyclerView.setLayoutManager(mLayoutManager);
////                mRecyclerView=(RecyclerView)findViewById(R.id.ticket_recyclerView);
////               // mRecyclerView.setHasFixedSize(true);
////                mAdapter = new TicketListAdapter(ticketList,this);
////                mRecyclerView.setAdapter(mAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<TicketsResponse> call, Throwable t) {
//
//            }
//        });

   // }

 }
