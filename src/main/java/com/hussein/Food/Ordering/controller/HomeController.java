package com.hussein.Food.Ordering.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The home page of the website. Currently it is just a get request which returns "Welcome!"
 * @author Hussein Abdallah
 */
@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> HomeController() {
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }
}
