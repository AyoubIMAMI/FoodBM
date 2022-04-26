package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.controller.NotificationController;

public class ManageNotificationActivity extends AppCompatActivity {

    private NotificationController notificationController;
    private ArrayList<Notification> listNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notification);
        Intent intent = getIntent();
        /*if (intent != null){
            this.notificationController = intent.getParcelableExtra("notifMan");
        }*/
        this.listNotification = new ArrayList<>();
        for(int i= 0 ; i < 20 ; i++)listNotification.add(new Notification());
        ListAdaptaterNotification listAdaptaterNotifPlan = new ListAdaptaterNotification(ManageNotificationActivity.this, listNotification);
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
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.planicon)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, ManageNotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}