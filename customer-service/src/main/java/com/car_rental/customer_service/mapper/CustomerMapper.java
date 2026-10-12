
package com.car_rental.customer_service.mapper;

import com.car_rental.customer_service.dto.CustomerRequestDto;
import com.car_rental.customer_service.dto.CustomerResponseDto;
import com.car_rental.customer_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDto requestDto);

    @Mapping(target = "id", source = "id")
    CustomerResponseDto toResponseDto(Customer customer);
}