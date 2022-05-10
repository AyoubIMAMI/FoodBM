package etu.polytech.foodbm.controller;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import etu.polytech.foodbm.MainActivity;
import etu.polytech.foodbm.ManageNotificationActivity;
import etu.polytech.foodbm.NotificationActivity;
import etu.polytech.foodbm.R;
import etu.polytech.foodbm.SettingsActivity;
import etu.polytech.foodbm.model.NotificationComparable;
import etu.polytech.foodbm.model.NotificationModel;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;

import com.google.firebase.database.core.Tag;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;


public class NotificationController extends AppCompatActivity{

    public static ArrayList<NotificationComparable> listNotificationOriginal;
    private final NotificationModel notificationModel;
    private final Activity activity;

    private Bitmap bitmap;
    private String simage;


    public NotificationController(Activity activity)  {
        this.activity = activity;
        this.notificationModel = new NotificationModel(this);
        this.bitmap=BitmapFactory.decodeResource(activity.getResources(), R.drawable.coupon);
        this.listNotificationOriginal = new ArrayList<>();

        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte [] bytes=stream.toByteArray();
        simage= Base64.encodeToString(bytes,Base64.DEFAULT);
        Log.d(TAG,"image est "+simage);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void sendNotification(){
        this.notificationModel.sendNotification();
    }

    public Notification onNotificationSent(String name, String description, int priorityDefault, int notificationId) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(activity, channel_ID)

                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle("Alerte Coupon")
                .setContentText(name)
                .setGroup("my voucher")
                .setPriority(priorityDefault)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(this.bitmap)).setTimeoutAfter(10000);

        Notification notif = notification.build();
      //  NotificationActivity.getNotificationManager().notify(notificationId, notif);

        for(int i=0;i<3;i++){
            SystemClock.sleep(500);
            NotificationActivity.getNotificationManager().notify(notificationId, notif);

        }
        NotificationComparable notifComparable = new NotificationComparable(notif, new Date(), "Alerte Coupon", name);
        listNotificationOriginal.add(notifComparable);

        return notif;
    }
    public Notification onNotificationSent2(String name, String description, int priorityDefault, Bitmap notimage, int notificationId) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(activity, channel_ID)

                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle("Alerte Coupon")
                .setContentText(name)
                .setGroup("my voucher")
                .setPriority(priorityDefault)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(notimage)).setTimeoutAfter(100000);

        Notification notif = notification.build();
       // NotificationActivity.getNotificationManager().notify(notificationId, notif);
        for(int i=0;i<3;i++){
            SystemClock.sleep(500);
            NotificationActivity.getNotificationManager().notify(notificationId, notif);

        }
        NotificationComparable notifComparable = new NotificationComparable(notif, new Date(), "Alerte Coupon", name);
        listNotificationOriginal.add(notifComparable);

        return notif;
    }

    public void navigateToNotification(Context c){
        Intent intent = new Intent(c, ManageNotificationActivity.class);
        //intent.putExtra("notifMan", this);
        startActivity(intent);
    }

    public ArrayList<NotificationComparable> getListNotificationOriginal() {
        return listNotificationOriginal;
    }
}
