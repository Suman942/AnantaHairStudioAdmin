package com.freelance.anantahairstudioadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.databinding.ActivityMainBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;
import com.freelance.anantahairstudioadmin.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        notifications();
        splashScreenThread();

    }
    private void notifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel(
                    "AnantaHairStudioNotification",
                    "AnantaHairStudio",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic("Booking")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(HomeActivity.this, "Notification Sent", Toast.LENGTH_SHORT).show();
                        }
////                        Log.d(TAG, msg);
                    }
                });
    }
    private void splashScreenThread() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                finish();
            }
        }, 3000);
    }
}