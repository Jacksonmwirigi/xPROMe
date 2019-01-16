package com.jackoyee.xprome;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jackoyee.xprome.model.UploadsResponse;
import com.jackoyee.xprome.network.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;


public class InstallationActivity extends BaseActivity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String TAG = "PhotoScreen";
    public final String APP_TAG = "MyCustomApp";
    private ImageView imageView;

    private Button btn;
    private RadioGroup rg ;
    private RadioButton completed_rd,pending_rd;
    private TextView longit,lat,ticket_number,navHeader;
    private TextView residence,client_name,mob_Number,engineer;
    private EditText  mynotes;
    private String imageLocation="";
    final int Permission_All = 1;
    ProgressDialog mprogress;
    Bitmap bitmapimage=null;

    String timeStamp= new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.getDefault()).format(new Date());
    public String resizuriphoto ="ONU_"+ timeStamp+".jpg";

    public  String photoFileName= "Connected" +timeStamp+".jpg";
    File photoFile;
    Uri resizedUri;

    Uri photoUri;
    double latitude;
    double longitude;
   // String timeStamp;

    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
    public static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;

    GPSTracker gps;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_installations, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.getMenu().getItem(2).setChecked(true);
       // setContentView(R.layout.activity_installations);



        preferences =getApplication().getSharedPreferences("myData",MODE_PRIVATE);
        lat = (TextView) findViewById(R.id.lati);
        longit = (TextView) findViewById(R.id.longit);
        ticket_number=(TextView)findViewById(R.id.ticket_no);

        rg=(RadioGroup)findViewById(R.id.status_rg) ;
        completed_rd=(RadioButton)findViewById(R.id.completed_rd);
        pending_rd=(RadioButton)findViewById(R.id.pending_rd);

        imageView = (ImageView) findViewById(R.id.capturedImage);
        client_name=(TextView)findViewById(R.id.client_name);
        mynotes=(EditText)findViewById(R.id.coment);
        engineer=(TextView)findViewById(R.id.fo_name);
        residence=(TextView)findViewById(R.id.residential);
        mob_Number=(TextView)findViewById(R.id.clientNumber);

        mprogress=new ProgressDialog(this);

        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,
         Manifest.permission.READ_EXTERNAL_STORAGE};

        if (!hasPermissions(this, permissions)) {

            ActivityCompat.requestPermissions(this, permissions, Permission_All);
          }
               String email_new=preferences.getString("user_email","");
               String session_key=preferences.getString("session_key","");
             // .setText(session_key);
              //navHeader.setText(email_new);

        btn = (Button) findViewById(R.id.sendBtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoFile = getPhotoFileUri(photoFileName);
                Uri fileProvider = FileProvider.getUriForFile(InstallationActivity.this, BuildConfig.APPLICATION_ID, photoFile);
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

                if (camIntent.resolveActivity(getPackageManager()) != null) {
                    // Start the image capture intent to take photo
                    startActivityForResult(camIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                }

//BELLOW IS THE INITIAL WORKING CODE (Nougat too)
//
//                if (camIntent.resolveActivity(getPackageManager()) != null) {
//                    File photoFile = null;
//                    try {
//                        photoFile = createImage();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                if (photoFile != null) {
//                  //  photoUri = Uri.fromFile(photoFile);
//                    photoUri =FileProvider.getUriForFile(InstallationActivity.this,BuildConfig.APPLICATION_ID,photoFile);
//                    camIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                    startActivityForResult(camIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//                }
//            }

            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

//                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//                imageView.setImageBitmap(takenImage);

                imageView.setImageURI(resizedUri);
                try {
                    saveScaledImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

         }

        gps = new GPSTracker(InstallationActivity.this);
        if(gps.canGetLocation())
        {
            latitude = (float) gps.getLatitude();
            longitude= (float) gps.getLongitude();

          //  float number = latitude;
          //  System.out.println(String.format ("%.1f", number));

            //float to string conversion with decimal places defined in my case 7 decimals for accuracy.
            String client_loc_lat=String.format("%.8f",latitude);
            String client_loc_long=String.format("%.8f",longitude);

            longit.setText(client_loc_long);
            lat.setText(client_loc_lat);
            // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }

    }

    public void onClickAddUploadData(View view) {

//        final String ClientName_Str=client_name.getText().toString().trim();
//        final String comment=mynotes.getText().toString().trim();
//        final String residence_Str=residence.getText().toString().trim();
//        final String engineer_str=engineer.getText().toString().trim();
//        final String phone_number=mob_Number.getText().toString().trim();
//        final String ticket=
//        final  String status=
       // final int contact=Integer.parseInt(phone_number);





//        if (!TextUtils.isEmpty(ClientName_Str) &&!TextUtils.isEmpty(comment) ){
//            mprogress.setMessage("Uploading...");
//            mprogress.show();
//
//
//          }
//        else {
//            Toast.makeText(InstallationActivity.this, "You need to include some description ", Toast.LENGTH_SHORT).show();
//        }


    }

//    public void restartActivity(){
//        recreate();
//    }


         public  File getPhotoFileUri(String imageFileName){
             File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

             // Create the storage directory if it does not exist
             if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                 Log.d(APP_TAG, "failed to create directory");
             }

             File file = new File(mediaStorageDir.getPath() + File.separator + imageFileName);

             Log.i(TAG,"taken bitmap image "+file);
             return file;

         }

         public void saveScaledImage() throws IOException {

             Uri takenPhotoUri = Uri.fromFile(getPhotoFileUri(photoFileName));
             Bitmap rawTakenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
             Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(rawTakenImage, 1024);

             ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
             resizedUri = Uri.fromFile(getPhotoFileUri(resizuriphoto));

             Log.i(TAG,"resized bitmap image "+resizedUri);

             //Uri.fromFile(getPhotoFileUri(photoFileName + "_resized"));
             File resizedFile = new File(resizedUri.getPath());
             resizedFile.createNewFile();
             FileOutputStream fos = new FileOutputStream(resizedFile);

             fos.write(bytes.toByteArray());
             fos.close();

         }

   public  void  uploadData(){

           int selectedStatus=rg.getCheckedRadioButtonId();

           RadioButton status_selected=(RadioButton)findViewById(selectedStatus);
          final String status=status_selected.getText().toString();

             final String ClientName_Str=client_name.getText().toString().trim();
             final String comment=mynotes.getText().toString().trim();
             final String residence_Str=residence.getText().toString().trim();
             final String engineer_str=engineer.getText().toString().trim();
             final String phone_number=mob_Number.getText().toString().trim();
//             final String ticket=

             String client_loc_lat=String.format("%.8f",latitude);
             String client_loc_long=String.format("%.8f",longitude);

             Call<UploadsResponse> call = RetrofitClient
                     .getNetworkInstance()
                     .getApiService()
                     .uploadData(status,client_loc_long,client_loc_lat);

         }

//   public  void chooseAppropriateAction(){
//       AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
//       builder.setTitle("Choose Required Action")
//               .setMessage(" Close or Pend the Ticket ")
//               .setCancelable(false)
//               .setPositiveButton("Close", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialogInterface, int i) {
//                       Toast.makeText(InstallationActivity.this, "Ticket has been Closed.", Toast.LENGTH_LONG).show();
//                   }
//               })
//               .setNegativeButton("set Pending", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialogInterface, int i) {
//                       Toast.makeText(getApplicationContext(), "Ticket has been suspended.", Toast.LENGTH_LONG).show();
//
//                   }
//               });
//
//    }

  }





