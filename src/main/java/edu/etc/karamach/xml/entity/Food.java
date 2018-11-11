package edu.etc.karamach.xml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Food implements Serializable {

    private static final long serialVersionUID = 576752808367632305L;

    private int id;
    private String imageURI;
    private String name;
    transient private List<FoodType> foodTypesInside = new ArrayList<>();

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

    public void addFoodType(FoodType foodType) {
        foodTypesInside.add(foodType);
    }

    public List<FoodType> getFoodTypesInside() {
        return foodTypesInside;
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
                foodTypesInside.equals(food.getFoodTypesInside());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageURI, name, foodTypesInside);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "id=" + id +
                ", imageURI='" + imageURI + '\'' +
                ", name='" + name + '\'' +
                ", " + foodTypesInside +
                '}';
    }
}
