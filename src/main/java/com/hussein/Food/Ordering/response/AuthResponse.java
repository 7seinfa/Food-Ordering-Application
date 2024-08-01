package com.hussein.Food.Ordering.response;

import com.hussein.Food.Ordering.model.USER_ROLE;
import lombok.Data;

/**
 * This data structure contains the response the server gives when a user attempts to log in or sign up.
 * @author Hussein Abdallah
 */
@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private USER_ROLE role;
}
