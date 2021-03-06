package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import etu.polytech.foodbm.controller.NotificationController;
import etu.polytech.foodbm.model.NotificationComparable;

public class SettingsActivity extends AppCompatActivity {
    Spinner spinner;
    private LinearLayout notifLayout;
    private ArrayList<NotificationComparable> listNotificationOriginal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavFragment navFragment = NavFragment.newInstance(R.id.settingButton);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, navFragment).commit();

        setContentView(R.layout.activity_settings);
        spinner = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List<String> exempleList = new ArrayList();
        exempleList.add("01");
        exempleList.add("02");
        exempleList.add("03");
        for(int i = 1  ; i<=31 ; i++)exempleList.add(String.valueOf(i));

        /*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
        Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );


        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);

        notifLayout = findViewById(R.id.notifLayout);
        notifLayout.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, ManageNotificationActivity.class);
            //intent.putExtra("listNotif", listNotificationOriginal);
            startActivity(intent);
        });
    }
}