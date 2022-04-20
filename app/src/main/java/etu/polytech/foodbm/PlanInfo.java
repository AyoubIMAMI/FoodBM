package etu.polytech.foodbm;

import java.util.Date;

public class PlanInfo {


    private String PlanName;
    private String Periode;
    private String PlanDate;


    public PlanInfo() {

    }


    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    public String getPeriode() {
        return Periode;
    }

    public void setPeriode(String Periode) {
        this.Periode=Periode;
    }

    public String getPlanDate() {
        return PlanDate;
    }

    public void setPlanDate(String PlanDate) {
        this.PlanDate=PlanDate;
    }
}

