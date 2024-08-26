package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Category;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.response.MessageResponse;
import com.hussein.Food.Ordering.service.CategoryService;
import com.hussein.Food.Ordering.service.RestaurantService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The category APIs of the website, which uses the Category Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Creates a new category (admins/restaurant owners only).
     * @param category the category to create
     * @param jwt the jwt of the user making the request
     * @return the resulting category
     * @throws Exception if jwt is invalid or id of restaurant owner provided in category is invalid
     */
    @PostMapping("/admin/category/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(categoryService.createCategory(category.getName(), category.getId()), HttpStatus.CREATED);
    }

    /**
     * Deletes a category (admins/restaurant owners only).
     * @param id the id of the category
     * @param jwt the jwt of the user making the request
     * @return a message stating category has been deleted
     * @throws Exception if jwt is invalid or category is not found
     */
    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable Long id,
                                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        categoryService.deleteCategory(id);

        MessageResponse mes = new MessageResponse();
        mes.setMessage("Category has been deleted.");

        return new ResponseEntity<>(mes,HttpStatus.OK);
    }

    /**
     * Finds food categories of a restaurant.
     * @param id the id of the restaurant
     * @param jwt the jwt of the user making the request
     * @return a list of categories of the restaurant
     * @throws Exception if jwt is invalid or restaurant is not found
     */
    @PostMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> findRestaurantCategories(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(categoryService.findCategoryByRestaurantId(id), HttpStatus.CREATED);
    }
}
