package org.example.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class MealRepositoryTest {

    MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {

        //given
        Meal meal = new Meal(10, "pizza");

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().getFirst(), is(meal));

    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {

        //given
        Meal meal = new Meal(10, "pizza");
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByExactName() {

        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("pizza", true);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByStartingLetters() {

        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("p", false);

        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void shouldBeAbleToFindMealByExactPrice() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(7, "spaghetti");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchOption.EQUAL);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.getFirst(), is(meal));
    }

    @Test
    void shouldBeAbleToFindMealByLowerPrice() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(7, "spaghetti");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(8, SearchOption.LOWER);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.getFirst(), is(meal2));
    }

    @Test
    void shouldBeAbleToFindMealByHigherPrice() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(7, "spaghetti");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(9, SearchOption.HIGHER);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.getFirst(), is(meal));
    }
}
