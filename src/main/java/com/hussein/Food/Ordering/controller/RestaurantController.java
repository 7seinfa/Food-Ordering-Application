package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.dto.RestaurantDto;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.service.RestaurantService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The restaurants APIs of the website for the general user, which uses the Restaurant Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    /**
     * The search API for the website.
     * @param jwt the jwt of the user sending the request
     * @param keyword the keyword to search for
     * @return the list of restaurants found
     * @throws Exception if jwt is invalid
     */
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    /**
     * Get all restaurants in the database
     * @param jwt the jwt of the user making the request
     * @return a list of all restaurants in the database
     * @throws Exception if jwt is invalid
     */
    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurants = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    /**
     * Get the restaurant with provided id
     * @param jwt the jwt of the user making the request
     * @param id the id of the restaurant to get
     * @return the restaurant with given id
     * @throws Exception if jwt is invalid or if user not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurants = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    /**
     * Add the restaurant with provided id to favourites of the user making the request
     * @param jwt the jwt of the user making the request
     * @param id the id of the restaurant to favourite
     * @return the RestaurantDto of the favourited restaurant
     * @throws Exception if jwt is invalid or if user not found
     */
    @GetMapping("/{id}/add-to-favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        RestaurantDto restaurant = restaurantService.addToFavourites(id, user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
