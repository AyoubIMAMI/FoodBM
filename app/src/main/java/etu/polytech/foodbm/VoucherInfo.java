package etu.polytech.foodbm;


import android.media.Image;

public class VoucherInfo {
    private String VName="coupon";
    private String Description=new String();
    private String VDate;


    public VoucherInfo(){
    }


    public void setVName(String VName) {
        this.VName = VName;

    }
    public String getVName() {
        return VName;
    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description =Description;
    }

    public String getVDate(String date) {
        return VDate;
    }

    public void setVDate(String PlanDate) {
        this.VDate =PlanDate;
    }

}