package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.CreateRestaurantRequest;
import com.hussein.Food.Ordering.response.MessageResponse;
import com.hussein.Food.Ordering.service.RestaurantService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The restaurants APIs of the website for the admins/owners of restaurants, which uses the Restaurant Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    /**
     * Create a new restaurant under the current user.
     * @param req the details of the restaurant to create
     * @param jwt the jwt of the user making the request
     * @return the restaurant created
     * @throws Exception if the jwt is invalid
     */
    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    /**
     * Update the restaurant with given id with details provided.
     * @param req the details to update restaurant with
     * @param jwt the jwt of the user making the request
     * @param id the id of the restaurant to update
     * @return the updated restaurant
     * @throws Exception if jwt is invalid or if restaurant not found
     */
    @PutMapping("/{id}/update")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    /**
     * Delete the restaurant with provided id
     * @param jwt the jwt of the user making the request
     * @param id the id of the restaurant to delete
     * @return a message stating the restaurant has been deleted.
     * @throws Exception if jwt is invalid or if restaurant not found
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        restaurantService.deleteRestaurant(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    /**
     * Update the status of the restaurant with provided id.
     * @param jwt the jwt of the user making the request
     * @param id the id of the restaurant to update
     * @return the updated restaurant
     * @throws Exception if jwt is invalid or if restaurant not found
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    /**
     * Get restaurant that the current user owns.
     * @param jwt the jwt of the user making the request
     * @return the restaurant found
     * @throws Exception if jwt is invalid or if restaurant not found
     */
    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserID(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
