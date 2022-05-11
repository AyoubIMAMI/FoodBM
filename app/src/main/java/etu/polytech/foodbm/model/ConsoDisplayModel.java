package etu.polytech.foodbm.model;

import java.util.ArrayList;

import etu.polytech.foodbm.controller.ConsoDisplayController;
import etu.polytech.foodbm.controller.MainPlanDisplayController;

public class ConsoDisplayModel {
    private ConsoDisplayController controller;
    private ArrayList<Conso> listPlan;

    public ConsoDisplayModel(ConsoDisplayController controller) {
        this.controller = controller;
    }

    public void displayPlan(){
        this.listPlan = new ArrayList<>();

        listPlan.add(new Conso("Viande", 10));
        listPlan.add(new Conso("Legumes", 15));
        listPlan.add(new Conso("Bonbon", 5));
        for(int i= 0 ; i < 20 ; i++)
            listPlan.add(new Conso("Catégorie n°"+i, 100+i));

        this.controller.onPlanDisplayed(listPlan);
    }
}
