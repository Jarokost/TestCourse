package org.example.meal;

import java.util.ArrayList;
import java.util.Collections;
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
        return meals.stream()
                .filter(meal -> exactMatch?meal.getName().equals(mealName):meal.getName().startsWith(mealName))
                .collect(Collectors.toList());
    }

    public List<Meal> findByPrice(int price, SearchOption searchOption) {
        return findByPriceWithInitialData(price, searchOption, meals);
    }

    public List<Meal> findByPriceWithInitialData(int price, SearchOption searchOption, List<Meal> initialData) {

        List<Meal> result = new ArrayList<>();

        switch(searchOption) {
            case SearchOption.EQUAL ->
            result = initialData.stream()
                    .filter(meal -> meal.getPrice() == price)
                    .collect(Collectors.toList());

            case SearchOption.LOWER ->
                    result = initialData.stream()
                            .filter(meal -> meal.getPrice() < price)
                            .collect(Collectors.toList());

            case SearchOption.HIGHER ->
                    result = initialData.stream()
                            .filter(meal -> meal.getPrice() > price)
                            .collect(Collectors.toList());
        }

        return result;
    }

    public List<Meal> find(String mealName, boolean exactMatch, int price, SearchOption searchOption) {
        List<Meal> nameMatches = findByName(mealName, exactMatch);
        return findByPriceWithInitialData(price, searchOption, nameMatches);
    }
}
