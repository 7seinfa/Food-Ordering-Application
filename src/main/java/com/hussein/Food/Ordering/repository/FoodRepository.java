package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This holds the repository of foods.
 * @author Hussein Abdallah
 */
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT food FROM Food f WHERE lower(f.name) LIKE lower(concat('%',:keyword, '%'))" +
            "OR lower(f.foodCategory.name) LIKE lower(concat('%',:keyword, '%'))")
    List<Food> searchFood(@Param("keyword") String keyword);
}
