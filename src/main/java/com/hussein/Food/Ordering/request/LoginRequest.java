package com.hussein.Food.Ordering.request;

import lombok.Data;

/**
 * This is a data structure containing the email and password entered by a user to log in.
 * @author Hussein Abdallah
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
