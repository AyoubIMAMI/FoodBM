package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
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
    }
}