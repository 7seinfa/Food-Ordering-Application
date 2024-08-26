package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Food;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.CreateFoodRequest;
import com.hussein.Food.Ordering.service.FoodService;
import com.hussein.Food.Ordering.service.RestaurantService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The food APIs of the website for users, which uses the Food Service.
 * @author Hussein Abdallah
 */

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Searches for foods using a keyword.
     * @param keyword the keyword to search
     * @param jwt the jwt of the user making the request
     * @return the list of foods found
     * @throws Exception if jwt is invalid
     */
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(foodService.searchFood(keyword), HttpStatus.OK);
    }

    /**
     * Finds the foods of a restaurant.
     * @param restaurantId the id of the restaurant to pull foods of
     * @param isVegetarian pull only vegetarian foods
     * @param isNonVeg pull only non-vegetarian foods
     * @param isSeasonal pull only seasonal foods
     * @param foodCategory pull only foods belonging to provided category
     * @param jwt the jwt of the user making the request
     * @return the list of foods of the restaurant
     * @throws Exception if jwt is invalid or restaurant is not found
     */
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> searchFood(@PathVariable Long restaurantId,
                                                 @RequestParam boolean isVegetarian,
                                                 @RequestParam boolean isSeasonal,
                                                 @RequestParam boolean isNonVeg,
                                                 @RequestParam String foodCategory,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(foodService.getRestaurantsFood(restaurantId, isVegetarian, isNonVeg, isSeasonal, foodCategory), HttpStatus.CREATED);
    }
}
