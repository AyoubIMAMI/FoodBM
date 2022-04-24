package etu.polytech.foodbm.model;

public class Conso {
    private String name;
    private double value;

    public Conso(String name, double value){
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
