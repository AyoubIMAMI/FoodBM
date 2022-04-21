package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class  PlanManagerActivity extends AppCompatActivity {
    private TextView CreatePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_manager);
        this.CreatePlan = (TextView) findViewById(R.id.addPlanButton);
        CreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanManagerActivity.this, CreatePlanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Plan> listPlan = new ArrayList<>();
        listPlan.add(new Plan("Conso perso", 100, LocalDate.now()));
        listPlan.add(new Plan("Travail Chine", 100,LocalDate.now()));
        listPlan.add(new Plan("Travail Espagne", 100,LocalDate.now()));
        for(int i= 0 ; i < 20 ; i++)listPlan.add(new Plan("Travail Espagne n°"+i, 100+i,LocalDate.now()));


        ListAdaptaterPlan listAdaptaterConsoPlan = new ListAdaptaterPlan(PlanManagerActivity.this, listPlan);
        ListView planListView = findViewById(R.id.planListView);
        planListView.setAdapter(listAdaptaterConsoPlan);

        planListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Plan planSelected = (Plan) planListView.getItemAtPosition(i);
                Intent intent = new Intent(PlanManagerActivity.this, PlanDetailActivity.class);
                intent.putExtra("plan", planSelected);
                startActivity(intent);
            }
        });
    }
}