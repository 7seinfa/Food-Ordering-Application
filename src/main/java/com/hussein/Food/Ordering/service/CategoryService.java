package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Category;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the category service, allowing you to find and manipulate food categories.
 * @author Hussein Abdallah
 */

@Service
public class CategoryService implements CategoryServiceInterface{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception {
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) throw new Exception("Category is not found");
        return category.get();
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = findCategoryById(id);
        category.setRestaurant(null);
        categoryRepository.save(category);
        categoryRepository.delete(category);
    }
}
