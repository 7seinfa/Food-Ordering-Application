package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Food;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.CreateFoodRequest;
import com.hussein.Food.Ordering.response.MessageResponse;
import com.hussein.Food.Ordering.service.FoodService;
import com.hussein.Food.Ordering.service.RestaurantService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The foods APIs of the website for the admins/owners of restaurants, which uses the Food Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Creates a new food.
     * @param req the information of the food to create.
     * @param jwt the jwt of the user making the request
     * @return the food created
     * @throws Exception if jwt token invalid or if restaurant is not found
     */
    @PostMapping("/create")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        return new ResponseEntity<>(foodService.createFood(req, restaurant), HttpStatus.CREATED);
    }

    /**
     * Deletes a food.
     * @param id the id of the food to delete
     * @param jwt the jwt of the user making the request
     * @return a message stating food has been deleted
     * @throws Exception if jwt is invalid or if food is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse mes = new MessageResponse();
        mes.setMessage("Food deleted successfully");
        return new ResponseEntity<>(mes, HttpStatus.OK);
    }

    /**
     * Updates the availability status of a food.
     * @param id the id of the food
     * @param jwt the jwt of the user making the request
     * @return the resulting food
     * @throws Exception if jwt is invalid or food is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
