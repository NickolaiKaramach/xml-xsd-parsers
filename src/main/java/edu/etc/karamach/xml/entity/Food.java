package edu.etc.karamach.xml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Food implements Serializable {

    private static final long serialVersionUID = 6214373354610140788L;

    private int id;
    private String imageURI;
    private String name;
    private String description = "";
    private String weight;

    transient private List<Ingredient> foodIngredientsInside = new ArrayList<>();

    public Food() {
    }


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

    public void addIngredients(Ingredient ingredient) {
        foodIngredientsInside.add(ingredient);
    }

    public List<Ingredient> getFoodIngredientsInside() {
        return foodIngredientsInside;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Food.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("imageURI='" + imageURI + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("weight='" + weight + "'")
                .add("foodIngredientsInside=" + foodIngredientsInside)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;
        return id == food.id &&
                Objects.equals(imageURI, food.imageURI) &&
                Objects.equals(name, food.name) &&
                Objects.equals(description, food.description) &&
                Objects.equals(weight, food.weight) &&
                Objects.equals(foodIngredientsInside, food.foodIngredientsInside);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageURI, name, description, weight, foodIngredientsInside);
    }
}
