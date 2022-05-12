package etu.polytech.foodbm.model;

public class ButtonModel {
    public int id;
    public String name;
    public Class redirection;

    public ButtonModel(int id, String name, Class redirection) {
        this.id = id;
        this.name = name;
        this.redirection = redirection;
    }

    @Override
    public String toString() {
        return "ButtonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", redirection=" + redirection +
                '}';
    }
}



























