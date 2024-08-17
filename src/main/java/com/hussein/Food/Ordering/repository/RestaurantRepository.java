package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * This holds the repository of restaurants.
 * @author Hussein Abdallah
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%',:query, '%'))" +
            "OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(Long userId);
}
