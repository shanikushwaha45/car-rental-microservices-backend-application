
package com.car_rental.customer_service.controller;

import com.car_rental.customer_service.dto.CustomerLoginRequestDto;
import com.car_rental.customer_service.dto.CustomerRequestDto;
import com.car_rental.customer_service.dto.CustomerResponseDto;
import com.car_rental.customer_service.dto.LoginResponseDto;
import com.car_rental.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Register a new customer
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto registerCustomer(
            @Valid @RequestBody CustomerRequestDto requestDto) {

        return customerService.registerCustomer(requestDto);
    }

    // Get all customers
    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(
            @PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    // Get customer by email
    @GetMapping("/email/{email}")
    public CustomerResponseDto getCustomerByEmail(
            @PathVariable String email) {

        return customerService.getCustomerByEmail(email);
    }


    @PostMapping("/login")
    public LoginResponseDto loginCustomer(
            @Valid @RequestBody CustomerLoginRequestDto requestDto) {
        return customerService.loginCustomer(requestDto);
    }
}