package etu.polytech.foodbm.controller;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.ListAdaptaterConso;
import etu.polytech.foodbm.R;
import etu.polytech.foodbm.model.Conso;
import etu.polytech.foodbm.model.ConsoDisplayModel;

public class ConsoDisplayController {
    private final ConsoDisplayModel planDisplayModel;
    private final Activity activity;

    public ConsoDisplayController(Activity activity){
        this.planDisplayModel = new ConsoDisplayModel(this);
        this.activity = activity;
    }

    public void displayPlans(){
        this.planDisplayModel.displayPlan();
    }

    public void onPlanDisplayed(ArrayList<Conso> plans){
        ListAdaptaterConso listAdapterConsoPlan = new ListAdaptaterConso(activity, plans);
        ListView planListView = activity.findViewById(R.id.listCategorie);
        planListView.setAdapter(listAdapterConsoPlan);
    }
}


















