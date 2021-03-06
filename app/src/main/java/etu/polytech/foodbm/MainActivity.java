package etu.polytech.foodbm;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import etu.polytech.foodbm.controller.CurrencyController;
import etu.polytech.foodbm.controller.MainPlanDisplayController;
import etu.polytech.foodbm.controller.NotificationController;

public class MainActivity extends AppCompatActivity {
    public CurrencyController currencyController;
    public NotificationController notificationController;
    public MainPlanDisplayController mainPlanDisplayController;
    ConstraintLayout layout;


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

