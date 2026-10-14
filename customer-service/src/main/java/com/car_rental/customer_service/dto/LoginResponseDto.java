
package com.car_rental.customer_service.dto;

public record LoginResponseDto(
        String token,
        String tokenType,
        CustomerResponseDto customer
) {}