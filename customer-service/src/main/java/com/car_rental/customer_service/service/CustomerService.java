
package com.car_rental.customer_service.service;

import com.car_rental.customer_service.dto.CustomerRequestDto;
import com.car_rental.customer_service.dto.CustomerResponseDto;
import com.car_rental.customer_service.entity.Customer;
import com.car_rental.customer_service.exception.ResourceNotFoundException;
import com.car_rental.customer_service.mapper.CustomerMapper;
import com.car_rental.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDto registerCustomer(CustomerRequestDto requestDto) {
        if (customerRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Customer customer = customerMapper.toEntity(requestDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDto(savedCustomer);
    }

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }


    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + id
                        ));

        return customerMapper.toResponseDto(customer);
    }

    public CustomerResponseDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with email: " + email
                        ));

        return customerMapper.toResponseDto(customer);
    }
}