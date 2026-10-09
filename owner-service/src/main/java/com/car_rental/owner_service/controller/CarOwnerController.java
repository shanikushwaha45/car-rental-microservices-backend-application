
package com.car_rental.owner_service.controller;

import com.car_rental.owner_service.dto.CarOwnerRequestDto;
import com.car_rental.owner_service.dto.CarOwnerResponseDto;
import com.car_rental.owner_service.service.CarOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class CarOwnerController {

    private final CarOwnerService carOwnerService;

    // Register owner
    @PostMapping("/register")
    public ResponseEntity<CarOwnerResponseDto> registerOwner(
            @Valid @RequestBody CarOwnerRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carOwnerService.registerOwner(requestDto));
    }

    // Get all owners
    @GetMapping
    public ResponseEntity<List<CarOwnerResponseDto>> getAllOwners() {
        return ResponseEntity.ok(carOwnerService.getAllOwners());
    }

    // Get owner by ID
    @GetMapping("/{id}")
    public ResponseEntity<CarOwnerResponseDto> getOwnerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(carOwnerService.getOwnerById(id));
    }

    // Get owner by email
    @GetMapping("/email/{email}")
    public ResponseEntity<CarOwnerResponseDto> getOwnerByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(carOwnerService.getOwnerByEmail(email));
    }
}