package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user APIs of the website for the general user, which uses the User Service.
 * @author Hussein Abdallah
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Find the current user.
     * @param jwt the jwt of the user making the request
     * @return the User found
     * @throws Exception if jwt is invalid
     */
    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
