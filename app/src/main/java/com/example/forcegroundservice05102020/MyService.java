package com.example.forcegroundservice05102020;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    NotificationManager mNotificationManager;
    Notification mNotification;
    String CHANNEL_ID = "MY_CHANNEL";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = createNotification(this,CHANNEL_ID);
        startForeground(1, mNotification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
    private Notification createNotification(Context context , String channelId){
        NotificationCompat.Builder builder =
                new NotificationCompat
                        .Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setShowWhen(true)
                        .setContentTitle("Thong bao")
                        .setContentText("Service running");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    "MY_APP",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        return builder.build();

    }
}
