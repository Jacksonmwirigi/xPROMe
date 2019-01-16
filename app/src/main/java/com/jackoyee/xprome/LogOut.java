package com.jackoyee.xprome;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jackoyee.xprome.splash.SplashScreen;

public class LogOut extends BaseActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_out_activity);

        progressBar=(ProgressBar)findViewById(R.id.progressBar1);

        AlertDialog.Builder  builder=new AlertDialog.Builder(LogOut.this);
        builder.setTitle(R.string.app_name)
                .setMessage("Are You Sure You Want to Logout")
                .setCancelable(false)
                .setIcon(R.mipmap.iconic)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Handler handler =new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(),SplashScreen.class));
                                finish();
                            }
                        },1000);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();


    }

  }

