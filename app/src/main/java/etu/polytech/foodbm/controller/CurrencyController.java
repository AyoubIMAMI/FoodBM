package etu.polytech.foodbm.controller;

import android.app.Activity;
import android.widget.TextView;

import etu.polytech.foodbm.R;
import etu.polytech.foodbm.model.CurrencyModel;

public class CurrencyController {
    private final CurrencyModel currencyModel;
    private final Activity activity;

    public CurrencyController(Activity activity){
        this.activity = activity;
        this.currencyModel = new CurrencyModel(this);
    }

    public void convertCurrency(){
        currencyModel.execute("10", "EUR", "USD");
    }

    public void onCurrencyConverted(String currencyDisplay){
        TextView currencyTextView = activity.findViewById(R.id.totalValue);
        currencyTextView.setText(currencyDisplay);
    }
}
