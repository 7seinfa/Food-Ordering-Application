package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.IngredientCategory;
import com.hussein.Food.Ordering.model.IngredientsItem;
import com.hussein.Food.Ordering.request.IngredientCategoryRequest;
import com.hussein.Food.Ordering.request.IngredientsItemRequest;
import com.hussein.Food.Ordering.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ingredients APIs of the website for admins/restaurant owners, which uses the Ingredients Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;

    /**
     * Creates an ingredient category.
     * @param req the information of the category to create
     * @param jwt the jwt of the user making the request
     * @return the resulting ingredient category
     * @throws Exception if jwt is invalidor restaurant not found
     */
    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory (@RequestBody IngredientCategoryRequest req,
                                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    /**
     * Creates an ingredients item.
     * @param req the information of the ingredient to create
     * @param jwt the jwt of the user making the request
     * @return the resulting ingredients item
     * @throws Exception if jwt is invalid or if ingredients category or restaurant is not found
     */
    @PostMapping("")
    public ResponseEntity<IngredientsItem> createIngredientsItem (@RequestBody IngredientsItemRequest req,
                                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientsItem item = ingredientsService.createIngredientsItem(req.getName(), req.getCategoryId(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    /**
     * Updates whether ingredient is in stock or not
     * @param id the id of the ingredient
     * @param jwt the jwt of the user making the request
     * @return the resulting ingredients item
     * @throws Exception if jwt is invalid or ingredient is not found
     */
    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientsStock (@PathVariable Long id,
                                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * Gets a list of ingredients at a restaurant
     * @param restaurantId the restaurant id
     * @param jwt the jwt of the user making the request
     * @return the list of ingredients
     * @throws Exception if jwt is invalid or restaurant is not found
     */
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients (@PathVariable Long restaurantId,
                                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(restaurantId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * Gets the ingredient categories at a restaurant.
     * @param restaurantId id of the restaurant
     * @param jwt the jwt or the user making the request
     * @return the list of ingredient categories at the restaurant
     * @throws Exception if jwt is invalid or restaurant is not found
     */
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory (@PathVariable Long restaurantId,
                                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(restaurantId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
