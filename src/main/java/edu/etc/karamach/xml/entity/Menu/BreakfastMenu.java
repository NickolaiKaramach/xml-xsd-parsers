package edu.etc.karamach.xml.entity.Menu;

import edu.etc.karamach.xml.entity.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BreakfastMenu implements FoodMenu {
    private static final long serialVersionUID = 5867380930531472016L;
    private List<Food> foodMenu = new ArrayList<>();

    public void addFood(Food food) {
        foodMenu.add(food);
    }

    public void removeFood(Food food) {
        foodMenu.remove(food);
    }

    public void clear() {
        foodMenu.clear();
    }

    public String printMenu() {
        StringBuilder stringBuilder = new StringBuilder("Today's breakfast menu:\n");

        for (Food food : foodMenu) {
            stringBuilder.append(food);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public List<Food> getMenuAsList() {
        return foodMenu;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BreakfastMenu breakfastMenu = (BreakfastMenu) obj;
        return foodMenu.equals(breakfastMenu.getMenuAsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodMenu);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "foodMenu=" + foodMenu +
                '}';
    }
}
