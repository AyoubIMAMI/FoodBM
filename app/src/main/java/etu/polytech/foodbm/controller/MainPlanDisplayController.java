package etu.polytech.foodbm.controller;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import etu.polytech.foodbm.model.Conso;
import etu.polytech.foodbm.ListAdaptaterConso;
import etu.polytech.foodbm.R;
import etu.polytech.foodbm.model.MainPlanDisplayModel;


public class MainPlanDisplayController {
    private final MainPlanDisplayModel mainPlanDisplayModel;
    private final Activity activity;

    public MainPlanDisplayController(Activity activity){
        this.mainPlanDisplayModel = new MainPlanDisplayModel(this);
        this.activity = activity;
    }

    public void displayPlans(){
        this.mainPlanDisplayModel.displayPlan();
    }

    public void onPlanDisplayed(ArrayList<Conso> plans){
        ListAdaptaterConso listAdapterConsoPlan = new ListAdaptaterConso(activity, plans);
        ListView planListView = activity.findViewById(R.id.plans);
        planListView.setAdapter(listAdapterConsoPlan);
    }
}
