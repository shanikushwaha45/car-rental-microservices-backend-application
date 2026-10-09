package com.car_rental.car_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {

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
