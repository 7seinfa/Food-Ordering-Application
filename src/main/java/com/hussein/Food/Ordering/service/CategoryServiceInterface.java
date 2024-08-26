package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Category;

import java.util.List;

/**
 * This is the interface of the category service, allowing you to find and manipulate food categories.
 * @author Hussein Abdallah
 */
public interface CategoryServiceInterface {

    /**
     * Creates a new category.
     * @param name the name of the category
     * @param userId the id of the creator of the category
     * @return the resulting category
     * @throws Exception if user is not found
     */
    public Category createCategory (String name, Long userId) throws Exception;

    /**
     * Finds the categories of a restaurant
     * @param restaurantId the id of the restaurant to pull categories from
     * @return the list of categories of the restaurant
     * @throws Exception if restaurant is not found
     */
    public List<Category> findCategoryByRestaurantId (Long restaurantId) throws Exception;

    /**
     * Finds the category with the id provided
     * @param id the id of the category
     * @return the category
     * @throws Exception if category is not found
     */
    public Category findCategoryById (Long id) throws Exception;

    /**
     * Deletes the category with the id provided
     * @param id the id of the category to delete
     * @throws Exception if category is not found
     */
    public void deleteCategory (Long id) throws Exception;
}
