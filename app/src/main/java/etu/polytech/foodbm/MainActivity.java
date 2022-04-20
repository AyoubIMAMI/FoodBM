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

        ArrayList<Conso> listConso = new ArrayList<>();
        listConso.add(new Conso(new PlanInfo().getPlanName(), 200));
        listConso.add(new Conso("Sauce", 100));
        //Conso consoPate = new Conso("Pate", 200);
        //Conso consoSauce = new Conso("Sauce", 100);

        ListAdaptater listAdaptater = new ListAdaptater(MainActivity.this, listConso);
        ListView planListView = findViewById(R.id.plans);
        planListView.setAdapter(listAdaptater);

        ListView historiqueListView = findViewById(R.id.historique);
        historiqueListView.setAdapter(listAdaptater);
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