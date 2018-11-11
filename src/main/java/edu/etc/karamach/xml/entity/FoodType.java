package edu.etc.karamach.xml.entity;

import java.io.Serializable;
import java.util.Objects;

public class FoodType implements Serializable {
    private static final long serialVersionUID = 1725026354145119585L;
    private String description;
    private String weight;
    private double price;

    public FoodType(String description, String weight, double price) {
        this.description = description;
        this.weight = weight;
        this.price = price;
    }

    public FoodType() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "description='" + description + '\'' +
                ", weight='" + weight + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodType foodType = (FoodType) o;
        return Double.compare(foodType.price, price) == 0 &&
                Objects.equals(description, foodType.description) &&
                Objects.equals(weight, foodType.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, price);
    }
}
