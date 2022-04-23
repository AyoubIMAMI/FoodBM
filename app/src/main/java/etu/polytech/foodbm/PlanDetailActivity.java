package etu.polytech.foodbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanDetailActivity extends AppCompatActivity {

    private Plan plan;
    private TextView nomTextView;
    private TextView valueTextView;
    private TextView dateTextView;

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

        ArrayList<Conso> listPlan = new ArrayList<>();
        listPlan.add(new Conso("Conso perso", 100));
        listPlan.add(new Conso("Travail Chine", 100));
        listPlan.add(new Conso("Travail Espagne", 100));
        for(int i= 0 ; i < 20 ; i++)listPlan.add(new Conso("Travail Espagne n°"+i, 100+i));


        ListAdaptaterConso listAdaptaterConsoPlan = new ListAdaptaterConso(PlanDetailActivity.this, listPlan);
        ListView planListView = findViewById(R.id.listCategorie);
        planListView.setAdapter(listAdaptaterConsoPlan);
    }
}