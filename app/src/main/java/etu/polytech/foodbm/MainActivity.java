package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import etu.polytech.foodbm.helpers.CurrencyHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBarFragment navBarFragment = new NavBarFragment();
        CurrencyHelper currencyHelper = new CurrencyHelper(this);
        currencyHelper.execute("EUR");
    }
}