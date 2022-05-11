package etu.polytech.foodbm.model;

import java.time.LocalDate;
import java.util.ArrayList;

import etu.polytech.foodbm.controller.MainPlanDisplayController;

public class MainPlanDisplayModel {
    private MainPlanDisplayController controller;
    private ArrayList<Plan> listPlan;

    public MainPlanDisplayModel(MainPlanDisplayController controller) {
        this.controller = controller;
    }

    public void displayPlan(){
        this.listPlan = new ArrayList<>();

        listPlan.add(new Plan("Plan perso", 100, LocalDate.now()));
        listPlan.add(new Plan("Travail Chine", 100, LocalDate.now()));
        listPlan.add(new Plan("Travail Espagne", 100, LocalDate.now()));
        for(int i= 0 ; i < 20 ; i++)
            listPlan.add(new Plan("Travail Espagne n°"+i, 100+i, LocalDate.now()));

        this.controller.onPlanDisplayed(listPlan);
    }
}
