package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.MainPlanDisplayController;
import etu.polytech.foodbm.controller.NotificationController;

public class MainActivity extends AppCompatActivity {
    CurrencyController currencyController;
    NotificationController notificationController;
    MainPlanDisplayController mainPlanDisplayController;


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
    }
}