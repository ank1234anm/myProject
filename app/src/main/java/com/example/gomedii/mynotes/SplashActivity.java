package com.example.gomedii.mynotes;


import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import java.util.List;




public class SplashActivity extends AppCompatActivity
{
    private Handler handler;
    private DelayTask  delayTask;
    Context context;
    private int REQUESTCODE = 200;
    private String slug;
    private Uri deepLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        delayTask = new DelayTask();
        handler.postDelayed(delayTask, 1500);
    }
    private class DelayTask implements  Runnable {
        @Override
        public void run() {
       Intent intent = new Intent(SplashActivity.this,ListOfNotesActivity.class);
       startActivity(intent);
       finish();
        }
    }
    }




