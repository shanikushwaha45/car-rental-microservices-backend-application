
package com.car_rental.car_service.client;

public record OwnerResponseDto(
        Long id,
        String name,
        String email,
        String address,
        String phoneNumber
) {}