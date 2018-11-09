package edu.etc.karamach.xml.entity.Menu;

import edu.etc.karamach.xml.entity.Food;

import java.io.Serializable;
import java.util.List;

public interface FoodMenu extends Serializable {
    void addFood(Food food);

    void removeFood(Food food);

    void clear();

    String printMenu();

    List<Food> getMenuAsList();
}
