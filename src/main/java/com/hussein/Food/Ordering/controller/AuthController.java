package com.hussein.Food.Ordering.controller;

import com.hussein.Food.Ordering.config.JwtProvider;
import com.hussein.Food.Ordering.model.Cart;
import com.hussein.Food.Ordering.model.USER_ROLE;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.repository.CartRepository;
import com.hussein.Food.Ordering.repository.UserRepository;
import com.hussein.Food.Ordering.request.LoginRequest;
import com.hussein.Food.Ordering.response.AuthResponse;
import com.hussein.Food.Ordering.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This class consists of the REST requests which will be called to sign in or create accounts with
 * authentication. The two links provided for this are [URL]/auth/signup and [URL]/auth/login.
 * Both are post requests.
 * @author Hussein Abdallah
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    /**
     * The signup API, which is called to create a new user. Located at [URL]/auth/signup
     * @param user the user object of the user which should be created.
     *             This requires the email, password, full-name, and role to be filled out.
     * @return a response indicating the registration was successful
     * @throws Exception the account could not be created
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new Exception("There is an account associated with this email.");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration completed!");
        authResponse.setRole(user.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    /**
     * The login API, which is used to authenticate a user's log-in attempt.Located at [URL]/auth/login
     * @param loginRequest the username and password of the user to sign into
     * @return a response indicating the login was successful
     * @throws Exception the login failed
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication =  authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successful!");
        Collection<? extends GrantedAuthority>authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    /**
     * A function to authenticate a user, checking if it exists in the database
     * and if the password matches.
     * @param email the email of the user
     * @param password the password of the user
     * @return a UsernamePasswordAuthenticationToken with the details of the user
     */
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if (userDetails==null) {
            throw new BadCredentialsException("User not found.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
