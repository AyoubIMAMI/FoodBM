package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanManagerActivity extends AppCompatActivity {
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
    }
}