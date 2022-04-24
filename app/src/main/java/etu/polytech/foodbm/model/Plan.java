package etu.polytech.foodbm.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate; // import the LocalDate class


public class Plan implements Parcelable {
    private String name;
    private double value;
    private String date; // Create a date object


    public Plan(String name, double value, LocalDate date){
        this.name = name;
        this.value = value;
        this.date = date.toString();
    }

    protected Plan(Parcel in) {
        name = in.readString();
        value = in.readDouble();
        date = in.readString();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(value);
        parcel.writeString(date);
    }
}
