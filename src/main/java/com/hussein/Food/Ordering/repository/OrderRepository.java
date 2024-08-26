package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This holds the repository of orders.
 * @author Hussein Abdallah
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);
}
