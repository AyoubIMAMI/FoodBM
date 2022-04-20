package etu.polytech.foodbm;

import static etu.polytech.foodbm.NotificationActivity.channel_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.helpers.CurrencyHelper;

public class MainActivity extends AppCompatActivity {

    //ActivityMainBinding binding;
    VoucherInfo voucherInfo;
    private int notificationId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBarFragment navBarFragment = new NavBarFragment();
        CurrencyHelper currencyHelper = new CurrencyHelper(this);
        currencyHelper.execute("10", "EUR", "USD");

        ArrayList<Conso> listPlan = new ArrayList<>();
        listPlan.add(new Conso("Conso perso", 100));
        listPlan.add(new Conso("Travail Chine", 100));
        listPlan.add(new Conso("Travail Espagne", 100));
        for(int i= 0 ; i < 20 ; i++)listPlan.add(new Conso("Travail Espagne n°"+i, 100+i));


        ListAdaptater listAdaptaterPlan = new ListAdaptater(MainActivity.this, listPlan);
        ListView planListView = findViewById(R.id.plans);
        planListView.setAdapter(listAdaptaterPlan);


        //--------------------------------
        voucherInfo=new VoucherInfo();
        String name=voucherInfo.getVName();
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