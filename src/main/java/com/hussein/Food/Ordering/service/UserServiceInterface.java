package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.model.User;
/**
 * This is the interface of the user service, allowing you to find a user based on email or jwt token
 * @author Hussein Abdallah
 */
public interface UserServiceInterface {
    /**
     * Get User using the provided JWT
     * @param jwt the JWT of the User requested
     * @return the User associated with the JWT
     * @throws Exception if user is not found
     */
    public User findUserByJwtToken (String jwt) throws Exception;

    /**
     * Get User using the provided email
     * @param email the email of the User requested
     * @return the User associated with the email provided
     * @throws Exception if user is not found
     */
    public User findUserByEmail (String email) throws Exception;
}
