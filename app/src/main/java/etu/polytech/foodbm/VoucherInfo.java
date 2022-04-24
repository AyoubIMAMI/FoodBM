package etu.polytech.foodbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class VoucherInfo extends AppCompatActivity {
    private String VName;
    private String Description;
    private String VDate;
    public VoucherInfo() {

    }





        public String getVName() {
        return this.VName;
    }

    public void setVName(String VoucherName) {
        this.VName = VoucherName;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description =Description;
    }

    public String getVDate() {
        return this.VDate;
    }
    public void setVDate(String VDate) {
        this.VDate =VDate;
    }


   }

