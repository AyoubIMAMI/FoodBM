package etu.polytech.foodbm.controller;

import android.app.Activity;

import androidx.core.app.NotificationCompat;

import etu.polytech.foodbm.NotificationActivity;
import etu.polytech.foodbm.model.NotificationModel;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;

public class NotificationController {
    private final NotificationModel notificationModel;
    private final Activity activity;

    public NotificationController(Activity activity) {
        this.activity = activity;
        this.notificationModel = new NotificationModel(this);
    }

    public void sendNotification(){
        this.notificationModel.sendNotification();
    }

    public void onNotificationSent(String name, String description, int priorityDefault, int notificationId) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(activity, channel_ID)
                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle(name)
                .setContentText(description)
                .setPriority(priorityDefault);

        NotificationActivity.getNotificationManager().notify(notificationId, notification.build());
    }
}
