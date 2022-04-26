package etu.polytech.foodbm.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import etu.polytech.foodbm.MainActivity;
import etu.polytech.foodbm.NotificationActivity;
import etu.polytech.foodbm.R;
import etu.polytech.foodbm.model.NotificationModel;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;




public class NotificationController  extends AppCompatActivity {

    private final NotificationModel notificationModel;
    private final Activity activity;
    private Bitmap bitmap;

    public NotificationController(Activity activity)  {
        this.activity = activity;
        this.notificationModel = new NotificationModel(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.televha);
    }
    public void sendNotification(){
        this.notificationModel.sendNotification();
    }

    public void onNotificationSent(String name, String description, int priorityDefault, int notificationId) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(activity, channel_ID)

                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle("Alerte Coupon")
                .setContentText(name)
                .setPriority(priorityDefault)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(this.bitmap)).setTimeoutAfter(10000);

        NotificationActivity.getNotificationManager().notify(notificationId, notification.build());
    }



}
