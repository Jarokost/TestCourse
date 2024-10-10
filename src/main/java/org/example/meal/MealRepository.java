package org.example.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> findByName(String mealName, boolean exactMatch) {

        List<Meal> result;

        if(exactMatch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .collect(Collectors.toList());
        }

        return result;
    }

    public List<Meal> findByPrice(int price, SearchOption searchOption) {

        List<Meal> result;

        switch(searchOption) {
            case SearchOption.EQUAL ->
            result = meals.stream()
                    .filter(meal -> meal.getPrice() == price)
                    .collect(Collectors.toList());

            case SearchOption.LOWER ->
                    result = meals.stream()
                            .filter(meal -> meal.getPrice() < price)
                            .collect(Collectors.toList());

            case SearchOption.HIGHER ->
                    result = meals.stream()
                            .filter(meal -> meal.getPrice() > price)
                            .collect(Collectors.toList());

            default ->
                result = null;
        }

        return result;
    }
}
