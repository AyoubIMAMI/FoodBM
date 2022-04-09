package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.helpers.CurrencyHelper;

public class MainActivity extends AppCompatActivity {

    //ActivityMainBinding binding;

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
    }
}