package com.freelance.anantahairstudioadmin.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.freelance.anantahairstudioadmin.activities.MainActivity;
import com.freelance.anantahairstudioadmin.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    public FCMService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotifications(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("token"," "+token);
    }

    public  void showNotifications(String title, String message){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"AnantaHairStudioNotification")
                .setSmallIcon(R.drawable.main_logo)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, builder.build());

    }
}