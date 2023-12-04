package com.example.glovobot;

import static android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.glovobot.Model.PythonAuthResponse;
import com.example.glovobot.databinding.ActivityMainBinding;
import com.example.glovobot.services.PythonApiService;
import com.otpview.OTPListener;
import com.otpview.OTPTextView;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String[] LOCATION_PERMISSIONS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final String[] LOCATION_PERMISSIONS_BACKGROUND = {
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
    };
    private static final String[] INTERNET_PERMISSIONS = {
            android.Manifest.permission.INTERNET
    };
    private static final String[] SERVICE_PERMISSIONS = {
            android.Manifest.permission.FOREGROUND_SERVICE
    };
    private static final String[] WAKE_PERMISSIONS = {
            android.Manifest.permission.WAKE_LOCK
    };

    private static final String[] BACKGROUND_PERMISSIONS = {
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    };



    private static AlertDialog dialog = null;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OTPTextView otpTextView = findViewById(R.id.otp_view);
        otpTextView.requestFocusOTP();

        EasyPermissions.requestPermissions(this, "We need location permissions", 1, LOCATION_PERMISSIONS);

        try{

            if (Build.VERSION.SDK_INT >= 29)
                EasyPermissions.requestPermissions(this, "We need background location permissions", 1, LOCATION_PERMISSIONS_BACKGROUND);

        }catch (Exception ex){

        }

        EasyPermissions.requestPermissions(this, "We need internet access", 2, INTERNET_PERMISSIONS);

        try{
            EasyPermissions.requestPermissions(this, "We need service access", 3, SERVICE_PERMISSIONS);
        }catch (Exception ex){

        }


        EasyPermissions.requestPermissions(this, "We need background tasks & battery permissions", 1, BACKGROUND_PERMISSIONS);

        EasyPermissions.requestPermissions(this, "We need wakeup access", 4, WAKE_PERMISSIONS);


        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(@NonNull String s) {


                showDialog(MainActivity.this, "Please wait.");
                PythonApiService pythonApiService = new PythonApiService(s);
                pythonApiService.auth().enqueue(new Callback<PythonAuthResponse>() {
                    @Override
                    public void onResponse(Call<PythonAuthResponse> call, Response<PythonAuthResponse> response) {
                        dismissDialog();
                        if (response.isSuccessful()) {
                            int responseCode = response.code();

                            PythonAuthResponse authResponse = response.body();
                            android.util.Log.i("myApplication", "Response from localhost/client/me " + responseCode);
                            android.util.Log.i("MyApplication", "Client email is " + (authResponse != null ? authResponse.getData().getUsername() : null));

                            Intent intent = new Intent(MainActivity.this, activity_user.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.putExtra("device_id", s);
                            intent.putExtra("mail", authResponse.getData().getUsername());
                            intent.putExtra("password", authResponse.getData().getPassword());
                            startActivity(intent);

                        }
                        else {
                            otpTextView.showError();
                            Toast.makeText(MainActivity.this, "Authentication is not successful.", Toast.LENGTH_LONG);
                            android.util.Log.w("myApplication", "Response is not gathered successfully! (localhost/client/me)");

                        }
                    }

                    @Override
                    public void onFailure(Call<PythonAuthResponse> call, Throwable t) {
                        dismissDialog();
                        otpTextView.resetState();

                        android.util.Log.e("myApplication", "Python API call (localhost/client/me) failed with the following error: " + t.getMessage());
                        android.util.Log.e("myApplication", "Stack trace: " + t);

                    }
                });

            }
        });

//        Button errorButton = findViewById(R.id.button);
//        Button successButton = findViewById(R.id.button2);
//
//        errorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                otpTextView.showError();
//            }
//        });
//
//        successButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                otpTextView.showSuccess();
//            }
//        });

//



//
//        val errorButton = findViewById<Button>(R.id.button)
//                val successButton = findViewById<Button>(R.id.button2)
//                val otpTextView = findViewById(R.id.otp_view) as OTPTextView
//        otpTextView.requestFocusOTP()
//        otpTextView.otpListener = object : OTPListener {
//            override fun onInteractionListener() {
//
//            }
//
//            override fun onOTPComplete(otp: String) {
//                Toast.makeText(this@SampleActivity1, "The OTP is $otp", Toast.LENGTH_SHORT).show()
//            }
//        }
//        errorButton.setOnClickListener { otpTextView.showError() }
//        successButton.setOnClickListener { otpTextView.showSuccess() }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public void showDialog(Context context, String message) {
        if(dialog == null){
            int llPadding = 30;
            LinearLayout ll = new LinearLayout(context);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setPadding(llPadding, llPadding, llPadding, llPadding);
            ll.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            llParam.gravity = Gravity.CENTER;
            ll.setLayoutParams(llParam);

            ProgressBar progressBar = new ProgressBar(context);
            progressBar.setIndeterminate(true);
            progressBar.setPadding(0, 0, llPadding, 0);
            progressBar.setLayoutParams(llParam);

            llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llParam.gravity = Gravity.CENTER;
            TextView tvText = new TextView(context);
            tvText.setText(message);
            tvText.setTextColor(Color.parseColor("#000000"));
            tvText.setTextSize(20);
            tvText.setLayoutParams(llParam);

            ll.addView(progressBar);
            ll.addView(tvText);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setView(ll);

            dialog = builder.create();
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);
            }
        }
    }

    public  boolean isDialogVisible(){
        if(dialog != null){
            return dialog.isShowing();
        }else {
            return false;
        }
    }

    public  void dismissDialog(){
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}