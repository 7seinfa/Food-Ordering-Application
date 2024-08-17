package com.hussein.Food.Ordering.service;

import com.hussein.Food.Ordering.config.JwtProvider;
import com.hussein.Food.Ordering.model.User;
import com.hussein.Food.Ordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the user service, allowing you to find a user based on email or jwt token
 * @author Hussein Abdallah
 */

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        User user = userRepository.findByEmail(jwtProvider.getEmailFromJwtToken(jwt));

        if(user == null) throw new Exception("User not found");
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null) throw new Exception("User not found");
        return user;
    }
}
