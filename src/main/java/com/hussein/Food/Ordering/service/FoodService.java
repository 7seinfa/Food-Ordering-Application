package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Category;
import com.hussein.Food.Ordering.model.Food;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.repository.FoodRepository;
import com.hussein.Food.Ordering.repository.RestaurantRepository;
import com.hussein.Food.Ordering.repository.UserRepository;
import com.hussein.Food.Ordering.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * This class implements the food service, allowing you to create, find and manipulate food.
 * @author Hussein Abdallah
 */
@Service
public class FoodService implements FoodServiceInterface{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(req.getCategory());
        food.setImages(req.getImages());
        food.setDescription(req.getDescription());
        food.setName(req.getName());
        food.setAvailable(true);
        food.setVegetarian(req.isVegetarian());
        food.setSeasonal(req.isSeasonal());
        food.setPrice(req.getPrice());
        food.setCreationDate(LocalDate.now());
        food.setRestaurant(restaurant);


        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findById(foodId);
        food.setRestaurant(null);

        foodRepository.save(food);
        foodRepository.delete(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) throws Exception {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) foods.removeIf(food -> !food.isVegetarian());
        if (isNonVeg) foods.removeIf(food -> food.isVegetarian());
        if (isSeasonal) foods.removeIf(food -> !food.isSeasonal());
        if (foodCategory!=null && foodCategory!="") foods.removeIf(food -> {
            if (food.getFoodCategory() != null) return !food.getFoodCategory().getName().equals(foodCategory);
            else return true;
        });
        return foods;
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findById(Long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);

        if (food.isEmpty()) throw new Exception("Food not found with id "+foodId);
        return food.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
