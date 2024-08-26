package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.Cart;
import com.hussein.Food.Ordering.model.CartItem;
import com.hussein.Food.Ordering.model.Food;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.repository.CartItemRepository;
import com.hussein.Food.Ordering.repository.CartRepository;
import com.hussein.Food.Ordering.repository.FoodRepository;
import com.hussein.Food.Ordering.repository.UserRepository;
import com.hussein.Food.Ordering.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class implements the cart service, allowing you to find and manipulate carts.
 * @author Hussein Abdallah
 */


@Service
public class CartService implements CartServiceInterface{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, User user) throws Exception {
        Food food = foodService.findById(req.getFoodId());
        Cart cart = findCartByUserId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredientsList());
        newCartItem.setTotalPrice(food.getPrice()*req.getQuantity());

        cart.setTotal(calculateCartTotal(cart));
        newCartItem.setCart(cartRepository.save(cart));

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        CartItem cartItem =  findCartItemById(cartItemId);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(quantity*cartItem.getFood().getPrice());

        Cart cart = cartItem.getCart();
        cart.setTotal(calculateCartTotal(cart));
        cartItem.setCart(cartRepository.save(cart));

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, User user) throws Exception {
        Cart cart = findCartById(user.getId());
        CartItem cartItem =  findCartItemById(cartItemId);

        cart.getItems().remove(cartItem);

        cart.setTotal(calculateCartTotal(cart));

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;

        for (CartItem cartItem : cart.getItems()) {
            total+=cartItem.getTotalPrice();
        }

        return total;
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws Exception {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);

        if(cartItemOpt.isEmpty()) throw new Exception("Could not find cart item");

        return cartItemOpt.get();
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOpt = cartRepository.findById(id);

        if(cartOpt.isEmpty()) throw new Exception("Could not find cart");

        return cartOpt.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();

        return cartRepository.save(cart);
    }
}
