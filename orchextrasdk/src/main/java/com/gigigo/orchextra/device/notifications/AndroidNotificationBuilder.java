package com.gigigo.orchextra.device.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.gigigo.ggglib.device.AndroidSdkVersion;
import com.gigigo.orchextra.R;
import com.gigigo.orchextra.device.notifications.dtos.AndroidBasicAction;
import com.gigigo.orchextra.domain.model.actions.strategy.Notification;

public class AndroidNotificationBuilder {

    public static final String EXTRA_NOTIFICATION_ACTION = "OX_EXTRA_NOTIFICATION_ACTION";

    private final Context context;

    public AndroidNotificationBuilder(Context context) {
        this.context = context;
    }

    public void createNotification(Notification notification, PendingIntent pendingIntent) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(getSmallIconResourceId())
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody())
                        .setTicker(notification.getTitle())
                        .setWhen(System.currentTimeMillis())
                        .setColor(context.getResources().getColor(R.color.orc_notification_background))
                        .setAutoCancel(true);

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)(System.currentTimeMillis()%Integer.MAX_VALUE), builder.build());
    }

    public PendingIntent getPendingIntent(AndroidBasicAction androidBasicAction) {
        PackageManager pm = context.getPackageManager();

        Intent intent = pm.getLaunchIntentForPackage(context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(String.valueOf(System.currentTimeMillis()));
        intent.putExtra(EXTRA_NOTIFICATION_ACTION, androidBasicAction);

        return PendingIntent.getActivity(context, 1, intent, 0);
    }

    private int getSmallIconResourceId() {
        return AndroidSdkVersion.hasLollipop21() ? R.drawable.orc_ic_notification_alpha : R.drawable.orc_ic_notification_color;
    }
}
