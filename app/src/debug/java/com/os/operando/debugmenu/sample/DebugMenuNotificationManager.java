package com.os.operando.debugmenu.sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class DebugMenuNotificationManager {

    private static final String NOTIFICATION_TAG = DebugMenuNotificationManager.class.getName();
    private static final int NOTIFICATION_ID = Integer.MAX_VALUE;
    private static final String CHANNEL_ID = "debug";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "DebugNotification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void showDebugMenuNotification(Context context) {
        PendingIntent pendingIntent = DebugMenuActivity.createPendingIntent(context);
        Notification notification = buildNotification(context, pendingIntent);
        NotificationManager notificationManager = getNotificationManager(context);
        notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notification);
    }

    public static void cancelDebugMenuNotification(Context context) {
        NotificationManager notificationManager = getNotificationManager(context);
        notificationManager.cancel(NOTIFICATION_TAG, NOTIFICATION_ID);
    }

    private static Notification buildNotification(Context context, PendingIntent pendingIntent) {
        PackageManager packageManager = context.getPackageManager();
        String applicationName = context.getApplicationInfo().loadLabel(packageManager).toString();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setTicker("debug menu");
        builder.setOngoing(true);
        builder.setContentTitle(applicationName);
        builder.setContentText("タップするとDebug Menuが開きます");
        builder.setSmallIcon(R.drawable.ic_notification_small);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        return builder.build();
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}