package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.*;
import com.hussein.Food.Ordering.repository.*;
import com.hussein.Food.Ordering.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the order service, allowing you to create, find, and manipulate orders.
 * @author Hussein Abdallah
 */
@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest req, User user) throws Exception {
        Address savedAddress = addressRepository.save(req.getDeliveryAddress());

        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Order order = new Order();
        order.setCustomer(user);
        order.setCreatedAt(new Date());
        order.setOrderStatus("PENDING");
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(savedAddress);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        Long totalAmount = 0L;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItems.add(orderItemRepository.save(orderItem));
            totalAmount+=cartItem.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalPrice(cart.getTotal());
        order.setTotalAmount(cartService.calculateCartTotal(cart));

        Order savedOrder = orderRepository.save(order);

        restaurant.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);

        if (orderStatus.equals("OUT_FOR_DELIVER") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Invalid order status provided");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrders(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus != null || !orderStatus.equals("")) orders.removeIf(order -> !order.getOrderStatus().equals(orderStatus));

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) throw new Exception("Order not found with id "+orderId);
        return order.get();
    }
}
