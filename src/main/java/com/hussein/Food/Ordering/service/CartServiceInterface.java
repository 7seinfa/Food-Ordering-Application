package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Cart;
import com.hussein.Food.Ordering.model.CartItem;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.request.AddCartItemRequest;

/**
 * This is the interface of the cart service, allowing you to find and manipulate carts.
 * @author Hussein Abdallah
 */
public interface CartServiceInterface {

    /**
     * Add an item to cart
     * @param req the information of the cart item to add
     * @param user the user who owns the cart to add item to
     * @return the resulting cart item
     * @throws Exception if food item is not found
     */
    public CartItem addItemToCart(AddCartItemRequest req, User user) throws Exception;

    /**
     * Update the quantity of an item in cart.
     * @param cartItemId the id of the cart item to change the quantity of
     * @param quantity the new quantity of the cart item
     * @return the resulting cart item
     * @throws Exception if cart item is not found
     */
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    /**
     * Remove a cart item from the cart.
     * @param cartItemId the id of the cart item to remove
     * @param user the user to remove the cart item from
     * @return the resulting cart
     * @throws Exception if cart item is not found
     */
    public Cart removeItemFromCart(Long cartItemId, User user) throws Exception;

    /**
     * Calculate the total price of the items in the cart.
     * @param cart the cart to find the total of
     * @return the total price
     * @throws Exception if cart item is not found
     */
    public Long calculateCartTotal (Cart cart) throws Exception;

    /**
     * Finds a cart item using the given id.
     * @param cartItemId the id of the cart item to search for
     * @return the cart item
     * @throws Exception if cart item is not found
     */
    public CartItem findCartItemById(Long cartItemId) throws Exception;

    /**
     * Finds a cart using the given id.
     * @param id the id of the cart to find
     * @return the cart
     * @throws Exception if cart is not found
     */
    public Cart findCartById (Long id) throws Exception;

    /**
     * Finds cart by user id.
     * @param userId the id of the user owning the cart
     * @return the cart
     * @throws Exception if cart is not found
     */
    public Cart findCartByUserId (Long userId) throws Exception;

    /**
     * Clears the cart belonging to the user with the id provided
     * @param userId the id of the user owning the cart
     * @return the cart
     * @throws Exception if user is not found
     */
    public Cart clearCart (Long userId) throws Exception;
}
