package com.freelance.anantaadmin.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.allBooking.AllBookingsActivity;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FCMService extends FirebaseMessagingService {

    public FCMService() {
    }

//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        showNotifications(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
//    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("token"," "+token);
    }

    @Override
    public void handleIntent(Intent intent) {
        showNotifications(intent.getExtras().getString("gcm.notification.title"),intent.getExtras().getString("gcm.notification.body"));
    }


    public  void showNotifications(String title,String body){
        Intent intent = new Intent(this, AllBookingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"AnantaHairStudioNotification")
                .setSmallIcon(R.drawable.main_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{0L})
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(999 /* ID of notification */, builder.build());

    }
}