package com.car_rental.car_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;

    private String brand;

    private String fuelType;

    private String model;

    private int seatingCapacity;

    private double pricePerDay;

    private double pricePerKm;

    private String pinCode;

    private Long carOwnerId;
}
