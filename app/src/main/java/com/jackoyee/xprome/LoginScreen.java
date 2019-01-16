package com.jackoyee.xprome;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jackoyee.xprome.model.LoginResponse;

import com.jackoyee.xprome.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

   private static final String TAG = "LoginScreen";

   private Button btn ;
   private TextView link;
   private   EditText pass,emailaddress;

   private DrawerLayout drawer;
   private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        emailaddress=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.EPass);
        btn=(Button)findViewById(R.id.LognBtn);


    }

    public void userLogin(){
        String username =emailaddress.getText().toString();
        String password=pass.getText().toString();

        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
     Call<LoginResponse> call = RetrofitClient
             .getNetworkInstance()
             .getApiService()
             .userLogin(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse  loginResponse=response.body();
                int status = 0;
                if (loginResponse != null) {
                    status = loginResponse.getStatus();

                    if (status==200){

                        Log.d("status on Connect", String.valueOf(status));

                        String session_key= loginResponse.getUser().getCURRENT_SESSION_KEY();
                        String my_name=loginResponse.getUser().getNAME();
                        String phone=loginResponse.getUser().getPHONE_NUMBER();
                        String username=loginResponse.getUser().getUSERNAME();
                        String user_email=loginResponse.getUser().getEMAIL_ADDRESS();
                        int user_id=loginResponse.getUser().getUSER_ID();

                        SharedPreferences sharedPreferences=getSharedPreferences("myData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();

                        editor.putString("session_key",session_key);
                        editor.putString("my_name",my_name);
                        editor.putString("phone",phone);
                        editor.putString("username",username);
                        editor.putInt("user_id",user_id);
                        editor.putString("user_email",user_email);
                        editor.commit();

//                  Intent intent =new Intent(getApplicationContext(),InstallationActivity.class);
//                  intent.putExtra("session_key",session_key);
//                  intent.putExtra("my_name",my_name);

                        startActivity(new Intent(LoginScreen.this,MainActivity.class));
                    }
                    else  if (status==403){ Toast.makeText(LoginScreen.this, "Wrong credentials.Please Retry!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else {
                    Toast.makeText(LoginScreen.this, "Incorrect Network Host. "+response.message(), Toast.LENGTH_SHORT).show();
                  }


//                else {
//                    Toast.makeText(LoginScreen.this, "Failed to login", Toast.LENGTH_SHORT).show();
//                   }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginScreen.this, "Network Error! " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public  void onclickSignIn(View view){
        switch (view.getId()){
            case R.id.LognBtn:
//                String username=email.getText().toString();
//                String password=pass.getText().toString();
                userLogin();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder  builder=new AlertDialog.Builder(LoginScreen.this);
         builder.setTitle(R.string.app_name)
                 .setMessage("Are You Sure You Want to Exit the Application")
                 .setCancelable(false)
                 .setIcon(R.mipmap.iconic)
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         finish();
                     }
                 })
                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         dialogInterface.cancel();

                     }
                 });

        AlertDialog alert = builder.create();
        alert.show();


    }
}



