package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.dto.RestaurantDto;
import com.hussein.Food.Ordering.model.Address;
import com.hussein.Food.Ordering.model.Restaurant;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.repository.AddressRepository;
import com.hussein.Food.Ordering.repository.RestaurantRepository;
import com.hussein.Food.Ordering.repository.UserRepository;
import com.hussein.Food.Ordering.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the restaurant service, allowing you to find and manipulate restaurants.
 * @author Hussein Abdallah
 */
@Service
public class RestaurantService implements RestaurantServiceInterface{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(req.getAddress());
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant != null) {
            if (updateRequest.getName() != null) restaurant.setName(updateRequest.getName());
            if (updateRequest.getCuisineType() != null)restaurant.setCuisineType(updateRequest.getCuisineType());
            if (updateRequest.getContactInformation() != null)restaurant.setContactInformation(updateRequest.getContactInformation());
            if (updateRequest.getDescription() != null)restaurant.setDescription(updateRequest.getDescription());
            if (updateRequest.getOpeningHours() != null)restaurant.setOpeningHours(updateRequest.getOpeningHours());
            if (updateRequest.getImages() != null)restaurant.setImages(updateRequest.getImages());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);

        if (opt.isEmpty()) throw new Exception("Restaurant not found with id "+restaurantId);
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);

        if (restaurant == null) throw new Exception("Restaurant not found with owner id "+userId);
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();

        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavourited = false;
        List<RestaurantDto> favourites = user.getFavourites();
        for (RestaurantDto favourite : favourites) {
            if (favourite.getId().equals(restaurantId)) isFavourited = true;
        }

        if(isFavourited) favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
        else favourites.add(dto);

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
