package etu.polytech.foodbm;


import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.MainPlanDisplayController;
import etu.polytech.foodbm.controller.NotificationController;

public class MainActivity extends AppCompatActivity {
    public CurrencyController currencyController;
    public NotificationController notificationController;
    public MainPlanDisplayController mainPlanDisplayController;
    public ImageView notifIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBarFragment navBarFragment = new NavBarFragment();


        // MVC currency convertor call
        currencyController = new CurrencyController(this);
        currencyController.convertCurrency();

        // MVC notification controller call
        notificationController = new NotificationController(this);
        notificationController.sendNotification();

        // MVC planDisplay controller call
        mainPlanDisplayController = new MainPlanDisplayController(this);
        mainPlanDisplayController.displayPlans();

        /*notifIcon = findViewById(R.id.notifImageView);
        notifIcon.setOnClickListener(view -> {
            //notificationController.navigateToNotification(requireActivity());
            /*Intent intent = new Intent(MainActivity.this, ManageNotificationActivity.class);
            //intent.putExtra("notifMan", this);
            startActivity(intent);
        });*/

    }
}