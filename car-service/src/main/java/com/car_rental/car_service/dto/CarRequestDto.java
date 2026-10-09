package com.car_rental.car_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Fuel type is required")
    private String fuelType;

    @NotBlank(message = "Model is required")
    private String model;

    @Positive(message = "Seating capacity must be greater than 0")
    private int seatingCapacity;

    @Positive(message = "Price per day must be greater than 0")
    private double pricePerDay;

    @Positive(message = "Price per km must be greater than 0")
    private double pricePerKm;

    @NotBlank(message = "Pin code is required")
    private String pinCode;

    @NotNull(message = "Car owner ID is required")
    private Long carOwnerId;
}