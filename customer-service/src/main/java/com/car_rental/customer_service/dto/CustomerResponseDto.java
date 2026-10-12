
package com.car_rental.customer_service.dto;

public record CustomerResponseDto(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String address
) {
}