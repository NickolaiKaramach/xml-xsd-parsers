package edu.etc.karamach.xml.entity.Menu;

import edu.etc.karamach.xml.entity.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainMenu implements FoodMenu {
    private static final long serialVersionUID = 5350480323224019606L;
    private static final String START_MESSAGE = "Today's main menu:\n";
    private static final String NEW_LINE_REGEX = "\n";
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
        StringBuilder stringBuilder = new StringBuilder(START_MESSAGE);

        for (Food food : foodMenu) {
            stringBuilder.append(food);
            stringBuilder.append(NEW_LINE_REGEX);
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

        MainMenu mainMenu = (MainMenu) obj;
        return foodMenu.equals(mainMenu.getMenuAsList());
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
