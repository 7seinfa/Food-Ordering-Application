package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This holds the repository of food categories.
 * @author Hussein Abdallah
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByRestaurantId(Long id);
}
