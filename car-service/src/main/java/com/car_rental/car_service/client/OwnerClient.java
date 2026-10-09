package com.car_rental.car_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "owner-service",
        url = "http://localhost:8082"
)
public interface OwnerClient {

    @GetMapping("/api/owners/{id}")
    OwnerResponseDto getOwnerById(@PathVariable("id") Long id);
}
