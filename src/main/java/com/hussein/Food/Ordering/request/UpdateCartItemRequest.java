package com.hussein.Food.Ordering.request;

import lombok.Data;

/**
 * This is a data structure containing the information of a cart item to update the quantity of.
 * @author Hussein Abdallah
 */
@Data
public class UpdateCartItemRequest {
    private Long cartItemId;
    private int quantity;
}
