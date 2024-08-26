package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Order;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.OrderRequest;
import com.hussein.Food.Ordering.service.OrderService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The order APIs of the website for the admins/owners of restaurants, which uses the Order Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * Gets order history of a restaurant.
     * @param id the id of the restaurant
     * @param order_status the order status to filter for
     * @param jwt the jwt of the user making the request
     * @return a list of orders
     * @throws Exception if jwt is invalid or if restaurant is not found
     */
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id,
                                                       @RequestParam(required = false) String order_status,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Order> orders = orderService.getRestaurantOrders(id, order_status);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Updates status of an order
     * @param id the id of the order to update status of
     * @param orderStatus the new status to set (must be "PENDING", "OUT_FOR_DELIVER", "DELIVERED", or "COMPLETED"
     * @param jwt the jwt of the user making the request
     * @return the resulting order
     * @throws Exception if jwt is invalid, or if order is not found
     */
    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                       @PathVariable String orderStatus,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Order order = orderService.updateOrder(id, orderStatus);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
