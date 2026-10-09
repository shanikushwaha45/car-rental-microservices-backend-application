package com.car_rental.owner_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerResponseDto {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}