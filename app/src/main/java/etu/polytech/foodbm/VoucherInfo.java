package etu.polytech.foodbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class VoucherInfo {
    private String VName;
    private String Description;
    private String VDate;
    public VoucherInfo() {

    }





        public String getVName() {
        return this.VName;
    }

    public void setVName(String PlanName) {
        this.VName = PlanName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Periode) {
        this.Description =Periode;
    }

    public String getVDate(String date) {
        return VDate;
    }


   }

