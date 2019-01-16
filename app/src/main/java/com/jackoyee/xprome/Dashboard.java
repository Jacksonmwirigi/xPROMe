package com.jackoyee.xprome;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;

public class Dashboard extends BaseActivity {
    CardView assigned_task, closed,devices_inst,req_actions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_dashboard, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
      //  setContentView(R.layout.activity_dashboard);

//        assigned_task=(CardView)findViewById(R.id.assigned_task);
//        closed=(CardView)findViewById(R.id.closed_task);
//        devices_inst=(CardView)findViewById(R.id.devices_installed);
//        req_actions=(CardView)findViewById(R.id.required_action);


    }

    public  void onClickCardAction(View view){
        switch (view.getId()){
            case R.id.assigned_task:
                break;
            case R.id.required_action:
                startActivity(new Intent(getApplicationContext(),ViewTickets.class));
                break;
            case R.id.devices_installed:
                break;
            case R.id.closed_task:
                break;
        }

    }
}
