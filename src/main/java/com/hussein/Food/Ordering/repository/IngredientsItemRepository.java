package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This holds the repository of ingredients.
 * @author Hussein Abdallah
 */
public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, Long> {
    List<IngredientsItem> findByRestaurantId(Long id);
}
