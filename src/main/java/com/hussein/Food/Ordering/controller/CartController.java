package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.Cart;
import com.hussein.Food.Ordering.model.CartItem;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.AddCartItemRequest;
import com.hussein.Food.Ordering.request.UpdateCartItemRequest;
import com.hussein.Food.Ordering.service.CartService;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The cart APIs of the website for users, which uses the Cart Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    /**
     * Adds an item to users cart.
     * @param req the information of the cart item to add
     * @param jwt the jwt of the user making the request
     * @return the resulting cart item
     * @throws Exception if jwt is invalid, or if food is not found
     */
    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        CartItem cartItem = cartService.addItemToCart(req, user);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    /**
     * Updates the quantity of item in cart
     * @param req the information of the cart item to update
     * @param jwt the jwt of the user making the request
     * @return the resulting cart item
     * @throws Exception if jwt is invalid or cart item is not found
     */
    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartQuantity(@RequestBody UpdateCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    /**
     * Deletes an item from cart
     * @param id the id of the cart item to delete
     * @param jwt the jwt of the user making the request
     * @return the resulting cart
     * @throws Exception if jwt is invalid or if cart item is not found
     */
    @DeleteMapping("/cart-item/{id}/remove}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartService.removeItemFromCart(id, user);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    /**
     * Clears the users cart
     * @param jwt the jwt of the user making the request
     * @return the resulting empty cart
     * @throws Exception if the jwt is invalid
     */
    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartService.clearCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    /**
     * Finds the users cart.
     * @param jwt the jwt of the user making the request
     * @return the cart
     * @throws Exception if the jwt is invalid
     */
    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
