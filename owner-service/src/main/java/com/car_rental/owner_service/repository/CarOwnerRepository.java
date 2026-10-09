package com.car_rental.owner_service.repository;

import com.car_rental.owner_service.entity.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

    Optional<CarOwner> findByEmail(String email);

    boolean existsByEmail(String email);
}