package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Category;
import com.hussein.Food.Ordering.model.Food;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.request.CreateFoodRequest;

import java.util.List;

/**
 * This is the interface of the food service, allowing you to create, find and manipulate food.
 * @author Hussein Abdallah
 */
public interface FoodServiceInterface {

    /**
     * Creates a new food
     * @param req the information of the food to create
     * @param restaurant the restaurant selling the food
     * @return the resulting food
     */
    public Food createFood(CreateFoodRequest req, Restaurant restaurant);

    /**
     * Deletes the food with the given id.
     * @param foodId the id of the food to delete
     * @throws Exception if food is not found
     */
    void deleteFood(Long foodId) throws Exception;

    /**
     * Gets the foods of a restaurant with given filters
     * @param restaurantId the id of the restaurant to pull foods of
     * @param isVegetarian pull only vegetarian foods
     * @param isNonVeg pull only non-vegetarian foods
     * @param isSeasonal pull only seasonal foods
     * @param foodCategory pull only foods belonging to provided category
     * @return the list of foods from the restaurant matching the filters
     * @throws Exception if restaurant is not found
     */
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonVeg,
                                         boolean isSeasonal, String foodCategory) throws Exception;

    /**
     * Searches for foods matching the keywords
     * @param keyword the keyword to search for
     * @return the list of foods found
     */
    public List<Food> searchFood(String keyword);

    /**
     * Finds food by its id.
     * @param foodId the id of the food to find
     * @return the food
     * @throws Exception if food is not found
     */
    public Food findById(Long foodId) throws Exception;

    /**
     * Updates availability status of food.
     * @param foodId the id of the food to update
     * @return the food
     * @throws Exception if food is not found
     */
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
