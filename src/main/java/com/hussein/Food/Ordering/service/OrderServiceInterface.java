package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Order;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.OrderRequest;

import java.util.List;

/**
 * This is the interface of the order service, allowing you to create, find, and manipulate orders.
 * @author Hussein Abdallah
 */
public interface OrderServiceInterface {

    /**
     * Creates an order.
     * @param req a request containing the information of the order to create
     * @param user the user making the order
     * @return the resulting order
     * @throws Exception if restaurant is not found
     */
    public Order createOrder(OrderRequest req, User user) throws Exception;

    /**
     * Updates the order status of an order
     * @param orderId the id of the order to update
     * @param orderStatus the new status (must be "PENDING", "OUT_FOR_DELIVER", "DELIVERED", or "COMPLETED")
     * @return the resulting order
     * @throws Exception if order is not found, or if order status is invalid
     */
    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    /**
     * cancels an order
     * @param orderId the id of the order to cancel
     * @throws Exception if order is not found
     */
    public void cancelOrder(Long orderId) throws Exception;

    /**
     * Get the history of orders by a user
     * @param userId the id of the user
     * @return a list of orders
     * @throws Exception if user is not found
     */
    public List<Order> getUsersOrders(Long userId) throws Exception;

    /**
     * Finds the history of orders from a restaurant
     * @param restaurantId the id of the restaurant
     * @param orderStatus the order status to filter full, leave null or blank to not filter
     * @return the list of orders
     * @throws Exception if restaurant is not found
     */
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;

    /**
     * Finds an order by its id.
     * @param orderId the id of the order
     * @return the order
     * @throws Exception if order is not found
     */
    public Order findOrderById (Long orderId) throws Exception;
}
