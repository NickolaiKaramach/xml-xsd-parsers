package edu.etc.karamach.xml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Food implements Serializable {

    private static final long serialVersionUID = -6817684030365398826L;

    private int id;
    private String imageURI;
    private String name;
    private ArrayList<String> description = new ArrayList<>();
    private String weight;
    private ArrayList<Double> prices = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight.trim();
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
    }

    public void addDescription(String description) {
        this.description.add(description.trim());
    }

    public void addPrice(Double price) {
        prices.add(price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Food food = (Food) obj;
        return id == food.getId() &&
                imageURI.equals(food.getImageURI()) &&
                name.equals(food.getName()) &&
                description.equals(food.getDescription()) &&
                weight.equals(food.getWeight()) &&
                prices.equals(food.getPrices());
    }

    //TODO: Replace with our hash?
    @Override
    public int hashCode() {
        return Objects.hash(id, imageURI, name, description, weight, prices);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "id=" + id +
                ", imageURI='" + imageURI + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", weight='" + weight + '\'' +
                ", prices=" + prices +
                '}';
    }
}
