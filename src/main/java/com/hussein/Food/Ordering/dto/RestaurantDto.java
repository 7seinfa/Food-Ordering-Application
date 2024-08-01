package com.hussein.Food.Ordering.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * This class contains the information needed to display a restaurant.
 * @author Hussein Abdallah
 */
@Data
@Embeddable
public class RestaurantDto {
    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;
    private Long id;

}
