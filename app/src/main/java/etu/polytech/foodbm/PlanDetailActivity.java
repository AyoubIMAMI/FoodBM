package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;

import etu.polytech.foodbm.controller.MainPlanDisplayController;
import etu.polytech.foodbm.controller.PlanDisplayController;
import etu.polytech.foodbm.model.Plan;
import etu.polytech.foodbm.model.PlanDisplayModel;

public class PlanDetailActivity extends AppCompatActivity {

    private Plan plan;
    private TextView nomTextView;
    private TextView valueTextView;
    private TextView dateTextView;
    private PlanDisplayController planDisplayController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

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
        planDisplayController = new PlanDisplayController(this);
        planDisplayController.displayPlans();
    }
}