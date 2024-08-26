package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.IngredientCategory;
import com.hussein.Food.Ordering.model.IngredientsItem;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.repository.IngredientCategoryRepository;
import com.hussein.Food.Ordering.repository.IngredientsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the ingredients service, allowing you to create,
 * find and manipulate ingredients items and ingredient categories.
 * @author Hussein Abdallah
 */
@Service
public class IngredientsService implements IngredientsServiceInterface{
    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

        if(ingredientCategory.isEmpty()) throw new Exception("Ingredient category not found.");
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(String name, Long categoryId, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(name);
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setCategory(ingredientCategory);

        IngredientsItem saved = ingredientsItemRepository.save(ingredientsItem);
        ingredientCategory.getIngredients().add(saved);

        return saved;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItemOpt = ingredientsItemRepository.findById(id);
        if (ingredientsItemOpt.isEmpty()) throw new Exception("Ingredients item not found.");
        IngredientsItem ingredientsItem = ingredientsItemOpt.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }
}
