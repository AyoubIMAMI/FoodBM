package etu.polytech.foodbm;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import etu.polytech.foodbm.controller.NotificationController;
import etu.polytech.foodbm.model.NotificationComparable;

public class ManageNotificationActivity extends AppCompatActivity {

    private NotificationController notificationController;
    private ArrayList<NotificationComparable> listNotification;
    private int indice = 0;
    private ListAdaptaterNotification listAdaptaterNotifPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notification);
        Intent intent = getIntent();
        /*if (intent != null){
            this.notificationController = intent.getParcelableExtra("notifMan");
        }*/
        this.listNotification = new ArrayList<>();
       // for(int i= 0 ; i < 5 ; i++)listNotification.add(createNotification("Notification n°"+i,"C'est la notif "+i));
        listAdaptaterNotifPlan = new ListAdaptaterNotification(ManageNotificationActivity.this, listNotification);
        ListView notifListView = findViewById(R.id.notifListView);
        notifListView.setAdapter(listAdaptaterNotifPlan);


        Button addNotif = (Button) findViewById(R.id.addNotifButton);
        addNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }

    private void addNotification() {
        NotificationComparable notif = createNotification("Notification","Je suis une notif");
        NotificationActivity.getNotificationManager().notify(indice++, notif.getNotification());
        listNotification.add(notif);
        Collections.sort(listNotification);
        Collections.reverse(listNotification);
        listAdaptaterNotifPlan.notifyDataSetChanged();
    }

    NotificationComparable createNotification(String titre, String text){
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channel_ID)
                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle(titre+indice)
                .setContentText(text);

        Notification notif = notification.build();
        return new NotificationComparable(notif, new Date());
    }

}