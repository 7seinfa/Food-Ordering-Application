package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This holds the repository of carts.
 * @author Hussein Abdallah
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
