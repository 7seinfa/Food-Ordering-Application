package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This holds the repository of ingredient categories.
 * @author Hussein Abdallah
 */
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    List<IngredientCategory> findByRestaurantId(Long id);
}
