package etu.polytech.foodbm;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.model.CurrencyModel;

public class MainActivity extends AppCompatActivity {
    //ActivityMainBinding binding;
    VoucherInfo voucherInfo;
    private int notificationId=0;
    CurrencyController currencyController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBarFragment navBarFragment = new NavBarFragment();

        // MVC currency convertor call
        currencyController = new CurrencyController(this);
        currencyController.convertCurrency();

        ArrayList<Conso> listPlan = new ArrayList<>();
        listPlan.add(new Conso("Conso perso", 100));
        listPlan.add(new Conso("Travail Chine", 100));
        listPlan.add(new Conso("Travail Espagne", 100));
        for(int i= 0 ; i < 20 ; i++)listPlan.add(new Conso("Travail Espagne n°"+i, 100+i));


        ListAdaptaterConso listAdaptaterConsoPlan = new ListAdaptaterConso(MainActivity.this, listPlan);
        ListView planListView = findViewById(R.id.plans);
        planListView.setAdapter(listAdaptaterConsoPlan);


        //--------------------------------
        voucherInfo=new VoucherInfo();
        String name=voucherInfo.getVDate();
        String description2=voucherInfo.getDescription();
        sendNotificationchannel(name,description2,channel_ID, NotificationCompat.PRIORITY_DEFAULT);

    }

    private void sendNotificationchannel(String name, String description, String channel_id, int priorityDefault) {
        NotificationCompat.Builder notification=new NotificationCompat.Builder(getApplicationContext(),channel_id)
                .setSmallIcon(com.google.firebase.database.ktx.R.drawable.common_full_open_on_phone)
                .setContentTitle(name)
                .setContentText(description)
                .setPriority(priorityDefault);
        NotificationActivity.getNotificationManager().notify(++notificationId,notification.build());
    }

}