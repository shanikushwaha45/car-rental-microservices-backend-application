package com.car_rental.car_service.service;

import com.car_rental.car_service.client.OwnerClient;
import com.car_rental.car_service.client.OwnerResponseDto;
import com.car_rental.car_service.dto.CarRequestDto;
import com.car_rental.car_service.dto.CarResponseDto;
import com.car_rental.car_service.entity.Car;
import com.car_rental.car_service.mapper.CarMapper;
import com.car_rental.car_service.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final OwnerClient ownerClient;

    private final CarRepository carRepository;

    private final CarMapper carMapper;


    public CarResponseDto saveCar(CarRequestDto requestDto) {

        // Check whether the owner exists in Owner Service
        OwnerResponseDto owner =
                ownerClient.getOwnerById(requestDto.getCarOwnerId());

        // Save the car only if the owner exists
        Car car = carMapper.toEntity(requestDto);

        Car savedCar = carRepository.save(car);

        return carMapper.toResponseDto(savedCar);
    }

    public List<CarResponseDto> getAllCars() {

        List<Car> cars = carRepository.findAll();

        return cars.stream()
                .map(carMapper::toResponseDto)
                .toList();
    }

    public CarResponseDto getCarById(Long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        return carMapper.toResponseDto(car);
    }

    public CarResponseDto updateCar(Long id, CarRequestDto requestDto) {

        Car existingCar = carRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Car not found with id: " + id));

        existingCar.setVehicleNumber(requestDto.getVehicleNumber());
        existingCar.setBrand(requestDto.getBrand());
        existingCar.setFuelType(requestDto.getFuelType());
        existingCar.setModel(requestDto.getModel());
        existingCar.setSeatingCapacity(requestDto.getSeatingCapacity());
        existingCar.setPricePerDay(requestDto.getPricePerDay());
        existingCar.setPricePerKm(requestDto.getPricePerKm());
        existingCar.setPinCode(requestDto.getPinCode());
        existingCar.setCarOwnerId(requestDto.getCarOwnerId());

        Car updatedCar = carRepository.save(existingCar);

        return carMapper.toResponseDto(updatedCar);
    }
}