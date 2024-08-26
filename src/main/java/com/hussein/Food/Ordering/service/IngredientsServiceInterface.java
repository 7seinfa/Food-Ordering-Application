package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.IngredientCategory;
import com.hussein.Food.Ordering.model.IngredientsItem;

import java.util.List;

/**
 * This is the interface of the ingredients service, allowing you to create,
 * find and manipulate ingredients items and ingredient categories.
 * @author Hussein Abdallah
 */
public interface IngredientsServiceInterface {

    /**
     * Creates a new ingredient category.
     * @param name the name of the category
     * @param restaurantId the id of the restaurant creating the ingredient category
     * @return the resulting ingredient category
     * @throws Exception if restaurant not found
     */
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    /**
     * Finds an ingredient category given its id.
     * @param id the id of the ingredient category to find
     * @return the ingredient category
     * @throws Exception if ingredient category not found
     */
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    /**
     * Finds the ingredient categories of a restaurant given its id.
     * @param restaurantId the id of the restaurant
     * @return the list of ingredient categories
     * @throws Exception if restaurant is not found
     */
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;

    /**
     * Creates an ingredients item.
     * @param name the name of the ingredient
     * @param categoryId the category of the ingredient
     * @param restaurantId the restaurant making the request
     * @return the resulting ingredients item
     * @throws Exception if ingredient category or restaurant is not found
     */
    public IngredientsItem createIngredientsItem(String name, Long categoryId, Long restaurantId) throws Exception;

    /**
     * Finds ingredients of a restaurant.
     * @param restaurantId the id of the restaurant
     * @return the list of ingredients items
     * @throws Exception if restaurant is not found
     */
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception;

    /**
     * Updates the stock of an ingredient in a restaurant.
     * @param id the id of the ingredients item
     * @return the resulting ingredients item
     * @throws Exception if ingredients item is not found
     */
    public IngredientsItem updateStock(Long id) throws Exception;
}
