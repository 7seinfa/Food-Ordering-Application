package com.hussein.Food.Ordering.request;

import com.hussein.Food.Ordering.model.Address;
import com.hussein.Food.Ordering.model.ContactInformation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

/**
 * This is a data structure containing the information of a restaurant to create or update.
 * @author Hussein Abdallah
 */
@Data
public class CreateRestaurantRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
