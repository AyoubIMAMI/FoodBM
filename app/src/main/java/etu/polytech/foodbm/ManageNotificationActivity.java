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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import etu.polytech.foodbm.controller.NotificationController;
import etu.polytech.foodbm.model.NotificationComparable;

public class ManageNotificationActivity extends AppCompatActivity {

    private NotificationController notificationController;
    private ArrayList<NotificationComparable> listNotificationOriginal;
    private ArrayList<NotificationComparable> listNotification;
    private ArrayList<NotificationComparable> listNotificationFilter;

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
        this.listNotificationOriginal = new ArrayList<>();
        this.listNotification = new ArrayList<>();
        this.listNotificationFilter = new ArrayList<>();
         for(int i= 0 ; i < 5 ; i++)listNotificationOriginal.add(createNotification("Notification Init","C'est la notif "));

         this.listNotification = cloneList(this.listNotificationOriginal);
         setupListView(listNotification);


        Button addNotif = (Button) findViewById(R.id.addNotifButton);
        addNotif.setOnClickListener(view -> {
            addNotification();
        });

        Button filter = (Button) findViewById(R.id.filterValidationButton);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterNotif();
            }
        });
        //deleteFilterImageView
        ImageView deleteFilter = (ImageView) findViewById(R.id.deleteFilterImageView);
        deleteFilter.setOnClickListener(view -> {
                this.listNotification = cloneList(this.listNotificationOriginal);
                setupListView(listNotification);
        });
    }

    private void setupListView(ArrayList<NotificationComparable> list){
        listAdaptaterNotifPlan = new ListAdaptaterNotification(ManageNotificationActivity.this, list);
        ListView notifListView = findViewById(R.id.notifListView);
        notifListView.setAdapter(listAdaptaterNotifPlan);
    }

    private void addNotification() {
        NotificationComparable notif = createNotification("Notification","Je suis une notif");
        NotificationActivity.getNotificationManager().notify(indice++, notif.getNotification());
        listNotificationOriginal.add(notif);
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
        return new NotificationComparable(notif, new Date(), titre, text);
    }

    public void filterNotif(){
        this.listNotificationFilter = cloneList(this.listNotificationOriginal);
        //recupère les différents filter émis par l'utilisateur
        EditText titleEdit = findViewById(R.id.titleEditText);
        EditText descriptionEdit = findViewById(R.id.descriptionEditText);
        EditText dateEdit = findViewById(R.id.dateEditText);

        String title = titleEdit.getText().toString();
        String description = descriptionEdit.getText().toString();
        String date = dateEdit.getText().toString();


        //appelle les méthodes de ces filtres
        if(title != "")
            this.listNotificationFilter = filterByTitle(listNotificationFilter, title);
        if(description != "")
            this.listNotificationFilter = filterByDescription(listNotificationFilter, description);
        /*if(date != "")
            this.listNotificationFilter = filterByDate(listNotificationFilter, date);*/
        //réinstancie la listview
        this.listNotification = listNotificationFilter;
        setupListView(listNotification);
    }


    public ArrayList<NotificationComparable> filterByTitle(ArrayList<NotificationComparable> list, String title){
        return list.stream()
                .filter(
                notif -> notif.getTitle().contains(title))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<NotificationComparable> filterByDescription(ArrayList<NotificationComparable> list, String description){
        return list.stream().filter(notif -> notif.getDescription().contains(description)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<NotificationComparable> filterByDate(ArrayList<NotificationComparable> list, Date date){
        return list.stream().filter(notif -> notif.getNotifDate().toString() == date.toString()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<NotificationComparable> filterByBeforeDate(ArrayList<NotificationComparable> list, Date date){
        return list.stream().filter(notif -> notif.getNotifDate().before(date)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<NotificationComparable> filterByAfterDate(ArrayList<NotificationComparable> list, Date date){
        return list.stream().filter(notif -> notif.getNotifDate().after(date)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<NotificationComparable> cloneList(ArrayList<NotificationComparable> list) {
        ArrayList<NotificationComparable> clone = new ArrayList<NotificationComparable>(list.size());
        for (NotificationComparable item : list) clone.add(new NotificationComparable(item.getNotification(), item.getNotifDate(), item.getTitle(), item.getDescription()));
        return clone;
    }
}