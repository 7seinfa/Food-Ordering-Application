package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.CartItem;
import com.hussein.Food.Ordering.model.Order;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.AddCartItemRequest;
import com.hussein.Food.Ordering.request.OrderRequest;
import com.hussein.Food.Ordering.service.OrderService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The order APIs of the website for users, which uses the Order Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * Creates a new order.
     * @param req the information of the order to create
     * @param jwt the jwt of the user making the request
     * @return the resulting order
     * @throws Exception if jwt is invalid or restaurant is not found
     */
    @PostMapping("/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Order order = orderService.createOrder(req, user);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * Gets users order history
     * @param jwt the jwt of the user making the request
     * @return the list of orders user has made
     * @throws Exception if jwt is invalid
     */
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Order> orders = orderService.getUsersOrders(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
