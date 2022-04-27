package etu.polytech.foodbm.model;

import java.util.ArrayList;
import java.util.List;

import etu.polytech.foodbm.MainActivity;
import etu.polytech.foodbm.PlanManagerActivity;
import etu.polytech.foodbm.R;
import etu.polytech.foodbm.SettingsActivity;
import etu.polytech.foodbm.VoucherActivity;

public class ButtonsModel {
    public List<ButtonModel> buttons ;

    public ButtonsModel(){
        this.buttons = new ArrayList<>();
        buttons.add(new ButtonModel(R.id.homeButton, "Acceuil", MainActivity.class));
        buttons.add(new ButtonModel(R.id.voucherButton, "Coupons", VoucherActivity.class));
        buttons.add(new ButtonModel(R.id.planButton, "Plans", PlanManagerActivity.class));
        buttons.add(new ButtonModel(R.id.settingButton, "Options", SettingsActivity.class));
    }

    public ButtonModel getButtonModelByName(int id){
        for(ButtonModel buttonModel : buttons){
            if (buttonModel.id == id){
                return buttonModel;
            }
        }
        return null;
    }

    public List<ButtonModel> constructButtonModelList(){
        return buttons;
    }

}
