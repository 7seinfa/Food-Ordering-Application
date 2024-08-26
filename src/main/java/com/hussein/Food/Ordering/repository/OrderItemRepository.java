package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This holds the repository of order items.
 * @author Hussein Abdallah
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
