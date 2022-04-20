package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class  PlanManagerActivity extends AppCompatActivity {
    private Button CreatePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_manager);
        this.CreatePlan = (Button) findViewById(R.id.addPlanButton);
        CreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanManagerActivity.this, CreatePlanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Conso> listPlan = new ArrayList<>();
        listPlan.add(new Conso("Conso perso", 100));
        listPlan.add(new Conso("Travail Chine", 100));
        listPlan.add(new Conso("Travail Espagne", 100));
        for(int i= 0 ; i < 20 ; i++)listPlan.add(new Conso("Travail Espagne n°"+i, 100+i));


        ListAdaptater listAdaptaterPlan = new ListAdaptater(PlanManagerActivity.this, listPlan);
        ListView planListView = findViewById(R.id.planListView);
        planListView.setAdapter(listAdaptaterPlan);
    }
}