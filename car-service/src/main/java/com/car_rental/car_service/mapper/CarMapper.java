package com.car_rental.car_service.mapper;

import com.car_rental.car_service.dto.CarRequestDto;
import com.car_rental.car_service.dto.CarResponseDto;
import com.car_rental.car_service.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarRequestDto dto);

    CarResponseDto toResponseDto(Car car);
}