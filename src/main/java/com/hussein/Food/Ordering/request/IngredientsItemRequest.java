package com.hussein.Food.Ordering.request;

import lombok.Data;

/**
 * This is a data structure containing the information of an ingredient item to create.
 * @author Hussein Abdallah
 */
@Data
public class IngredientsItemRequest {
    private String name;
    private Long categoryId;
    private Long restaurantId;
}
