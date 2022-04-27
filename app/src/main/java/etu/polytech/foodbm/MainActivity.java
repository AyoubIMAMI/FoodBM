package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.MainPlanDisplayController;
import etu.polytech.foodbm.controller.NotificationController;
import etu.polytech.foodbm.model.ButtonModel;
import etu.polytech.foodbm.model.ButtonsModel;

public class MainActivity extends AppCompatActivity {
    CurrencyController currencyController;
    NotificationController notificationController;
    MainPlanDisplayController mainPlanDisplayController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavFragment navFragment = NavFragment.newInstance(R.id.homeButton);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, navFragment).commit();

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