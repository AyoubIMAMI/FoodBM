package etu.polytech.foodbm;
import java.time.LocalDate; // import the LocalDate class


public class Plan {
    private String name;
    private double value;
    private LocalDate date; // Create a date object


    public Plan(String name, double value, LocalDate date){
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
