package com.car_rental.owner_service.mapper;

import com.car_rental.owner_service.dto.CarOwnerRequestDto;
import com.car_rental.owner_service.dto.CarOwnerResponseDto;
import com.car_rental.owner_service.entity.CarOwner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarOwnerMapper {

    CarOwner toEntity(CarOwnerRequestDto dto);

    CarOwnerResponseDto toResponseDto(CarOwner carOwner);
}