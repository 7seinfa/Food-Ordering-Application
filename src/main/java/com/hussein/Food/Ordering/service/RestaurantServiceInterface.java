package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.dto.RestaurantDto;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.CreateRestaurantRequest;

import java.util.List;

/**
 * This is the interface of the restaurant service, allowing you to find and manipulate restaurants.
 * @author Hussein Abdallah
 */
public interface RestaurantServiceInterface {
    /**
     * Creates a restaurant with the details in req and the owner user
     * @param req the details needed to create the restaurant
     * @param user the owner of the restaurant
     * @return the Restaurant that was created
     */
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    /**
     * Updates the restaurant with id restaurantId with the details in updateRequest.
     * @param restaurantId the id of the restaurant to update
     * @param updateRequest the update details, if a detail is unchanged, leave that field null
     * @return the updated Restaurant
     * @throws Exception if restaurant not found
     */
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest) throws Exception;

    /**
     * Deletes restaurant with id restaurantId
     * @param restaurantId the id of the restaurant to delete
     * @throws Exception if restaurant not found
     */
    public void deleteRestaurant(Long restaurantId) throws Exception;

    /**
     * Get a list of all restaurants in the database
     * @return the list of all restaurants in the database
     */
    public List<Restaurant> getAllRestaurant();

    /**
     * Search for restaurants with the provided keyword.
     * The keyword is used to check the names as well as cuisine types of restaurants.
     * @param keyword the key word to search for
     * @return a list of the restaurants which match the search word
     */
    public List<Restaurant> searchRestaurant(String keyword);

    /**
     * Get the restaurant with the id restaurantId
     * @param restaurantId the id of the restaurant to find
     * @return the restaurant with the id restaurantId
     * @throws Exception if restaurant not found
     */
    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    /**
     * Get the restaurant that user with userId owns
     * @param userId the id of the owner of the restaurant
     * @return the restaurant that user with userId owns
     * @throws Exception if restaurant not found
     */
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    /**
     * Add restaurant with restaurantId to user's favourites list
     * @param restaurantId the id of the restaurant to add to favourites
     * @param user the user to add the restaurant to the favourites of
     * @return the RestaurantDto that has been added to user's favourites
     * @throws Exception if restaurant not found
     */
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception;

    /**
     * Update to open/close status of a restaurant.
     * If open, it will be set as closed. If closed, it will be set as open.
     * @param restaurantId the id of the restaurant to change the status of
     * @return the updated restaurant
     * @throws Exception if restaurant not found
     */
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
