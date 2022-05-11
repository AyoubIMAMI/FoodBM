package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;

import etu.polytech.foodbm.controller.ConsoDisplayController;
import etu.polytech.foodbm.model.Plan;

public class PlanDetailActivity extends AppCompatActivity {

    private Plan plan;
    private TextView nomTextView;
    private TextView valueTextView;
    private TextView dateTextView;
    private ConsoDisplayController planDisplayController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        NavFragment navFragment = NavFragment.newInstance(R.id.planButton);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, navFragment).commit();

        this.nomTextView = findViewById(R.id.Nom);
        this.valueTextView = findViewById(R.id.Value);
        this.dateTextView = findViewById(R.id.Date);

        this.plan = new Plan("Espagne",100.5, LocalDate.now());
        Intent intent = getIntent();
        if (intent != null){
            this.plan = intent.getParcelableExtra("plan");
            if (this.plan != null){
                this.nomTextView.setText(this.plan.getName());
                this.valueTextView.setText(String.valueOf(this.plan.getValue()));
                this.dateTextView.setText(this.plan.getDate().toString());
            }
        }

        // MVC planDisplay controller call
        planDisplayController = new ConsoDisplayController(this);
        planDisplayController.displayPlans();
    }
}