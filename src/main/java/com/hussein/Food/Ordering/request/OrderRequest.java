package com.hussein.Food.Ordering.request;

import com.hussein.Food.Ordering.model.Address;
import lombok.Data;

/**
 * This is a data structure containing the information of an order to create.
 * @author Hussein Abdallah
 */
@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
