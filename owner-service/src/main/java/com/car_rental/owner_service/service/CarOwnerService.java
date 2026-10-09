
package com.car_rental.owner_service.service;

import com.car_rental.owner_service.dto.CarOwnerRequestDto;
import com.car_rental.owner_service.dto.CarOwnerResponseDto;
import com.car_rental.owner_service.entity.CarOwner;
import com.car_rental.owner_service.exception.ResourceNotFoundException;
import com.car_rental.owner_service.mapper.CarOwnerMapper;
import com.car_rental.owner_service.repository.CarOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarOwnerService {

    private final CarOwnerRepository carOwnerRepository;
    private final CarOwnerMapper carOwnerMapper;

    // Register a new owner
    public CarOwnerResponseDto registerOwner(CarOwnerRequestDto requestDto) {

        if (carOwnerRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        CarOwner carOwner = carOwnerMapper.toEntity(requestDto);

        CarOwner savedOwner = carOwnerRepository.save(carOwner);

        return carOwnerMapper.toResponseDto(savedOwner);
    }

    // Get all owners
    public List<CarOwnerResponseDto> getAllOwners() {
        return carOwnerRepository.findAll()
                .stream()
                .map(carOwnerMapper::toResponseDto)
                .toList();
    }

    // Get owner by ID
    public CarOwnerResponseDto getOwnerById(Long id) {
        CarOwner carOwner = carOwnerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Owner not found with id: " + id
                        ));

        return carOwnerMapper.toResponseDto(carOwner);
    }

    // Get owner by email
    public CarOwnerResponseDto getOwnerByEmail(String email) {
        CarOwner carOwner = carOwnerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Owner not found with email: " + email
                        ));

        return carOwnerMapper.toResponseDto(carOwner);
    }
}