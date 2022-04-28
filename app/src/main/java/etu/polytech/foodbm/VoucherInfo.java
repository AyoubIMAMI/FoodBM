package etu.polytech.foodbm;


import android.media.Image;

public class VoucherInfo {
    private String VName="coupon";
    private String Description=new String();
    private  String ImageID=new String();


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

    public String getVDate() {
        return VDate;
    }

    public void setVDate(String VDate) {
        this.VDate = VDate;
    }

    private String VDate;

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }
}