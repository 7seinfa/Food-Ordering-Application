package com.hussein.Food.Ordering.repository;

import com.hussein.Food.Ordering.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This holds the repository of addresses.
 * @author Hussein Abdallah
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
