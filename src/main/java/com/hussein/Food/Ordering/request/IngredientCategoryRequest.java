package com.hussein.Food.Ordering.request;

import lombok.Data;

/**
 * This is a data structure containing the information of an ingredient category to create.
 * @author Hussein Abdallah
 */
@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantId;
}
