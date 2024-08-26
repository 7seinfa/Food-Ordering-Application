package com.hussein.Food.Ordering.request;

import com.hussein.Food.Ordering.model.IngredientsItem;
import lombok.Data;

import java.util.List;

/**
 * This is a data structure containing the information of a cart item to create or update.
 * @author Hussein Abdallah
 */
@Data
public class AddCartItemRequest {
    private Long foodId;
    private int quantity;
    private List<String> ingredientsList;
}
