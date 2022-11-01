package com.example.tesstbus;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class Foreground extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        createNotification();
        Intent intent1 = new Intent(this,MainActivity.class);

        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent1,0);

        Notification notification = new NotificationCompat.Builder(this,"Channel").setContentText("Application is Running")
                .setContentTitle("Location Service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        startForeground(1, notification);

        return START_STICKY;

    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel=new NotificationChannel(
                    "Channell", "Foreground Service", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        stopSelf();
        super.onDestroy();
        Toast.makeText(this, "Stoping Service", Toast.LENGTH_SHORT).show();
    }
}
