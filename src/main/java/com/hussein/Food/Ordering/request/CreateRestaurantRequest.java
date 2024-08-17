package com.hussein.Food.Ordering.request;

import com.hussein.Food.Ordering.model.Address;
import com.hussein.Food.Ordering.model.ContactInformation;
import lombok.Data;

import java.util.List;

/**
 * This is a data structure containing the information of a restaurant to create or update.
 * @author Hussein Abdallah
 */
@Data
public class CreateRestaurantRequest {
    private Long id;

    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
