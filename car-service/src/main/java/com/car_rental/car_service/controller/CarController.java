package com.car_rental.car_service.controller;



import com.car_rental.car_service.dto.CarRequestDto;
import com.car_rental.car_service.dto.CarResponseDto;
import com.car_rental.car_service.entity.Car;

import com.car_rental.car_service.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @PostMapping("registerCars")
    public ResponseEntity<CarResponseDto> saveCar(@RequestBody @Valid CarRequestDto car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }

    @GetMapping("/getCar/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable Long id) {

        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarResponseDto> updateCar(
            @PathVariable Long id,
            @RequestBody @Valid CarRequestDto requestDto) {

        return ResponseEntity.ok(
                carService.updateCar(id, requestDto)
        );
    }

    @GetMapping("/getCars")
    public ResponseEntity<List<CarResponseDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }
}
