package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This holds the repository of users.
 * @author Hussein Abdallah
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
